/**
 * Copyright (c) 2010-2024 Contributors to the openHAB project
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
package org.openhab.binding.modbus.kostal.internal.ksem.parser;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.openhab.binding.modbus.kostal.internal.ksem.ModbusConstants;
import org.openhab.binding.modbus.kostal.internal.ksem.dto.Power0ModelBlock;
import org.openhab.binding.modbus.kostal.internal.ksem.dto.Power1ModelBlock;
import org.openhab.binding.modbus.kostal.internal.parser.AbstractBaseParser;
import org.openhab.core.io.transport.modbus.ModbusRegisterArray;

/**
 * Parser for the Kostal Smart Energy Meter "Power registers" data.
 *
 * @author Martin Renner - Initial contribution
 */
@NonNullByDefault
public class PowerBlockParser extends AbstractBaseParser {

    public Power0ModelBlock parseBlock0(ModbusRegisterArray raw) {
        final var block = new Power0ModelBlock();

        block.activePowerPlus = extractUInt32(raw, 0 - ModbusConstants.POWER0_REG_START);
        block.activePowerMinus = extractUInt32(raw, 2 - ModbusConstants.POWER0_REG_START);
        block.reactivePowerPlus = extractUInt32(raw, 4 - ModbusConstants.POWER0_REG_START);
        block.reactivePowerMinus = extractUInt32(raw, 6 - ModbusConstants.POWER0_REG_START);
        block.apparentPowerPlus = extractUInt32(raw, 16 - ModbusConstants.POWER0_REG_START);
        block.apparentPowerMinus = extractUInt32(raw, 18 - ModbusConstants.POWER0_REG_START);
        block.powerFactor = extractSInt32(raw, 24 - ModbusConstants.POWER0_REG_START);
        block.supplyFrequency = extractUInt32(raw, 26 - ModbusConstants.POWER0_REG_START);

        return block;
    }

    public Power1ModelBlock parseBlock1(ModbusRegisterArray raw) {
        final var block = new Power1ModelBlock();

        block.activePowerPlusL1 = extractUInt32(raw, 40 - ModbusConstants.POWER1_REG_START);
        block.activePowerMinusL1 = extractUInt32(raw, 42 - ModbusConstants.POWER1_REG_START);
        block.reactivePowerPlusL1 = extractUInt32(raw, 44 - ModbusConstants.POWER1_REG_START);
        block.reactivePowerMinusL1 = extractUInt32(raw, 46 - ModbusConstants.POWER1_REG_START);
        block.apparentPowerPlusL1 = extractUInt32(raw, 56 - ModbusConstants.POWER1_REG_START);
        block.apparentPowerMinusL1 = extractUInt32(raw, 58 - ModbusConstants.POWER1_REG_START);
        block.currentL1 = extractUInt32(raw, 60 - ModbusConstants.POWER1_REG_START);
        block.voltageL1 = extractUInt32(raw, 62 - ModbusConstants.POWER1_REG_START);
        block.powerFactorL1 = extractSInt32(raw, 64 - ModbusConstants.POWER1_REG_START);

        block.activePowerPlusL2 = extractUInt32(raw, 80 - ModbusConstants.POWER1_REG_START);
        block.activePowerMinusL2 = extractUInt32(raw, 82 - ModbusConstants.POWER1_REG_START);
        block.reactivePowerPlusL2 = extractUInt32(raw, 84 - ModbusConstants.POWER1_REG_START);
        block.reactivePowerMinusL2 = extractUInt32(raw, 86 - ModbusConstants.POWER1_REG_START);
        block.apparentPowerPlusL2 = extractUInt32(raw, 96 - ModbusConstants.POWER1_REG_START);
        block.apparentPowerMinusL2 = extractUInt32(raw, 98 - ModbusConstants.POWER1_REG_START);
        block.currentL2 = extractUInt32(raw, 100 - ModbusConstants.POWER1_REG_START);
        block.voltageL2 = extractUInt32(raw, 102 - ModbusConstants.POWER1_REG_START);
        block.powerFactorL2 = extractSInt32(raw, 104 - ModbusConstants.POWER1_REG_START);

        block.activePowerPlusL3 = extractUInt32(raw, 120 - ModbusConstants.POWER1_REG_START);
        block.activePowerMinusL3 = extractUInt32(raw, 122 - ModbusConstants.POWER1_REG_START);
        block.reactivePowerPlusL3 = extractUInt32(raw, 124 - ModbusConstants.POWER1_REG_START);
        block.reactivePowerMinusL3 = extractUInt32(raw, 126 - ModbusConstants.POWER1_REG_START);
        block.apparentPowerPlusL3 = extractUInt32(raw, 136 - ModbusConstants.POWER1_REG_START);
        block.apparentPowerMinusL3 = extractUInt32(raw, 138 - ModbusConstants.POWER1_REG_START);
        block.currentL3 = extractUInt32(raw, 140 - ModbusConstants.POWER1_REG_START);
        block.voltageL3 = extractUInt32(raw, 142 - ModbusConstants.POWER1_REG_START);
        block.powerFactorL3 = extractSInt32(raw, 144 - ModbusConstants.POWER1_REG_START);

        block.minimumActivePowerPlus = extractUInt32(raw, 146 - ModbusConstants.POWER1_REG_START);

        return block;
    }
}
