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

import static org.openhab.binding.modbus.kostal.internal.plenticore.ModbusConstants.DATA4_REG_START;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.openhab.binding.modbus.kostal.internal.parser.AbstractBaseParser;
import org.openhab.binding.modbus.kostal.internal.plenticore.dto.Data4ModelBlock;
import org.openhab.core.io.transport.modbus.ModbusRegisterArray;

/**
 * Parser for the Kostal Plenticore operating data (part "Data4")
 *
 * @author Martin Renner - Initial contribution
 */
@NonNullByDefault
public class Data4BlockParser extends AbstractBaseParser {

    public Data4ModelBlock parse(ModbusRegisterArray raw) {
        final var block = new Data4ModelBlock();

        block.batteryGrossCapacity = extractUInt32Swap(raw, 512 - DATA4_REG_START);
        block.batteryActualSoc = extractUInt16(raw, 514 - DATA4_REG_START);
        block.batteryManufacturer = extractString(raw, 517 - DATA4_REG_START, 8);
        block.batteryModelId = extractUInt32Swap(raw, 525 - DATA4_REG_START);
        block.batterySerialNumber = extractUInt32Swap(raw, 527 - DATA4_REG_START);
        block.workCapacity = extractUInt32Swap(raw, 529 - DATA4_REG_START);

        block.inverterMaxPower = extractUInt16(raw, 531 - DATA4_REG_START);
        block.inverterMaxPowerScaleFactor = extractSInt16(raw, 532 - DATA4_REG_START);
        block.activePowerSetpoint = extractUInt16(raw, 533 - DATA4_REG_START);
        block.inverterManufacturer = extractString(raw, 535 - DATA4_REG_START, 16);
        block.inverterSerialNumber = extractString(raw, 559 - DATA4_REG_START, 16);
        block.inverterGenerationPower = extractSInt16(raw, 575 - DATA4_REG_START);
        block.powerScaleFactor = extractSInt16(raw, 576 - DATA4_REG_START);
        block.generationEnergy = extractUInt32Swap(raw, 577 - DATA4_REG_START);
        block.energyScaleFactor = extractSInt16(raw, 580 - DATA4_REG_START);

        block.actualBatteryChargePower = extractSInt16(raw, 582 - DATA4_REG_START);

        block.reactivePowerSetpoint = extractSInt16(raw, 583 - DATA4_REG_START);
        block.deltaCosPhiSetpoint = extractSInt16(raw, 585 - DATA4_REG_START);

        block.batteryFirmware = extractUInt32Swap(raw, 586 - DATA4_REG_START);
        block.batteryType = extractUInt16(raw, 588 - DATA4_REG_START);

        return block;
    }
}
