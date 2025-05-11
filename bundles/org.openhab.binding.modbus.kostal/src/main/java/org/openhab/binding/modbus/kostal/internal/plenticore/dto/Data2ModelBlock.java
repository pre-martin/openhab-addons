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
 * Model for Kostal Plenticore operating data (part "Data2")
 *
 * @author Martin Renner - Initial contribution
 */
public class Data2ModelBlock {
    public double cosPhiPowerMeter;
    public double frequencyPowerMeter;
    public double currentPhase1PowerMeter;
    public double activePowerPhase1PowerMeter;
    public double reactivePowerPhase1PowerMeter;
    public double apparentPowerPhase1PowerMeter;
    public double voltagePhase1PowerMeter;
    public double currentPhase2PowerMeter;
    public double activePowerPhase2PowerMeter;
    public double reactivePowerPhase2PowerMeter;
    public double apparentPowerPhase2PowerMeter;
    public double voltagePhase2PowerMeter;
    public double currentPhase3PowerMeter;
    public double activePowerPhase3PowerMeter;
    public double reactivePowerPhase3PowerMeter;
    public double apparentPowerPhase3PowerMeter;
    public double voltagePhase3PowerMeter;
    public double totalActivePowerPowerMeter;
    public double totalReactivePowerPowerMeter;
    public double totalApparentPowerPowerMeter;

    public double currentDc1;
    public double powerDc1;
    public double voltageDc1;
    public double currentDc2;
    public double powerDc2;
    public double voltageDc2;
    public double currentDc3;
    public double powerDc3;
    public double voltageDc3;
}
