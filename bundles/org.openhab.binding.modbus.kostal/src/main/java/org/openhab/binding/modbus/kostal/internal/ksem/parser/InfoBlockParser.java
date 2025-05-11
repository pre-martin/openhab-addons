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

import static org.openhab.binding.modbus.kostal.internal.ksem.ModbusConstants.INFO_REG_START;

import org.openhab.binding.modbus.kostal.internal.ksem.dto.InfoModelBlock;
import org.openhab.binding.modbus.kostal.internal.parser.AbstractBaseParser;
import org.openhab.core.io.transport.modbus.ModbusRegisterArray;

/**
 * Parser for the Kostal Smart Energy Meter "KSEM/RM PnP register" data.
 *
 * @author Martin Renner - Initial contribution
 */
public class InfoBlockParser extends AbstractBaseParser {

    private final InfoModelBlock block = new InfoModelBlock();

    public InfoModelBlock parse(ModbusRegisterArray raw) {

        block.manufacturerId = extractUInt16(raw, 8192 - INFO_REG_START);
        block.deviceId = extractUInt16(raw, 8192 - INFO_REG_START);
        block.productVersion = extractUInt16(raw, 8194 - INFO_REG_START);
        block.firmwareVersion = extractUInt16(raw, 8195 - INFO_REG_START);
        block.vendorName = extractString(raw, 8196 - INFO_REG_START, 16);
        block.productName = extractString(raw, 8212 - INFO_REG_START, 16);
        block.serialNumber = extractString(raw, 8228 - INFO_REG_START, 16);
        block.measuringInterval = extractUInt16(raw, 8244 - INFO_REG_START);

        return block;
    }
}
