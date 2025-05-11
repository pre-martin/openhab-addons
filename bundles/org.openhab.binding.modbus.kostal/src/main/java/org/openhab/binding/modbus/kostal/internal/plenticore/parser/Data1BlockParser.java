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
package org.openhab.binding.modbus.kostal.internal.plenticore.parser;

import static org.openhab.binding.modbus.kostal.internal.plenticore.ModbusConstants.DATA1_REG_START;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.openhab.binding.modbus.kostal.internal.parser.AbstractBaseParser;
import org.openhab.binding.modbus.kostal.internal.plenticore.dto.Data1ModelBlock;
import org.openhab.core.io.transport.modbus.ModbusRegisterArray;

/**
 * Parser for the Kostal Plenticore operating data (part "Data1")
 *
 * @author Martin Renner - Initial contribution
 */
@NonNullByDefault
public class Data1BlockParser extends AbstractBaseParser {

    public Data1ModelBlock parse(ModbusRegisterArray raw) {
        final var block = new Data1ModelBlock();

        block.temperatureOfControllerPcb = extractFloatSwap(raw, 98 - DATA1_REG_START);
        block.totalDcPower = extractFloatSwap(raw, 100 - DATA1_REG_START);
        block.stateOfEnergyManager = extractUInt32Swap(raw, 104 - DATA1_REG_START);

        block.homeOwnConsumptionFromBattery = extractFloatSwap(raw, 106 - DATA1_REG_START);
        block.homeOwnConsumptionFromGrid = extractFloatSwap(raw, 108 - DATA1_REG_START);
        block.totalHomeConsumptionBattery = extractFloatSwap(raw, 110 - DATA1_REG_START);
        block.totalHomeConsumptionGrid = extractFloatSwap(raw, 112 - DATA1_REG_START);
        block.totalHomeConsumptionPv = extractFloatSwap(raw, 114 - DATA1_REG_START);
        block.homeOwnConsumptionFromPv = extractFloatSwap(raw, 116 - DATA1_REG_START);
        block.totalHomeConsumption = extractFloatSwap(raw, 118 - DATA1_REG_START);
        block.totalHomeConsumptionRate = extractFloatSwap(raw, 124 - DATA1_REG_START);

        block.actualCosPhi = extractFloatSwap(raw, 150 - DATA1_REG_START);
        block.gridFrequency = extractFloatSwap(raw, 152 - DATA1_REG_START);
        block.currentPhase1 = extractFloatSwap(raw, 154 - DATA1_REG_START);
        block.activePowerPhase1 = extractFloatSwap(raw, 156 - DATA1_REG_START);
        block.voltagePhase1 = extractFloatSwap(raw, 158 - DATA1_REG_START);
        block.currentPhase2 = extractFloatSwap(raw, 160 - DATA1_REG_START);
        block.activePowerPhase2 = extractFloatSwap(raw, 162 - DATA1_REG_START);
        block.voltagePhase2 = extractFloatSwap(raw, 164 - DATA1_REG_START);
        block.currentPhase3 = extractFloatSwap(raw, 166 - DATA1_REG_START);
        block.activePowerPhase3 = extractFloatSwap(raw, 168 - DATA1_REG_START);
        block.voltagePhase3 = extractFloatSwap(raw, 170 - DATA1_REG_START);
        block.totalAcActivePower = extractFloatSwap(raw, 172 - DATA1_REG_START);
        block.totalAcReactivePower = extractFloatSwap(raw, 174 - DATA1_REG_START);
        block.totalAcApparentPower = extractFloatSwap(raw, 178 - DATA1_REG_START);

        block.batteryChargeCurrent = extractFloatSwap(raw, 190 - DATA1_REG_START);
        block.numberOfBatteryCycles = extractFloatSwap(raw, 194 - DATA1_REG_START);
        block.actualBatteryCharge = extractFloatSwap(raw, 200 - DATA1_REG_START);
        block.pssbFuseState = extractFloatSwap(raw, 202 - DATA1_REG_START);
        block.batteryReadyFlag = extractFloatSwap(raw, 208 - DATA1_REG_START);
        block.actualStateOfCharge = extractFloatSwap(raw, 210 - DATA1_REG_START);
        block.batteryTemperature = extractFloatSwap(raw, 214 - DATA1_REG_START);
        block.batteryVoltage = extractFloatSwap(raw, 216 - DATA1_REG_START);

        return block;
    }
}
