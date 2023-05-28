/**
 * Copyright (c) 2010-2022 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional information.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License 2.0
 * which is available at http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.proconip.internal.handler;

import static org.openhab.binding.proconip.internal.ProConIpBindingConstants.*;
import static org.openhab.core.library.unit.MetricPrefix.MILLI;
import static org.openhab.core.library.unit.SIUnits.CELSIUS;
import static org.openhab.core.library.unit.Units.LITRE;
import static org.openhab.core.library.unit.Units.PERCENT;

import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jetty.client.HttpClient;
import org.openhab.binding.proconip.internal.ConnectionInformation;
import org.openhab.binding.proconip.internal.ProConIpCommunicationException;
import org.openhab.binding.proconip.internal.client.ProConIpClient;
import org.openhab.binding.proconip.internal.config.ProConIpChannelConfig;
import org.openhab.binding.proconip.internal.config.ProConIpThingConfiguration;
import org.openhab.binding.proconip.internal.model.DeviceState;
import org.openhab.binding.proconip.internal.relaisfilter.RelaisFilter;
import org.openhab.core.library.types.DecimalType;
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.library.types.QuantityType;
import org.openhab.core.thing.*;
import org.openhab.core.thing.binding.BaseThingHandler;
import org.openhab.core.types.Command;
import org.openhab.core.types.RefreshType;
import org.openhab.core.types.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link ProConIpHandler} is responsible for handling commands, which are sent to one of the channels.
 *
 * @author Martin Renner - Initial contribution
 */
@NonNullByDefault
public class ProConIpHandler extends BaseThingHandler {

    private final Logger logger = LoggerFactory.getLogger(ProConIpHandler.class);

    private final HttpClient httpClient;
    private @Nullable ProConIpClient proConIpClient;
    private @Nullable ScheduledFuture<?> pollingJob;
    private @Nullable DeviceState deviceState;
    private final RelaisFilter relaisFilter = new RelaisFilter();

    public ProConIpHandler(Thing thing, HttpClient httpClient) {
        super(thing);
        this.httpClient = httpClient;
    }

    @Override
    public void initialize() {
        ProConIpThingConfiguration config = getConfigAs(ProConIpThingConfiguration.class);
        logger.info("Initializing binding ProCon.IP for {}", config.hostname);
        ConnectionInformation conInfo = new ConnectionInformation(config.hostname);

        try {
            httpClient.start();
        } catch (Exception e) {
            logger.error("Exception while trying to start http client", e);
            throw new RuntimeException("Exception while trying to start http client", e);
        }

        deviceState = null;
        proConIpClient = new ProConIpClient(httpClient, conInfo);
        pollingJob = scheduler.scheduleWithFixedDelay(this::pollState, 1, config.refreshInterval, TimeUnit.SECONDS);
        updateStatus(ThingStatus.UNKNOWN);
    }

