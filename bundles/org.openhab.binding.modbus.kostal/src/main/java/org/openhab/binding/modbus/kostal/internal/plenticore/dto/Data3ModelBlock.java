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
 * Model for Kostal Plenticore operating data (part "Data3")
 *
 * @author Martin Renner - Initial contribution
 */
public class Data3ModelBlock {
    public double totalYield;
    public double dailyYield;
    public double yearlyYield;
    public double monthlyYield;
}
