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

import static org.openhab.binding.modbus.kostal.internal.plenticore.ModbusConstants.DATA2_REG_START;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.openhab.binding.modbus.kostal.internal.parser.AbstractBaseParser;
import org.openhab.binding.modbus.kostal.internal.plenticore.dto.Data2ModelBlock;
import org.openhab.core.io.transport.modbus.ModbusRegisterArray;

/**
 * Parser for the Kostal Plenticore operating data (part "Data2")
 *
 * @author Martin Renner - Initial contribution
 */
@NonNullByDefault
public class Data2BlockParser extends AbstractBaseParser {

    public Data2ModelBlock parse(ModbusRegisterArray raw) {
        final var block = new Data2ModelBlock();

        block.cosPhiPowerMeter = extractFloatSwap(raw, 218 - DATA2_REG_START);
        block.frequencyPowerMeter = extractFloatSwap(raw, 220 - DATA2_REG_START);
        block.currentPhase1PowerMeter = extractFloatSwap(raw, 222 - DATA2_REG_START);
        block.activePowerPhase1PowerMeter = extractFloatSwap(raw, 224 - DATA2_REG_START);
        block.reactivePowerPhase1PowerMeter = extractFloatSwap(raw, 226 - DATA2_REG_START);
        block.apparentPowerPhase1PowerMeter = extractFloatSwap(raw, 228 - DATA2_REG_START);
        block.voltagePhase1PowerMeter = extractFloatSwap(raw, 230 - DATA2_REG_START);
        block.currentPhase2PowerMeter = extractFloatSwap(raw, 232 - DATA2_REG_START);
        block.activePowerPhase2PowerMeter = extractFloatSwap(raw, 234 - DATA2_REG_START);
        block.reactivePowerPhase2PowerMeter = extractFloatSwap(raw, 236 - DATA2_REG_START);
        block.apparentPowerPhase2PowerMeter = extractFloatSwap(raw, 238 - DATA2_REG_START);
        block.voltagePhase2PowerMeter = extractFloatSwap(raw, 240 - DATA2_REG_START);
        block.currentPhase3PowerMeter = extractFloatSwap(raw, 242 - DATA2_REG_START);
        block.activePowerPhase3PowerMeter = extractFloatSwap(raw, 244 - DATA2_REG_START);
        block.reactivePowerPhase3PowerMeter = extractFloatSwap(raw, 246 - DATA2_REG_START);
        block.apparentPowerPhase3PowerMeter = extractFloatSwap(raw, 248 - DATA2_REG_START);
        block.voltagePhase3PowerMeter = extractFloatSwap(raw, 250 - DATA2_REG_START);
        block.totalActivePowerPowerMeter = extractFloatSwap(raw, 252 - DATA2_REG_START);
        block.totalReactivePowerPowerMeter = extractFloatSwap(raw, 254 - DATA2_REG_START);
        block.totalApparentPowerPowerMeter = extractFloatSwap(raw, 256 - DATA2_REG_START);

        block.currentDc1 = extractFloatSwap(raw, 258 - DATA2_REG_START);
        block.powerDc1 = extractFloatSwap(raw, 260 - DATA2_REG_START);
        block.voltageDc1 = extractFloatSwap(raw, 266 - DATA2_REG_START);
        block.currentDc2 = extractFloatSwap(raw, 268 - DATA2_REG_START);
        block.powerDc2 = extractFloatSwap(raw, 270 - DATA2_REG_START);
        block.voltageDc2 = extractFloatSwap(raw, 276 - DATA2_REG_START);
        block.currentDc3 = extractFloatSwap(raw, 278 - DATA2_REG_START);
        block.powerDc3 = extractFloatSwap(raw, 280 - DATA2_REG_START);
        block.voltageDc3 = extractFloatSwap(raw, 286 - DATA2_REG_START);

        return block;
    }
}
