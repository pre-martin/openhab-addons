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
package org.openhab.binding.modbus.sunspec.internal.parser;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.openhab.binding.modbus.sunspec.internal.SunSpecExConstants;
import org.openhab.binding.modbus.sunspec.internal.dto.MpptModelBlock;
import org.openhab.core.io.transport.modbus.ModbusBitUtilities;
import org.openhab.core.io.transport.modbus.ModbusRegisterArray;

/**
 * Parses MPPT modbus data into a MpptModelBlock
 *
 * @author Martin Renner - Initial contribution
 */
@NonNullByDefault
public class MpptModelParser extends AbstractBaseParser implements SunspecParser<MpptModelBlock> {

    @Override
    public MpptModelBlock parse(ModbusRegisterArray raw) {
        MpptModelBlock block = new MpptModelBlock();

        block.modelId = extractUInt16(raw, 0, SunSpecExConstants.MPPT);
        block.length = extractUInt16(raw, 1, raw.size());
        block.currentSF = extractSunSSF(raw, 2);
        block.voltageSF = extractSunSSF(raw, 3);
        block.powerSF = extractSunSSF(raw, 4);
        block.energySF = extractSunSSF(raw, 5);
        block.events = extractUInt32(raw, 6, 0);
        int count = extractUInt16(raw, 8, 0);
        block.timestampPeriod = extractUInt16(raw, 9, 0);

        block.modules = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            var offset = 10 + i * 20;
            MpptModelBlock.MpptModuleBlock moduleBlock = new MpptModelBlock.MpptModuleBlock();
            moduleBlock.moduleId = extractUInt16(raw, offset, 0);
            moduleBlock.moduleIdString = ModbusBitUtilities.extractStringFromRegisters(raw, offset + 1, 8, StandardCharsets.UTF_8);
            moduleBlock.current = extractUInt16(raw, offset + 9, 0);
            moduleBlock.voltage = extractUInt16(raw, offset + 10, 0);
            moduleBlock.power = extractUInt16(raw, offset + 11, 0);
            moduleBlock.lifetimeEnergy = extractAcc32(raw, offset + 12, 0);
            moduleBlock.timestamp = extractUInt32(raw, offset + 14, 0);
            moduleBlock.temperature = extractInt16(raw, offset + 16, (short) 0);
            moduleBlock.operatingState = extractUInt16(raw, offset + 17, 0);
            moduleBlock.events = extractUInt32(raw, offset + 18, 0);
            block.modules.add(moduleBlock);
        }

        return block;
    }
}
