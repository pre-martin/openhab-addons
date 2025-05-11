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

import static org.openhab.binding.modbus.kostal.internal.plenticore.ModbusConstants.DATA3_REG_START;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.openhab.binding.modbus.kostal.internal.parser.AbstractBaseParser;
import org.openhab.binding.modbus.kostal.internal.plenticore.dto.Data3ModelBlock;
import org.openhab.core.io.transport.modbus.ModbusRegisterArray;

/**
 * Parser for the Kostal Plenticore operating data (part "Data3")
 *
 * @author Martin Renner - Initial contribution
 */
@NonNullByDefault
public class Data3BlockParser extends AbstractBaseParser {

    public Data3ModelBlock parse(ModbusRegisterArray raw) {
        final var block = new Data3ModelBlock();

        block.totalYield = extractFloatSwap(raw, 320 - DATA3_REG_START);
        block.dailyYield = extractFloatSwap(raw, 322 - DATA3_REG_START);
        block.yearlyYield = extractFloatSwap(raw, 324 - DATA3_REG_START);
        block.monthlyYield = extractFloatSwap(raw, 326 - DATA3_REG_START);

        return block;
    }
}
