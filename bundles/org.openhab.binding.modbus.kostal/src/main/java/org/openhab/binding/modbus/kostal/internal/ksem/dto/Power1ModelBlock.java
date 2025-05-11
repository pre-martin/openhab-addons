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
package org.openhab.binding.modbus.kostal.internal.ksem.dto;

/**
 * Model for Kostal Smart Energy Meter (Power registers - Block 1)
 *
 * @author Martin Renner - Initial contribution
 */
public class Power1ModelBlock {
    public long activePowerPlusL1;
    public long activePowerMinusL1;
    public long reactivePowerPlusL1;
    public long reactivePowerMinusL1;
    public long apparentPowerPlusL1;
    public long apparentPowerMinusL1;
    public long currentL1;
    public long voltageL1;
    public int powerFactorL1;

    public long activePowerPlusL2;
    public long activePowerMinusL2;
    public long reactivePowerPlusL2;
    public long reactivePowerMinusL2;
    public long apparentPowerPlusL2;
    public long apparentPowerMinusL2;
    public long currentL2;
    public long voltageL2;
    public int powerFactorL2;

    public long activePowerPlusL3;
    public long activePowerMinusL3;
    public long reactivePowerPlusL3;
    public long reactivePowerMinusL3;
    public long apparentPowerPlusL3;
    public long apparentPowerMinusL3;
    public long currentL3;
    public long voltageL3;
    public int powerFactorL3;

    public long minimumActivePowerPlus;
}
