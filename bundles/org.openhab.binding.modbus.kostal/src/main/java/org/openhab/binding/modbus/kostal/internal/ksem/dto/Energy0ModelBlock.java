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

import java.math.BigInteger;

/**
 * Model for Kostal Smart Energy Meter (Energy register - Block 0)
 *
 * @author Martin Renner - Initial contribution
 */
public class Energy0ModelBlock {
    public BigInteger activeEnergyPlus;
    public BigInteger activeEnergyMinus;
    public BigInteger reactiveEnergyPlus;
    public BigInteger reactiveEnergyMinus;
    public BigInteger apparentEnergyPlus;
    public BigInteger apparentEnergyMinus;

    public BigInteger activeEnergyPlusL1;
    public BigInteger activeEnergyMinusL1;
    public BigInteger reactiveEnergyPlusL1;
    public BigInteger reactiveEnergyMinusL1;
    public BigInteger apparentEnergyPlusL1;
    public BigInteger apparentEnergyMinusL1;
}
