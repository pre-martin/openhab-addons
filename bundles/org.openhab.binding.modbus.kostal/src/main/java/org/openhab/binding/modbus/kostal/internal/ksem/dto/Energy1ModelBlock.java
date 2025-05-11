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
 * Model for Kostal Smart Energy Meter (Energy register - Block 1)
 *
 * @author Martin Renner - Initial contribution
 */
public class Energy1ModelBlock {
    public BigInteger activeEnergyPlusL2;
    public BigInteger activeEnergyMinusL2;
    public BigInteger reactiveEnergyPlusL2;
    public BigInteger reactiveEnergyMinusL2;
    public BigInteger apparentEnergyPlusL2;
    public BigInteger apparentEnergyMinusL2;

    public BigInteger activeEnergyPlusL3;
    public BigInteger activeEnergyMinusL3;
    public BigInteger reactiveEnergyPlusL3;
    public BigInteger reactiveEnergyMinusL3;
    public BigInteger apparentEnergyPlusL3;
    public BigInteger apparentEnergyMinusL3;
}
