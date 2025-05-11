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
package org.openhab.binding.modbus.kostal.internal.plenticore.dto;

/**
 * Model for Kostal Plenticore operating data (part "Data1")
 *
 * @author Martin Renner - Initial contribution
 */
public class Data1ModelBlock {
    public double temperatureOfControllerPcb;
    public double totalDcPower;
    public long stateOfEnergyManager;

    public double homeOwnConsumptionFromBattery;
    public double homeOwnConsumptionFromGrid;
    public double totalHomeConsumptionBattery;
    public double totalHomeConsumptionGrid;
    public double totalHomeConsumptionPv;
    public double homeOwnConsumptionFromPv;
    public double totalHomeConsumption;
    public double totalHomeConsumptionRate;

    public double actualCosPhi;
    public double gridFrequency;
    public double currentPhase1;
    public double activePowerPhase1;
    public double voltagePhase1;
    public double currentPhase2;
    public double activePowerPhase2;
    public double voltagePhase2;
    public double currentPhase3;
    public double activePowerPhase3;
    public double voltagePhase3;
    public double totalAcActivePower;
    public double totalAcReactivePower;
    public double totalAcApparentPower;

    public double batteryChargeCurrent;
    public double numberOfBatteryCycles;
    public double actualBatteryCharge;
    public double pssbFuseState;
    public double batteryReadyFlag;
    public double actualStateOfCharge;
    public double batteryTemperature;
    public double batteryVoltage;
}
