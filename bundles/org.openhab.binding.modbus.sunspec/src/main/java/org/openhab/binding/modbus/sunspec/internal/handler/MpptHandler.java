/**
 * Copyright (c) 2010-2023 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.modbus.sunspec.internal.handler;

import static org.openhab.binding.modbus.ModbusBindingConstants.BINDING_ID;
import static org.openhab.binding.modbus.sunspec.internal.SunSpecConstants.*;
import static org.openhab.core.library.unit.SIUnits.CELSIUS;
import static org.openhab.core.library.unit.Units.*;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.openhab.binding.modbus.sunspec.internal.MpptOperatingState;
import org.openhab.binding.modbus.sunspec.internal.dto.MpptModelBlock;
import org.openhab.binding.modbus.sunspec.internal.parser.MpptModelParser;
import org.openhab.core.io.transport.modbus.ModbusRegisterArray;
import org.openhab.core.library.types.QuantityType;
import org.openhab.core.library.types.StringType;
import org.openhab.core.thing.*;
import org.openhab.core.thing.binding.ThingHandlerCallback;
import org.openhab.core.thing.type.ChannelGroupTypeUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles data received from a SunSpec Multiple MPPT Inverter Extension.
 *
 * @author Martin Renner
 */
@NonNullByDefault
public class MpptHandler extends AbstractSunSpecHandler {

    private final Logger logger = LoggerFactory.getLogger(MpptHandler.class);
    private final MpptModelParser parser = new MpptModelParser();
    private int currentModuleCount = 0;

    public MpptHandler(Thing thing) {
        super(thing);
    }

    @Override
    protected void handlePolledData(ModbusRegisterArray registers) {
        logger.trace("Model block received, size: {}", registers.size());

        MpptModelBlock block = parser.parse(registers);

        updateChannels(block.modules.size());

        for (var moduleNumber = 0; moduleNumber < block.modules.size(); moduleNumber++) {
            final var moduleBlock = block.modules.get(moduleNumber);
            final var groupId = generateGroupId(moduleNumber);

            updateState(channelUID(groupId, CHANNEL_MPPT_ID), new StringType(moduleBlock.moduleIdString));
            updateState(channelUID(groupId, CHANNEL_MPPT_DC_CURRENT),
                    getScaled(moduleBlock.current, block.currentSF, AMPERE));
            updateState(channelUID(groupId, CHANNEL_MPPT_DC_VOLTAGE),
                    getScaled(moduleBlock.voltage, block.voltageSF, VOLT));
            updateState(channelUID(groupId, CHANNEL_MPPT_DC_POWER), getScaled(moduleBlock.power, block.powerSF, WATT));
            updateState(channelUID(groupId, CHANNEL_MPPT_LIFETIME_ENERGY),
                    getScaled(moduleBlock.lifetimeEnergy, block.energySF, WATT_HOUR));
            updateState(channelUID(groupId, CHANNEL_MPPT_TEMPERATURE),
                    new QuantityType<>(moduleBlock.temperature, CELSIUS));
            updateState(channelUID(groupId, CHANNEL_MPPT_OPERATING_STATE),
                    new StringType(MpptOperatingState.fromCode(moduleBlock.operatingState).name()));
        }

        resetCommunicationError();
    }

    /**
     * Creates or delete channels according to the number of modules reported by the device.
     */
    private void updateChannels(int newModuleCount) {
        if (currentModuleCount == newModuleCount) {
            return;
        }

        logger.debug("Number of modules changed from {} to {}. Rebuilding channel groups.", currentModuleCount,
                newModuleCount);
        final List<Channel> toBeAddedChannels = new ArrayList<>();
        final List<Channel> toBeRemovedChannels = new ArrayList<>();

        if (currentModuleCount > newModuleCount) {
            for (var i = newModuleCount; i < currentModuleCount; i++) {
                toBeRemovedChannels.addAll(removeChannelsOfGroup(generateGroupId(i)));
            }
        } else {
            for (var i = currentModuleCount; i < newModuleCount; i++) {
                toBeAddedChannels.addAll(createChannelsForGroup(generateGroupId(i)));
            }
        }

        currentModuleCount = newModuleCount;

        logger.debug("toBeRemovedChannels: {}. toBeAddedChannels: {}", toBeRemovedChannels, toBeAddedChannels);
        if (!toBeAddedChannels.isEmpty() || !toBeRemovedChannels.isEmpty()) {
            final var builder = editThing().withoutChannels(toBeRemovedChannels);
            for (Channel channel : toBeAddedChannels) {
                builder.withChannel(channel);
            }
            updateThing(builder.build());
        }
    }

    private String generateGroupId(int moduleNumber) {
        return "module" + moduleNumber;
    }

    private List<Channel> removeChannelsOfGroup(String channelGroupId) {
        return getThing().getChannelsOfGroup(channelGroupId);
    }

    private List<Channel> createChannelsForGroup(String channelGroupId) {
        final var channelGroupUID = new ChannelGroupUID(getThing().getUID(), channelGroupId);
        final var channelGroupTypeUID = new ChannelGroupTypeUID(BINDING_ID, CHANNEL_GROUP_TYPE_MPPT_MODULE);

        final List<Channel> channels = new ArrayList<>();
        ThingHandlerCallback callback = getCallback();
        if (callback != null) {
            for (var channelBuilder : callback.createChannelBuilders(channelGroupUID, channelGroupTypeUID)) {
                final var newChannel = channelBuilder.build();
                final var existingChannel = getThing().getChannel(newChannel.getUID());
                if (existingChannel == null) {
                    channels.add(newChannel);
                }
            }
        }
        return channels;
    }
}
