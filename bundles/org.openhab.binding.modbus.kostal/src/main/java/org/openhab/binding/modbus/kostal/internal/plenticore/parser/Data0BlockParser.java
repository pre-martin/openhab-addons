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

import static org.openhab.binding.modbus.kostal.internal.plenticore.ModbusConstants.DATA0_REG_START;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.openhab.binding.modbus.kostal.internal.parser.AbstractBaseParser;
import org.openhab.binding.modbus.kostal.internal.plenticore.dto.Data0ModelBlock;
import org.openhab.core.io.transport.modbus.ModbusRegisterArray;

/**
 * Parser for the Kostal Plenticore operating data (part "Data0")
 *
 * @author Martin Renner - Initial contribution
 */
@NonNullByDefault
public class Data0BlockParser extends AbstractBaseParser {

    public Data0ModelBlock parse(ModbusRegisterArray raw) {
        final var block = new Data0ModelBlock();

        block.inverterArticleNumber = extractString(raw, 6 - DATA0_REG_START, 8);
        block.inverterSerialNumber = extractString(raw, 14 - DATA0_REG_START, 8);
        block.numberOfAcPhases = extractUInt16(raw, 32 - DATA0_REG_START);
        block.numberOfPvStrings = extractUInt16(raw, 34 - DATA0_REG_START);
        block.hardwareVersion = extractUInt16(raw, 36 - DATA0_REG_START);
        block.softwareVersionMainController = extractString(raw, 38 - DATA0_REG_START, 8);
        block.softwareVersionIoController = extractString(raw, 46 - DATA0_REG_START, 8);
        block.inverterState = extractUInt32Swap(raw, 56 - DATA0_REG_START);

        return block;
    }
}
