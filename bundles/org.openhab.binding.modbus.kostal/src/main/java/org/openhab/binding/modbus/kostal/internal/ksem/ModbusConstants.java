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
package org.openhab.binding.modbus.kostal.internal.ksem;

/**
 * Modbus related configuration of the binding. See "KOSTAL Smart Energy Meter Interface description MODBUS".
 * <p />
 * Maximum amount of registers for "Read Holding Registers (0x03)" is 123.
 */
public class ModbusConstants {

    // We have to split the whole registers into multiple chunks, because we can only request
    // 123 registers with one call.

    /** Power registers - Block 0 */
    public static final int POWER0_REG_START = 0;
    public static final int POWER0_REG_SIZE = 27 - POWER0_REG_START + 1;

    /** Power registers - Block 1 */
    public static final int POWER1_REG_START = 40;
    public static final int POWER1_REG_SIZE = 147 - POWER1_REG_START + 1;

    /** Energy registers - Block 0 */
    public static final int ENERGY0_REG_START = 512;
    public static final int ENERGY0_REG_SIZE = 631 - ENERGY0_REG_START + 1;

    /** Energy registers - Block 1 */
    public static final int ENERGY1_REG_START = 672;
    public static final int ENERGY1_REG_SIZE = 791 - ENERGY1_REG_START + 1;

    /** KSEM/RM PnP register */
    public static final int INFO_REG_START = 8192;
    public static final int INFO_REG_SIZE = 8249 - INFO_REG_START + 1;
}
