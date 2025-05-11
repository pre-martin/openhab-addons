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
package org.openhab.binding.modbus.kostal.internal.ksem.handler;

import static org.openhab.binding.modbus.kostal.internal.ksem.ThingConstants.*;

import org.openhab.core.thing.ChannelUID;
import org.openhab.core.thing.ThingUID;

/**
 * Utility class to bundle {@link ChannelUID ChannelUIDs} of the binding. Lots of boilerplate code that
 * should not pollute the handler.
 *
 * @author Martin Renner - Initial contribution
 */
class ChannelHolder {

    final ChannelUID activePowerPlus;
    final ChannelUID activePowerMinus;
    final ChannelUID reactivePowerPlus;
    final ChannelUID reactivePowerMinus;
    final ChannelUID apparentPowerPlus;
    final ChannelUID apparentPowerMinus;
    final ChannelUID powerFactor;
    final ChannelUID supplyFrequency;

    final ChannelUID activePowerPlusL1;
    final ChannelUID activePowerMinusL1;
    final ChannelUID reactivePowerPlusL1;
    final ChannelUID reactivePowerMinusL1;
    final ChannelUID apparentPowerPlusL1;
    final ChannelUID apparentPowerMinusL1;
    final ChannelUID currentL1;
    final ChannelUID voltageL1;
    final ChannelUID powerFactorL1;

    final ChannelUID activePowerPlusL2;
    final ChannelUID activePowerMinusL2;
    final ChannelUID reactivePowerPlusL2;
    final ChannelUID reactivePowerMinusL2;
    final ChannelUID apparentPowerPlusL2;
    final ChannelUID apparentPowerMinusL2;
    final ChannelUID currentL2;
    final ChannelUID voltageL2;
    final ChannelUID powerFactorL2;

    final ChannelUID activePowerPlusL3;
    final ChannelUID activePowerMinusL3;
    final ChannelUID reactivePowerPlusL3;
    final ChannelUID reactivePowerMinusL3;
    final ChannelUID apparentPowerPlusL3;
    final ChannelUID apparentPowerMinusL3;
    final ChannelUID currentL3;
    final ChannelUID voltageL3;
    final ChannelUID powerFactorL3;

    final ChannelUID activeEnergyPlus;
    final ChannelUID activeEnergyMinus;
    final ChannelUID reactiveEnergyPlus;
    final ChannelUID reactiveEnergyMinus;
    final ChannelUID apparentEnergyPlus;
    final ChannelUID apparentEnergyMinus;

    final ChannelUID activeEnergyPlusL1;
    final ChannelUID activeEnergyMinusL1;
    final ChannelUID reactiveEnergyPlusL1;
    final ChannelUID reactiveEnergyMinusL1;
    final ChannelUID apparentEnergyPlusL1;
    final ChannelUID apparentEnergyMinusL1;

    final ChannelUID activeEnergyPlusL2;
    final ChannelUID activeEnergyMinusL2;
    final ChannelUID reactiveEnergyPlusL2;
    final ChannelUID reactiveEnergyMinusL2;
    final ChannelUID apparentEnergyPlusL2;
    final ChannelUID apparentEnergyMinusL2;

    final ChannelUID activeEnergyPlusL3;
    final ChannelUID activeEnergyMinusL3;
    final ChannelUID reactiveEnergyPlusL3;
    final ChannelUID reactiveEnergyMinusL3;
    final ChannelUID apparentEnergyPlusL3;
    final ChannelUID apparentEnergyMinusL3;