    @Override
    public void dispose() {
        super.dispose();

        final ScheduledFuture<?> job = pollingJob;
        if (job != null) {
            job.cancel(true);
            pollingJob = null;
        }
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        try {
            if (command instanceof RefreshType) {
                refreshChannel(channelUID, getState());
            }
        } catch (ProConIpCommunicationException ce) {
            logger.warn("Could not communicate with ProCon.IP. Setting thing to 'OFFLINE'.");
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.COMMUNICATION_ERROR, ce.getMessage());
        }
    }

    private void refreshChannel(ChannelUID channelUID, @Nullable DeviceState deviceState) {
        if (deviceState == null) {
            return;
        }

        State state = null;
        switch (channelUID.getId()) {
            case ADC_1:
            case ADC_2:
            case ADC_3:
            case ADC_4:
            case ADC_5:
                int adcIdx = Integer.parseInt(channelUID.getId().substring(channelUID.getId().length() - 1));
                state = new DecimalType(Double.valueOf(deviceState.getAdcs()[adcIdx - 1]));
                break;
            case REDOX:
                state = new DecimalType(Double.valueOf(deviceState.getRedox()));
                break;
            case PH:
                state = new DecimalType(Double.valueOf(deviceState.getPh()));
                break;
            case TEMPERATURE_1:
            case TEMPERATURE_2:
            case TEMPERATURE_3:
            case TEMPERATURE_4:
            case TEMPERATURE_5:
            case TEMPERATURE_6:
            case TEMPERATURE_7:
            case TEMPERATURE_8:
                int tempIdx = Integer.parseInt(channelUID.getId().substring(channelUID.getId().length() - 1));
                state = new QuantityType<>(deviceState.getTemperatures()[tempIdx - 1], CELSIUS);
                break;
            case RELAIS_1:
            case RELAIS_2:
            case RELAIS_3:
            case RELAIS_4:
            case RELAIS_5:
            case RELAIS_6:
            case RELAIS_7:
            case RELAIS_8:
                int relaisIdx = Integer.parseInt(channelUID.getId().substring(channelUID.getId().length() - 1));
                state = deviceState.getRelais()[relaisIdx - 1] ? OnOffType.ON : OnOffType.OFF;
                relaisFilter.setRelaisState(channelUID.getId(), (OnOffType) state);
                break;
            case DIGITAL_INPUT_1:
            case DIGITAL_INPUT_2:
            case DIGITAL_INPUT_3:
            case DIGITAL_INPUT_4:
                int diIdx = Integer.parseInt(channelUID.getId().substring(channelUID.getId().length() - 1));
                state = new DecimalType(Double.valueOf(deviceState.getDigitalInputs()[diIdx - 1]));
                break;
            case EXTERNAL_RELAIS_1:
            case EXTERNAL_RELAIS_2:
            case EXTERNAL_RELAIS_3:
            case EXTERNAL_RELAIS_4:
            case EXTERNAL_RELAIS_5:
            case EXTERNAL_RELAIS_6:
            case EXTERNAL_RELAIS_7:
            case EXTERNAL_RELAIS_8:
                int extRelaisIdx = Integer.parseInt(channelUID.getId().substring(channelUID.getId().length() - 1));
                state = deviceState.getExternalRelais()[extRelaisIdx - 1] ? OnOffType.ON : OnOffType.OFF;
                break;
            case CHLORINE_REMAINING:
                state = new QuantityType<>(deviceState.getChlorineRemaining(), PERCENT);
                break;
            case CHLORINE_USAGE:
                state = new QuantityType<>(deviceState.getChlorineUsage(), MILLI(LITRE));
                break;
            case PH_MINUS_REMAINING:
                state = new QuantityType<>(deviceState.getPhMinusRemaining(), PERCENT);
                break;
            case PH_MINUS_USAGE:
                state = new QuantityType<>(deviceState.getPhMinusUsage(), MILLI(LITRE));
                break;
            case PH_PLUS_REMAINING:
                state = new QuantityType<>(deviceState.getPhPlusRemaining(), PERCENT);
                break;
            case PH_PLUS_USAGE:
                state = new QuantityType<>(deviceState.getPhPlusUsage(), MILLI(LITRE));
                break;
            default:
                logger.warn("Don't know how to handle channel with uid {}", channelUID);
                break;
        }
        if (state != null) {
            final Channel channel = getThing().getChannel(channelUID.getId());
            if (channel == null) {
                logger.error("Cannot find channel for {}", channelUID);
                return;
            }
            ProConIpChannelConfig config = channel.getConfiguration().as(ProConIpChannelConfig.class);
            if (config.relaisFilter != null) {
                State filteredState = relaisFilter.filterState(config.relaisFilter, state);
                updateState(channelUID, filteredState);
                return;
            }

            updateState(channelUID, state);
        }
    }

    /**
     * Gets the current state from the local cache. Only starts to poll if no cached data is available.
     */
    private @Nullable DeviceState getState() {
        synchronized (this) {
            if (deviceState == null) {
                pollState();
            }
            return deviceState;
        }
    }

    /**
     * Polls the current state from the device, stores it in the local state cache and updates the thing and the
     * channels.
     */
    private void pollState() {
        assert proConIpClient != null;
        logger.debug("Polling device");

        final DeviceState newDeviceState;
        try {
            newDeviceState = proConIpClient.readState();
        } catch (ProConIpCommunicationException ce) {
            logger.warn("Could not communicate with ProCon.IP. Setting thing to 'OFFLINE'.");
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.COMMUNICATION_ERROR, ce.getMessage());
            return;
        } catch (IllegalStateException ise) {
            logger.warn("Received invalid data from ProCon.IP: {}", ise.toString());
            return;
        }

        if (getThing().getStatus() == ThingStatus.OFFLINE) {
            logger.info("Setting thing back to 'ONLINE'");
        }
        updateStatus(ThingStatus.ONLINE);

        synchronized (this) {
            deviceState = newDeviceState;
        }

        // Update properties: Firmware and Uptime
        Map<String, String> properties = editProperties();
        properties.put(Thing.PROPERTY_FIRMWARE_VERSION, newDeviceState.getFirmwareVersion());
        properties.put(PROPERTY_UPTIME, uptimeToDays(newDeviceState.getUptime()));
        updateProperties(properties);

        // Update all channels
        for (Channel channel : getThing().getChannels()) {
            refreshChannel(channel.getUID(), newDeviceState);
        }
    }

    protected String uptimeToDays(int uptime) {
        long days = TimeUnit.SECONDS.toDays(uptime);
        long hours = TimeUnit.SECONDS.toHours(uptime) - (days * 24);
        long minutes = TimeUnit.SECONDS.toMinutes(uptime) - (TimeUnit.SECONDS.toHours(uptime) * 60);
        long seconds = TimeUnit.SECONDS.toSeconds(uptime) - (TimeUnit.SECONDS.toMinutes(uptime) * 60);
        String s = String.format("%dh %dm %ds", hours, minutes, seconds);
        return (days > 0) ? String.format("%dd ", days) + s : s;
    }
}
