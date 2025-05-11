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

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * The {@link ThingConstants} class defines common constants, which are used across the whole thing.
 *
 * @author Martin Renner - Initial contribution
 */
@NonNullByDefault
public class ThingConstants {

    // Channels for power registers
    public static final String GROUP_POWER = "power";
    public static final String GROUP_POWER_L1 = "powerL1";
    public static final String GROUP_POWER_L2 = "powerL2";
    public static final String GROUP_POWER_L3 = "powerL3";
    public static final String ACTIVE_POWER_PLUS = "activePowerPlus";
    public static final String ACTIVE_POWER_MINUS = "activePowerMinus";
    public static final String REACTIVE_POWER_PLUS = "reactivePowerPlus";
    public static final String REACTIVE_POWER_MINUS = "reactivePowerMinus";
    public static final String APPARENT_POWER_PLUS = "apparentPowerPlus";
    public static final String APPARENT_POWER_MINUS = "apparentPowerMinus";
    public static final String POWER_FACTOR = "powerFactor";
    public static final String SUPPLY_FREQUENCY = "supplyFrequency";
    public static final String CURRENT = "current";
    public static final String VOLTAGE = "voltage";

    // Channels for energy registers
    public static final String GROUP_ENERGY = "energy";
    public static final String GROUP_ENERGY_L1 = "energyL1";
    public static final String GROUP_ENERGY_L2 = "energyL2";
    public static final String GROUP_ENERGY_L3 = "energyL3";
    public static final String ACTIVE_ENERGY_PLUS = "activeEnergyPlus";
    public static final String ACTIVE_ENERGY_MINUS = "activeEnergyMinus";
    public static final String REACTIVE_ENERGY_PLUS = "reactiveEnergyPlus";
    public static final String REACTIVE_ENERGY_MINUS = "reactiveEnergyMinus";
    public static final String APPARENT_ENERGY_PLUS = "apparentEnergyPlus";
    public static final String APPARENT_ENERGY_MINUS = "apparentEnergyMinus";

    // Properties
    public static final String PROP_PRODUCT_VERSION = "productVersion";
    public static final String PROP_FIRMWARE_VERSION = "firmwareVersion";
    public static final String PROP_VENDOR_NAME = "vendorName";
    public static final String PROP_PRODUCT_NAME = "productName";
    public static final String PROP_SERIAL_NUMBER = "serialNumber";
    public static final String PROP_MEASURING_INTERVAL = "measuringInterval";
}
