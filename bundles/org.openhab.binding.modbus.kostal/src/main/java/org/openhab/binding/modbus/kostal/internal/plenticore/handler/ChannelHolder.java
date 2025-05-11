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
package org.openhab.binding.modbus.kostal.internal.plenticore.handler;

import static org.openhab.binding.modbus.kostal.internal.plenticore.ThingConstants.*;

import org.openhab.core.thing.ChannelUID;
import org.openhab.core.thing.ThingUID;

/**
 * Utility class to bundle {@link ChannelUID ChannelUIDs} of the binding. Lots of boilerplate code that
 * should not pollute the handler.
 *
 * @author Martin Renner - Initial contribution
 */
class ChannelHolder {

    final ChannelUID numberOfAcPhases;
    final ChannelUID numberOfPvStrings;
    final ChannelUID inverterState;
    final ChannelUID temperatureOfControllerPcb;

    final ChannelUID homeOwnConsumptionFromBattery;
    final ChannelUID homeOwnConsumptionFromGrid;
    final ChannelUID homeOwnConsumptionFromPv;
    final ChannelUID totalHomeConsumptionBattery;
    final ChannelUID totalHomeConsumptionGrid;
    final ChannelUID totalHomeConsumptionPv;
    final ChannelUID totalHomeConsumption;
    final ChannelUID totalHomeConsumptionRate;

    final ChannelUID cosPhi;
    final ChannelUID frequency;
    final ChannelUID totalAcActivePower;
    final ChannelUID totalAcReactivePower;
    final ChannelUID totalAcApparentPower;

    final ChannelUID currentPhase1;
    final ChannelUID activePowerPhase1;
    final ChannelUID voltagePhase1;
    final ChannelUID currentPhase2;
    final ChannelUID activePowerPhase2;
    final ChannelUID voltagePhase2;
    final ChannelUID currentPhase3;
    final ChannelUID activePowerPhase3;
    final ChannelUID voltagePhase3;

    final ChannelUID batteryChargeCurrent;
    final ChannelUID numberOfBatteryCycles;
    final ChannelUID actualBatteryCharge;
    final ChannelUID pssbFuseState;
    final ChannelUID batteryReadyFlag;
    final ChannelUID actualStateOfCharge;
    final ChannelUID batteryTemperature;
    final ChannelUID batteryVoltage;
    final ChannelUID batteryGrossCapacity;
    final ChannelUID batteryActualSoc;
    final ChannelUID batteryManufacturer;
    final ChannelUID batteryModelId;
    final ChannelUID batterySerialNumber;
    final ChannelUID workCapacity;
    final ChannelUID actualBatteryChargePower;
    final ChannelUID batteryFirmware;
    final ChannelUID batteryType;
    final ChannelUID totalDcChargeEnergy;
    final ChannelUID totalDcDischargeEnergy;
    final ChannelUID totalAcChargeEnergy;
    final ChannelUID totalAcDischargeEnergy;
    final ChannelUID totalAcChargeEnergyGrid;

    final ChannelUID cosPhiPowerMeter;
    final ChannelUID frequencyPowerMeter;
    final ChannelUID totalActivePowerPowerMeter;
    final ChannelUID totalReactivePowerPowerMeter;
    final ChannelUID totalApparentPowerPowerMeter;

    final ChannelUID currentPhase1PowerMeter;
    final ChannelUID activePowerPhase1PowerMeter;
    final ChannelUID reactivePowerPhase1PowerMeter;
    final ChannelUID apparentPowerPhase1PowerMeter;
    final ChannelUID voltagePhase1PowerMeter;
    final ChannelUID currentPhase2PowerMeter;
    final ChannelUID activePowerPhase2PowerMeter;
    final ChannelUID reactivePowerPhase2PowerMeter;
    final ChannelUID apparentPowerPhase2PowerMeter;
    final ChannelUID voltagePhase2PowerMeter;
    final ChannelUID currentPhase3PowerMeter;
    final ChannelUID activePowerPhase3PowerMeter;
    final ChannelUID reactivePowerPhase3PowerMeter;
    final ChannelUID apparentPowerPhase3PowerMeter;
    final ChannelUID voltagePhase3PowerMeter;

    final ChannelUID totalDcPower;
    final ChannelUID totalDcPowerSum;

