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

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * The {@link ThingConstants} class defines common constants, which are used across the whole binding.
 *
 * @author Martin Renner - Initial contribution
 */
@NonNullByDefault
public class ThingConstants {

    // Channels for "device information"
    public static final String GROUP_DEVICE_INFORMATION = "deviceInformation";
    public static final String NUMBER_OF_AC_PHASES = "numberOfAcPhases";
    public static final String NUMBER_OF_PV_STRINGS = "numberOfPvStrings";
    public static final String INVERTER_STATE = "inverterState";
    public static final String TEMPERATURE_OF_CONTROLLER_PCB = "temperatureOfControllerPcb";

    // Channels for "consumption"
    public static final String GROUP_CONSUMPTION = "consumption";
    public static final String HOME_OWN_CONSUMPTION_FROM_BATTERY = "homeOwnConsumptionFromBattery";
    public static final String HOME_OWN_CONSUMPTION_FROM_GRID = "homeOwnConsumptionFromGrid";
    public static final String HOME_OWN_CONSUMPTION_FROM_PV = "homeOwnConsumptionFromPv";
    public static final String TOTAL_HOME_CONSUMPTION_BATTERY = "totalHomeConsumptionBattery";
    public static final String TOTAL_HOME_CONSUMPTION_GRID = "totalHomeConsumptionGrid";
    public static final String TOTAL_HOME_CONSUMPTION_PV = "totalHomeConsumptionPv";
    public static final String TOTAL_HOME_CONSUMPTION = "totalHomeConsumption";
    public static final String TOTAL_HOME_CONSUMPTION_RATE = "totalHomeConsumptionRate";

    // Channels for "ac generic"
    public static final String GROUP_AC_GENERIC = "acGeneric";
    public static final String COS_PHI = "cosPhi";
    public static final String FREQUENCY = "frequency";
    public static final String TOTAL_AC_ACTIVE_POWER = "totalAcActivePower";
    public static final String TOTAL_AC_REACTIVE_POWER = "totalAcReactivePower";
    public static final String TOTAL_AC_APPARENT_POWER = "totalAcApparentPower";

    // Channels for "ac phase"
    public static final String GROUP_AC_PHASE1 = "acPhase1";
    public static final String GROUP_AC_PHASE2 = "acPhase2";
    public static final String GROUP_AC_PHASE3 = "acPhase3";
    public static final String CURRENT = "current";
    public static final String ACTIVE_POWER = "activePower";
    public static final String VOLTAGE = "voltage";

    // Channels for "battery"
    public static final String GROUP_BATTERY = "battery";
    public static final String BATTERY_CHARGE_CURRENT = "batteryChargeCurrent";
    public static final String NUMBER_OF_BATTERY_CYCLES = "numberOfBatteryCycles";
    public static final String ACTUAL_BATTERY_CHARGE = "actualBatteryCharge";
    public static final String PSSB_FUSE_STATE = "pssbFuseState";
    public static final String BATTERY_READY_FLAG = "batteryReadyFlag";
    public static final String ACTUAL_STATE_OF_CHARGE = "actualStateOfCharge";
    public static final String BATTERY_TEMPERATURE = "batteryTemperature";
    public static final String BATTERY_VOLTAGE = "batteryVoltage";
    public static final String BATTERY_GROSS_CAPACITY = "batteryGrossCapacity";
    public static final String BATTERY_ACTUAL_SOC = "batteryActualSoc";
    public static final String BATTERY_MANUFACTURER = "batteryManufacturer";
    public static final String BATTERY_MODEL_ID = "batteryModelId";
    public static final String BATTERY_SERIAL_NUMBER = "batterySerialNumber";
    public static final String WORK_CAPACITY = "workCapacity";
    public static final String ACTUAL_BATTERY_CHARGE_POWER = "actualBatteryChargePower";
    public static final String BATTERY_FIRMWARE = "batteryFirmware";
    public static final String BATTERY_TYPE = "batteryType";
    public static final String TOTAL_DC_CHARGE_ENERGY = "totalDcChargeEnergy";
    public static final String TOTAL_DC_DISCHARGE_ENERGY = "totalDcDischargeEnergy";
    public static final String TOTAL_AC_CHARGE_ENERGY = "totalAcChargeEnergy";
    public static final String TOTAL_AC_DISCHARGE_ENERGY = "totalAcDischargeEnergy";
    public static final String TOTAL_AC_CHARGE_ENERGY_GRID = "totalAcChargeEnergyGrid";

    // Channels for "powermeter generic"
    public static final String GROUP_POWERMETER_GENERIC = "powermeterGeneric";
    public static final String COS_PHI_PM = "cosPhi";
    public static final String FREQUENCY_PM = "frequency";
    public static final String TOTAL_ACTIVE_POWER_PM = "totalActivePower";
    public static final String TOTAL_REACTIVE_POWER_PM = "totalReactivePower";
    public static final String TOTAL_APPARENT_POWER_PM = "totalApparentPower";

    // Channels for "powermeter phase"
    public static final String GROUP_POWERMETER_PHASE1 = "powermeterPhase1";
    public static final String GROUP_POWERMETER_PHASE2 = "powermeterPhase2";
    public static final String GROUP_POWERMETER_PHASE3 = "powermeterPhase3";
    public static final String CURRENT_PM = "current";
    public static final String ACTIVE_POWER_PM = "activePower";
    public static final String REACTIVE_POWER_PM = "reactivePower";
    public static final String APPARENT_POWER_PM = "apparentPower";
    public static final String VOLTAGE_PM = "voltage";

    // Channels for "dc generic"
    public static final String GROUP_DC_GENERIC = "dcGeneric";
    public static final String TOTAL_DC_POWER = "totalDcPower";
    public static final String TOTAL_DC_POWER_SUM = "totalDcPowerSum";

    // Channels for "dc string"
    public static final String GROUP_DC_STRING1 = "dcString1";
    public static final String GROUP_DC_STRING2 = "dcString2";
    public static final String GROUP_DC_STRING3 = "dcString3";
    public static final String CURRENT_DC = "current";
    public static final String POWER_DC = "power";
    public static final String VOLTAGE_DC = "voltage";

    // Channels for "yield"
    public static final String GROUP_YIELD = "yield";
    public static final String TOTAL_YIELD = "totalYield";
    public static final String DAILY_YIELD = "dailyYield";
    public static final String MONTHLY_YIELD = "monthlyYield";
    public static final String YEARLY_YIELD = "yearlyYield";

    // Channels for "dc ac energy"
    public static final String GROUP_DC_AC_ENERGY = "dcAcEnergy";
    public static final String TOTAL_DC_PV_ENERGY = "totalDcPvEnergy";
    public static final String TOTAL_DC_ENERGY_FROM_PV1 = "totalDcEnergyFromPv1";
    public static final String TOTAL_DC_ENERGY_FROM_PV2 = "totalDcEnergyFromPv2";
    public static final String TOTAL_DC_ENERGY_FROM_PV3 = "totalDcEnergyFromPv3";
    public static final String TOTAL_ENERGY_AC_SIDE_TO_GRID = "totalEnergyAcSideToGrid";
}