    ChannelHolder(ThingUID thingUID) {
        // Channels for power registers
        activePowerPlus = new ChannelUID(thingUID, GROUP_POWER, ACTIVE_POWER_PLUS);
        activePowerMinus = new ChannelUID(thingUID, GROUP_POWER, ACTIVE_POWER_MINUS);
        reactivePowerPlus = new ChannelUID(thingUID, GROUP_POWER, REACTIVE_POWER_PLUS);
        reactivePowerMinus = new ChannelUID(thingUID, GROUP_POWER, REACTIVE_POWER_MINUS);
        apparentPowerPlus = new ChannelUID(thingUID, GROUP_POWER, APPARENT_POWER_PLUS);
        apparentPowerMinus = new ChannelUID(thingUID, GROUP_POWER, APPARENT_POWER_MINUS);
        powerFactor = new ChannelUID(thingUID, GROUP_POWER, POWER_FACTOR);
        supplyFrequency = new ChannelUID(thingUID, GROUP_POWER, SUPPLY_FREQUENCY);

        // Channels for power L1 registers
        activePowerPlusL1 = new ChannelUID(thingUID, GROUP_POWER_L1, ACTIVE_POWER_PLUS);
        activePowerMinusL1 = new ChannelUID(thingUID, GROUP_POWER_L1, ACTIVE_POWER_MINUS);
        reactivePowerPlusL1 = new ChannelUID(thingUID, GROUP_POWER_L1, REACTIVE_POWER_PLUS);
        reactivePowerMinusL1 = new ChannelUID(thingUID, GROUP_POWER_L1, REACTIVE_POWER_MINUS);
        apparentPowerPlusL1 = new ChannelUID(thingUID, GROUP_POWER_L1, APPARENT_POWER_PLUS);
        apparentPowerMinusL1 = new ChannelUID(thingUID, GROUP_POWER_L1, APPARENT_POWER_MINUS);
        currentL1 = new ChannelUID(thingUID, GROUP_POWER_L1, CURRENT);
        voltageL1 = new ChannelUID(thingUID, GROUP_POWER_L1, VOLTAGE);
        powerFactorL1 = new ChannelUID(thingUID, GROUP_POWER_L1, POWER_FACTOR);

        // Channels for power L2 registers
        activePowerPlusL2 = new ChannelUID(thingUID, GROUP_POWER_L2, ACTIVE_POWER_PLUS);
        activePowerMinusL2 = new ChannelUID(thingUID, GROUP_POWER_L2, ACTIVE_POWER_MINUS);
        reactivePowerPlusL2 = new ChannelUID(thingUID, GROUP_POWER_L2, REACTIVE_POWER_PLUS);
        reactivePowerMinusL2 = new ChannelUID(thingUID, GROUP_POWER_L2, REACTIVE_POWER_MINUS);
        apparentPowerPlusL2 = new ChannelUID(thingUID, GROUP_POWER_L2, APPARENT_POWER_PLUS);
        apparentPowerMinusL2 = new ChannelUID(thingUID, GROUP_POWER_L2, APPARENT_POWER_MINUS);
        currentL2 = new ChannelUID(thingUID, GROUP_POWER_L2, CURRENT);
        voltageL2 = new ChannelUID(thingUID, GROUP_POWER_L2, VOLTAGE);
        powerFactorL2 = new ChannelUID(thingUID, GROUP_POWER_L2, POWER_FACTOR);

        // Channels for power L3 registers
        activePowerPlusL3 = new ChannelUID(thingUID, GROUP_POWER_L3, ACTIVE_POWER_PLUS);
        activePowerMinusL3 = new ChannelUID(thingUID, GROUP_POWER_L3, ACTIVE_POWER_MINUS);
        reactivePowerPlusL3 = new ChannelUID(thingUID, GROUP_POWER_L3, REACTIVE_POWER_PLUS);
        reactivePowerMinusL3 = new ChannelUID(thingUID, GROUP_POWER_L3, REACTIVE_POWER_MINUS);
        apparentPowerPlusL3 = new ChannelUID(thingUID, GROUP_POWER_L3, APPARENT_POWER_PLUS);
        apparentPowerMinusL3 = new ChannelUID(thingUID, GROUP_POWER_L3, APPARENT_POWER_MINUS);
        currentL3 = new ChannelUID(thingUID, GROUP_POWER_L3, CURRENT);
        voltageL3 = new ChannelUID(thingUID, GROUP_POWER_L3, VOLTAGE);
        powerFactorL3 = new ChannelUID(thingUID, GROUP_POWER_L3, POWER_FACTOR);

        // Channels for energy registers
        activeEnergyPlus = new ChannelUID(thingUID, GROUP_ENERGY, ACTIVE_ENERGY_PLUS);
        activeEnergyMinus = new ChannelUID(thingUID, GROUP_ENERGY, ACTIVE_ENERGY_MINUS);
        reactiveEnergyPlus = new ChannelUID(thingUID, GROUP_ENERGY, REACTIVE_ENERGY_PLUS);
        reactiveEnergyMinus = new ChannelUID(thingUID, GROUP_ENERGY, REACTIVE_ENERGY_MINUS);
        apparentEnergyPlus = new ChannelUID(thingUID, GROUP_ENERGY, APPARENT_ENERGY_PLUS);
        apparentEnergyMinus = new ChannelUID(thingUID, GROUP_ENERGY, APPARENT_ENERGY_MINUS);

        // Channels for energy L1 registers
        activeEnergyPlusL1 = new ChannelUID(thingUID, GROUP_ENERGY_L1, ACTIVE_ENERGY_PLUS);
        activeEnergyMinusL1 = new ChannelUID(thingUID, GROUP_ENERGY_L1, ACTIVE_ENERGY_MINUS);
        reactiveEnergyPlusL1 = new ChannelUID(thingUID, GROUP_ENERGY_L1, REACTIVE_ENERGY_PLUS);
        reactiveEnergyMinusL1 = new ChannelUID(thingUID, GROUP_ENERGY_L1, REACTIVE_ENERGY_MINUS);
        apparentEnergyPlusL1 = new ChannelUID(thingUID, GROUP_ENERGY_L1, APPARENT_ENERGY_PLUS);
        apparentEnergyMinusL1 = new ChannelUID(thingUID, GROUP_ENERGY_L1, APPARENT_ENERGY_MINUS);

        // Channels for energy L2 registers
        activeEnergyPlusL2 = new ChannelUID(thingUID, GROUP_ENERGY_L2, ACTIVE_ENERGY_PLUS);
        activeEnergyMinusL2 = new ChannelUID(thingUID, GROUP_ENERGY_L2, ACTIVE_ENERGY_MINUS);
        reactiveEnergyPlusL2 = new ChannelUID(thingUID, GROUP_ENERGY_L2, REACTIVE_ENERGY_PLUS);
        reactiveEnergyMinusL2 = new ChannelUID(thingUID, GROUP_ENERGY_L2, REACTIVE_ENERGY_MINUS);
        apparentEnergyPlusL2 = new ChannelUID(thingUID, GROUP_ENERGY_L2, APPARENT_ENERGY_PLUS);
        apparentEnergyMinusL2 = new ChannelUID(thingUID, GROUP_ENERGY_L2, APPARENT_ENERGY_MINUS);

        // Channels for energy L3 registers
        activeEnergyPlusL3 = new ChannelUID(thingUID, GROUP_ENERGY_L3, ACTIVE_ENERGY_PLUS);
        activeEnergyMinusL3 = new ChannelUID(thingUID, GROUP_ENERGY_L3, ACTIVE_ENERGY_MINUS);
        reactiveEnergyPlusL3 = new ChannelUID(thingUID, GROUP_ENERGY_L3, REACTIVE_ENERGY_PLUS);
        reactiveEnergyMinusL3 = new ChannelUID(thingUID, GROUP_ENERGY_L3, REACTIVE_ENERGY_MINUS);
        apparentEnergyPlusL3 = new ChannelUID(thingUID, GROUP_ENERGY_L3, APPARENT_ENERGY_PLUS);
        apparentEnergyMinusL3 = new ChannelUID(thingUID, GROUP_ENERGY_L3, APPARENT_ENERGY_MINUS);
    }
}
