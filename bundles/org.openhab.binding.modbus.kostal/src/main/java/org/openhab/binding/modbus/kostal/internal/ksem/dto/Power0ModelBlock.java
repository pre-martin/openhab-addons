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
 * Model for Kostal Smart Energy Meter (Power registers - Block 0)
 *
 * @author Martin Renner - Initial contribution
 */
public class Power0ModelBlock {
    public long activePowerPlus;
    public long activePowerMinus;
    public long reactivePowerPlus;
    public long reactivePowerMinus;
    public long apparentPowerPlus;
    public long apparentPowerMinus;
    public int powerFactor;
    public long supplyFrequency;
}
