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

import static org.openhab.binding.modbus.kostal.internal.ksem.ModbusConstants.*;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.openhab.binding.modbus.kostal.internal.ksem.dto.Energy0ModelBlock;
import org.openhab.binding.modbus.kostal.internal.ksem.dto.Energy1ModelBlock;
import org.openhab.binding.modbus.kostal.internal.parser.AbstractBaseParser;
import org.openhab.core.io.transport.modbus.ModbusRegisterArray;

/**
 * Parser for the Kostal Smart Energy Meter "Energy registers" data.
 *
 * @author Martin Renner - Initial contribution
 */
@NonNullByDefault
public class EnergyBlockParser extends AbstractBaseParser {

    public Energy0ModelBlock parseBlock0(ModbusRegisterArray raw) {
        final var block = new Energy0ModelBlock();

        block.activeEnergyPlus = extractUInt64(raw, 512 - ENERGY0_REG_START);
        block.activeEnergyMinus = extractUInt64(raw, 516 - ENERGY0_REG_START);
        block.reactiveEnergyPlus = extractUInt64(raw, 520 - ENERGY0_REG_START);
        block.reactiveEnergyMinus = extractUInt64(raw, 524 - ENERGY0_REG_START);
        block.apparentEnergyPlus = extractUInt64(raw, 544 - ENERGY0_REG_START);
        block.apparentEnergyMinus = extractUInt64(raw, 548 - ENERGY0_REG_START);

        block.activeEnergyPlusL1 = extractUInt64(raw, 592 - ENERGY0_REG_START);
        block.activeEnergyMinusL1 = extractUInt64(raw, 596 - ENERGY0_REG_START);
        block.reactiveEnergyPlusL1 = extractUInt64(raw, 600 - ENERGY0_REG_START);
        block.reactiveEnergyMinusL1 = extractUInt64(raw, 604 - ENERGY0_REG_START);
        block.apparentEnergyPlusL1 = extractUInt64(raw, 624 - ENERGY0_REG_START);
        block.apparentEnergyMinusL1 = extractUInt64(raw, 628 - ENERGY0_REG_START);

        return block;
    }

    public Energy1ModelBlock parseBlock1(ModbusRegisterArray raw) {
        final var block = new Energy1ModelBlock();

        block.activeEnergyPlusL2 = extractUInt64(raw, 672 - ENERGY1_REG_START);
        block.activeEnergyMinusL2 = extractUInt64(raw, 676 - ENERGY1_REG_START);
        block.reactiveEnergyPlusL2 = extractUInt64(raw, 680 - ENERGY1_REG_START);
        block.reactiveEnergyMinusL2 = extractUInt64(raw, 684 - ENERGY1_REG_START);
        block.apparentEnergyPlusL2 = extractUInt64(raw, 704 - ENERGY1_REG_START);
        block.apparentEnergyMinusL2 = extractUInt64(raw, 708 - ENERGY1_REG_START);

        block.activeEnergyPlusL3 = extractUInt64(raw, 752 - ENERGY1_REG_START);
        block.activeEnergyMinusL3 = extractUInt64(raw, 756 - ENERGY1_REG_START);
        block.reactiveEnergyPlusL3 = extractUInt64(raw, 760 - ENERGY1_REG_START);
        block.reactiveEnergyMinusL3 = extractUInt64(raw, 764 - ENERGY1_REG_START);
        block.apparentEnergyPlusL3 = extractUInt64(raw, 784 - ENERGY1_REG_START);
        block.apparentEnergyMinusL3 = extractUInt64(raw, 788 - ENERGY1_REG_START);

        return block;
    }
}
