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
package org.openhab.binding.modbus.kostal.internal.parser;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.openhab.core.io.transport.modbus.ModbusBitUtilities;
import org.openhab.core.io.transport.modbus.ModbusRegisterArray;

/**
 * Base class for parsers with some helper methods
 *
 * @author Martin Renner - Initial contribution
 */
@NonNullByDefault
public abstract class AbstractBaseParser {

    protected String extractString(ModbusRegisterArray registers, int registerIndex, int registerCount) {
        return ModbusBitUtilities.extractStringFromBytes(registers.getBytes(), registerIndex * 2, registerCount * 2,
                StandardCharsets.UTF_8);
    }

    protected int extractUInt16(ModbusRegisterArray registers, int registerIndex) {
        return ModbusBitUtilities.extractUInt16(registers.getBytes(), registerIndex * 2);
    }

    protected int extractSInt16(ModbusRegisterArray registers, int registerIndex) {
        return ModbusBitUtilities.extractSInt16(registers.getBytes(), registerIndex * 2);
    }

    protected long extractUInt32(ModbusRegisterArray registers, int registerIndex) {
        return ModbusBitUtilities.extractUInt32(registers.getBytes(), registerIndex * 2);
    }

    protected long extractUInt32Swap(ModbusRegisterArray registers, int registerIndex) {
        return ModbusBitUtilities.extractUInt32Swap(registers.getBytes(), registerIndex * 2);
    }

    protected int extractSInt32(ModbusRegisterArray registers, int registerIndex) {
        return ModbusBitUtilities.extractSInt32(registers.getBytes(), registerIndex * 2);
    }

    protected BigInteger extractUInt64(ModbusRegisterArray registers, int registerIndex) {
        return ModbusBitUtilities.extractUInt64(registers.getBytes(), registerIndex * 2);
    }

    protected float extractFloat(ModbusRegisterArray registers, int registerIndex) {
        return ModbusBitUtilities.extractFloat32(registers.getBytes(), registerIndex * 2);
    }

    protected float extractFloatSwap(ModbusRegisterArray registers, int registerIndex) {
        return ModbusBitUtilities.extractFloat32Swap(registers.getBytes(), registerIndex * 2);
    }
}