    final ChannelUID currentDc1;
    final ChannelUID powerDc1;
    final ChannelUID voltageDc1;
    final ChannelUID currentDc2;
    final ChannelUID powerDc2;
    final ChannelUID voltageDc2;
    final ChannelUID currentDc3;
    final ChannelUID powerDc3;
    final ChannelUID voltageDc3;

    final ChannelUID totalYield;
    final ChannelUID dailyYield;
    final ChannelUID monthlyYield;
    final ChannelUID yearlyYield;

    final ChannelUID totalDcPvEnergy;
    final ChannelUID totalDcEnergyFromPv1;
    final ChannelUID totalDcEnergyFromPv2;
    final ChannelUID totalDcEnergyFromPv3;
    final ChannelUID totalEnergyAcSideToGrid;

    ChannelHolder(ThingUID thingUID) {
        // Channels for "device information"
        numberOfAcPhases = new ChannelUID(thingUID, GROUP_DEVICE_INFORMATION, NUMBER_OF_AC_PHASES);
        numberOfPvStrings = new ChannelUID(thingUID, GROUP_DEVICE_INFORMATION, NUMBER_OF_PV_STRINGS);
        inverterState = new ChannelUID(thingUID, GROUP_DEVICE_INFORMATION, INVERTER_STATE);
        temperatureOfControllerPcb = new ChannelUID(thingUID, GROUP_DEVICE_INFORMATION, TEMPERATURE_OF_CONTROLLER_PCB);

        // Channels for "consumption"
        homeOwnConsumptionFromBattery = new ChannelUID(thingUID, GROUP_CONSUMPTION, HOME_OWN_CONSUMPTION_FROM_BATTERY);
        homeOwnConsumptionFromGrid = new ChannelUID(thingUID, GROUP_CONSUMPTION, HOME_OWN_CONSUMPTION_FROM_GRID);
        homeOwnConsumptionFromPv = new ChannelUID(thingUID, GROUP_CONSUMPTION, HOME_OWN_CONSUMPTION_FROM_PV);
        totalHomeConsumptionBattery = new ChannelUID(thingUID, GROUP_CONSUMPTION, TOTAL_HOME_CONSUMPTION_BATTERY);
        totalHomeConsumptionGrid = new ChannelUID(thingUID, GROUP_CONSUMPTION, TOTAL_HOME_CONSUMPTION_GRID);
        totalHomeConsumptionPv = new ChannelUID(thingUID, GROUP_CONSUMPTION, TOTAL_HOME_CONSUMPTION_PV);
        totalHomeConsumption = new ChannelUID(thingUID, GROUP_CONSUMPTION, TOTAL_HOME_CONSUMPTION);
        totalHomeConsumptionRate = new ChannelUID(thingUID, GROUP_CONSUMPTION, TOTAL_HOME_CONSUMPTION_RATE);

        // Channels for "ac generic"
        cosPhi = new ChannelUID(thingUID, GROUP_AC_GENERIC, COS_PHI);
        frequency = new ChannelUID(thingUID, GROUP_AC_GENERIC, FREQUENCY);
        totalAcActivePower = new ChannelUID(thingUID, GROUP_AC_GENERIC, TOTAL_AC_ACTIVE_POWER);
        totalAcReactivePower = new ChannelUID(thingUID, GROUP_AC_GENERIC, TOTAL_AC_REACTIVE_POWER);
        totalAcApparentPower = new ChannelUID(thingUID, GROUP_AC_GENERIC, TOTAL_AC_APPARENT_POWER);

        // Channels for "ac phase"
        currentPhase1 = new ChannelUID(thingUID, GROUP_AC_PHASE1, CURRENT);
        activePowerPhase1 = new ChannelUID(thingUID, GROUP_AC_PHASE1, ACTIVE_POWER);
        voltagePhase1 = new ChannelUID(thingUID, GROUP_AC_PHASE1, VOLTAGE);
        currentPhase2 = new ChannelUID(thingUID, GROUP_AC_PHASE2, CURRENT);
        activePowerPhase2 = new ChannelUID(thingUID, GROUP_AC_PHASE2, ACTIVE_POWER);
        voltagePhase2 = new ChannelUID(thingUID, GROUP_AC_PHASE2, VOLTAGE);
        currentPhase3 = new ChannelUID(thingUID, GROUP_AC_PHASE3, CURRENT);
        activePowerPhase3 = new ChannelUID(thingUID, GROUP_AC_PHASE3, ACTIVE_POWER);
        voltagePhase3 = new ChannelUID(thingUID, GROUP_AC_PHASE3, VOLTAGE);

        // Channels for "battery"
        batteryChargeCurrent = new ChannelUID(thingUID, GROUP_BATTERY, BATTERY_CHARGE_CURRENT);
        numberOfBatteryCycles = new ChannelUID(thingUID, GROUP_BATTERY, NUMBER_OF_BATTERY_CYCLES);
        actualBatteryCharge = new ChannelUID(thingUID, GROUP_BATTERY, ACTUAL_BATTERY_CHARGE);
        pssbFuseState = new ChannelUID(thingUID, GROUP_BATTERY, PSSB_FUSE_STATE);
        batteryReadyFlag = new ChannelUID(thingUID, GROUP_BATTERY, BATTERY_READY_FLAG);
        actualStateOfCharge = new ChannelUID(thingUID, GROUP_BATTERY, ACTUAL_STATE_OF_CHARGE);
        batteryTemperature = new ChannelUID(thingUID, GROUP_BATTERY, BATTERY_TEMPERATURE);
        batteryVoltage = new ChannelUID(thingUID, GROUP_BATTERY, BATTERY_VOLTAGE);
        batteryGrossCapacity = new ChannelUID(thingUID, GROUP_BATTERY, BATTERY_GROSS_CAPACITY);
        batteryActualSoc = new ChannelUID(thingUID, GROUP_BATTERY, BATTERY_ACTUAL_SOC);
        batteryManufacturer = new ChannelUID(thingUID, GROUP_BATTERY, BATTERY_MANUFACTURER);
        batteryModelId = new ChannelUID(thingUID, GROUP_BATTERY, BATTERY_MODEL_ID);
        batterySerialNumber = new ChannelUID(thingUID, GROUP_BATTERY, BATTERY_SERIAL_NUMBER);
        workCapacity = new ChannelUID(thingUID, GROUP_BATTERY, WORK_CAPACITY);
        actualBatteryChargePower = new ChannelUID(thingUID, GROUP_BATTERY, ACTUAL_BATTERY_CHARGE_POWER);
        batteryFirmware = new ChannelUID(thingUID, GROUP_BATTERY, BATTERY_FIRMWARE);
        batteryType = new ChannelUID(thingUID, GROUP_BATTERY, BATTERY_TYPE);
        totalDcChargeEnergy = new ChannelUID(thingUID, GROUP_BATTERY, TOTAL_DC_CHARGE_ENERGY);
        totalDcDischargeEnergy = new ChannelUID(thingUID, GROUP_BATTERY, TOTAL_DC_DISCHARGE_ENERGY);
        totalAcChargeEnergy = new ChannelUID(thingUID, GROUP_BATTERY, TOTAL_AC_CHARGE_ENERGY);
        totalAcDischargeEnergy = new ChannelUID(thingUID, GROUP_BATTERY, TOTAL_AC_DISCHARGE_ENERGY);
        totalAcChargeEnergyGrid = new ChannelUID(thingUID, GROUP_BATTERY, TOTAL_AC_CHARGE_ENERGY_GRID);

        // Channels for "powermeter generic"
        cosPhiPowerMeter = new ChannelUID(thingUID, GROUP_POWERMETER_GENERIC, COS_PHI_PM);
        frequencyPowerMeter = new ChannelUID(thingUID, GROUP_POWERMETER_GENERIC, FREQUENCY_PM);
        totalActivePowerPowerMeter = new ChannelUID(thingUID, GROUP_POWERMETER_GENERIC, TOTAL_ACTIVE_POWER_PM);
        totalReactivePowerPowerMeter = new ChannelUID(thingUID, GROUP_POWERMETER_GENERIC, TOTAL_REACTIVE_POWER_PM);
        totalApparentPowerPowerMeter = new ChannelUID(thingUID, GROUP_POWERMETER_GENERIC, TOTAL_APPARENT_POWER_PM);

        // Channels for "powermeter phase"
        currentPhase1PowerMeter = new ChannelUID(thingUID, GROUP_POWERMETER_PHASE1, CURRENT_PM);
        activePowerPhase1PowerMeter = new ChannelUID(thingUID, GROUP_POWERMETER_PHASE1, ACTIVE_POWER_PM);
        reactivePowerPhase1PowerMeter = new ChannelUID(thingUID, GROUP_POWERMETER_PHASE1, REACTIVE_POWER_PM);
        apparentPowerPhase1PowerMeter = new ChannelUID(thingUID, GROUP_POWERMETER_PHASE1, APPARENT_POWER_PM);
        voltagePhase1PowerMeter = new ChannelUID(thingUID, GROUP_POWERMETER_PHASE1, VOLTAGE_PM);
        currentPhase2PowerMeter = new ChannelUID(thingUID, GROUP_POWERMETER_PHASE2, CURRENT_PM);
        activePowerPhase2PowerMeter = new ChannelUID(thingUID, GROUP_POWERMETER_PHASE2, ACTIVE_POWER_PM);
        reactivePowerPhase2PowerMeter = new ChannelUID(thingUID, GROUP_POWERMETER_PHASE2, REACTIVE_POWER_PM);
        apparentPowerPhase2PowerMeter = new ChannelUID(thingUID, GROUP_POWERMETER_PHASE2, APPARENT_POWER_PM);
        voltagePhase2PowerMeter = new ChannelUID(thingUID, GROUP_POWERMETER_PHASE2, VOLTAGE_PM);
        currentPhase3PowerMeter = new ChannelUID(thingUID, GROUP_POWERMETER_PHASE3, CURRENT_PM);
        activePowerPhase3PowerMeter = new ChannelUID(thingUID, GROUP_POWERMETER_PHASE3, ACTIVE_POWER_PM);
        reactivePowerPhase3PowerMeter = new ChannelUID(thingUID, GROUP_POWERMETER_PHASE3, REACTIVE_POWER_PM);
        apparentPowerPhase3PowerMeter = new ChannelUID(thingUID, GROUP_POWERMETER_PHASE3, APPARENT_POWER_PM);
        voltagePhase3PowerMeter = new ChannelUID(thingUID, GROUP_POWERMETER_PHASE3, VOLTAGE_PM);

        // Channels for "dc generic"
        totalDcPower = new ChannelUID(thingUID, GROUP_DC_GENERIC, TOTAL_DC_POWER);
        totalDcPowerSum = new ChannelUID(thingUID, GROUP_DC_GENERIC, TOTAL_DC_POWER_SUM);

        // Channels for "dc string"
        currentDc1 = new ChannelUID(thingUID, GROUP_DC_STRING1, CURRENT_DC);
        powerDc1 = new ChannelUID(thingUID, GROUP_DC_STRING1, POWER_DC);
        voltageDc1 = new ChannelUID(thingUID, GROUP_DC_STRING1, VOLTAGE_DC);
        currentDc2 = new ChannelUID(thingUID, GROUP_DC_STRING2, CURRENT_DC);
        powerDc2 = new ChannelUID(thingUID, GROUP_DC_STRING2, POWER_DC);
        voltageDc2 = new ChannelUID(thingUID, GROUP_DC_STRING2, VOLTAGE_DC);
        currentDc3 = new ChannelUID(thingUID, GROUP_DC_STRING3, CURRENT_DC);
        powerDc3 = new ChannelUID(thingUID, GROUP_DC_STRING3, POWER_DC);
        voltageDc3 = new ChannelUID(thingUID, GROUP_DC_STRING3, VOLTAGE_DC);

        // Channels for "yield"
        totalYield = new ChannelUID(thingUID, GROUP_YIELD, TOTAL_YIELD);
        dailyYield = new ChannelUID(thingUID, GROUP_YIELD, DAILY_YIELD);
        monthlyYield = new ChannelUID(thingUID, GROUP_YIELD, MONTHLY_YIELD);
        yearlyYield = new ChannelUID(thingUID, GROUP_YIELD, YEARLY_YIELD);

        // Channels for "dc ac energy"
        totalDcPvEnergy = new ChannelUID(thingUID, GROUP_DC_AC_ENERGY, TOTAL_DC_PV_ENERGY);
        totalDcEnergyFromPv1 = new ChannelUID(thingUID, GROUP_DC_AC_ENERGY, TOTAL_DC_ENERGY_FROM_PV1);
        totalDcEnergyFromPv2 = new ChannelUID(thingUID, GROUP_DC_AC_ENERGY, TOTAL_DC_ENERGY_FROM_PV2);
        totalDcEnergyFromPv3 = new ChannelUID(thingUID, GROUP_DC_AC_ENERGY, TOTAL_DC_ENERGY_FROM_PV3);
        totalEnergyAcSideToGrid = new ChannelUID(thingUID, GROUP_DC_AC_ENERGY, TOTAL_ENERGY_AC_SIDE_TO_GRID);
    }
}
