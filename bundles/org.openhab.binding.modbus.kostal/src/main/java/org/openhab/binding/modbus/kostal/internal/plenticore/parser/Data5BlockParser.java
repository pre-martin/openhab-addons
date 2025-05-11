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

import static org.openhab.binding.modbus.kostal.internal.plenticore.ModbusConstants.DATA5_REG_START;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.openhab.binding.modbus.kostal.internal.parser.AbstractBaseParser;
import org.openhab.binding.modbus.kostal.internal.plenticore.dto.Data5ModelBlock;
import org.openhab.core.io.transport.modbus.ModbusRegisterArray;

/**
 * Parser for the Kostal Plenticore operating data (part "Data5")
 *
 * @author Martin Renner - Initial contribution
 */
@NonNullByDefault
public class Data5BlockParser extends AbstractBaseParser {

    public Data5ModelBlock parse(ModbusRegisterArray raw) {
        final var block = new Data5ModelBlock();

        block.totalDcChargeEnergy = extractFloatSwap(raw, 1046 - DATA5_REG_START);
        block.totalDcDischargeEnergy = extractFloatSwap(raw, 1048 - DATA5_REG_START);
        block.totalAcChargeEnergy = extractFloatSwap(raw, 1050 - DATA5_REG_START);
        block.totalAcDischargeEnergy = extractFloatSwap(raw, 1052 - DATA5_REG_START);
        block.totalAcChargeEnergyGrid = extractFloatSwap(raw, 1054 - DATA5_REG_START);

        block.totalDcPvEnergy = extractFloatSwap(raw, 1056 - DATA5_REG_START);
        block.totalDcEnergyFromPv1 = extractFloatSwap(raw, 1058 - DATA5_REG_START);
        block.totalDcEnergyFromPv2 = extractFloatSwap(raw, 1060 - DATA5_REG_START);
        block.totalDcEnergyFromPv3 = extractFloatSwap(raw, 1062 - DATA5_REG_START);
        block.totalEnergyAcSideToGrid = extractFloatSwap(raw, 1064 - DATA5_REG_START);
        block.totalDcPowerSum = extractFloatSwap(raw, 1066 - DATA5_REG_START);

        return block;
    }
}
