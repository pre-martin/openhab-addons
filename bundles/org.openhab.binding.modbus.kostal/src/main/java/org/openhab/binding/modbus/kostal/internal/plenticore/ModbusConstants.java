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
package org.openhab.binding.modbus.kostal.internal.plenticore;

/**
 * Modbus related configuration of the binding. See "KOSTAL Interface description MODBUS (TCP) & SunSpec", pages 17ff.
 * <p />
 * Maximum amount of registers for "Read Holding Registers (0x03)" is 125.
 */
public class ModbusConstants {

    // We have to split the whole "Operating data" registers into multiple chunks, because we can only request
    // 125 registers with one call. As there is almost no logical separation of the existing registers,
    // we call them "data0", "data1", "data2", "data3" and "data4".

    /** Device information */
    public static final int DATA0_REG_START = 0;
    public static final int DATA0_REG_SIZE = 57 - DATA0_REG_START + 1;

    /** Consumption, AC phases, Battery part 1 */
    public static final int DATA1_REG_START = 98;
    public static final int DATA1_REG_SIZE = 217 - DATA1_REG_START + 1;

    /** PowerMeter, DC, yield */
    public static final int DATA2_REG_START = 218;
    public static final int DATA2_REG_SIZE = 287 - DATA2_REG_START + 1;

    /** Yield */
    public static final int DATA3_REG_START = 320;
    public static final int DATA3_REG_SIZE = 327 - DATA3_REG_START + 1;

    /** Battery part 2, Inverter data */
    public static final int DATA4_REG_START = 512;
    public static final int DATA4_REG_SIZE = 588 - DATA4_REG_START + 1;

    /** DC charge, AC charge, DC+AC energy */
    public static final int DATA5_REG_START = 1046;
    public static final int DATA5_REG_SIZE = 1067 - DATA5_REG_START + 1;
}
