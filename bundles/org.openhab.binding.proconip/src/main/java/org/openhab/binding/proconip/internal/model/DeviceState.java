/**
 * Copyright (c) 2010-2022 Contributors to the openHAB project
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
package org.openhab.binding.proconip.internal.model;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * State of the ProConIp.
 *
 * @author Martin Renner - Initial contribution
 */
@NonNullByDefault
public class DeviceState {
    private final String firmwareVersion;
    private final int uptime;
    private boolean phPlusDosing;
    private boolean phMinusDosing;
    private boolean chlorineDosing;
    private ChlorineType chlorineType = ChlorineType.Liquid;
    private final String[] names = new String[42];
    private final double[] adcs = new double[5];
    private double redox;
    private double ph;
    private final double[] temperatures = new double[8];
    private final boolean[] relais = new boolean[8];
    private final double[] digitalInputs = new double[4];
    private final boolean[] externalRelais = new boolean[8];
    private double chlorineRemaining;
    private double chlorineUsage;
    private double phMinusRemaining;
    private double phMinusUsage;
    private double phPlusRemaining;
    private double phPlusUsage;

    public DeviceState(String firmwareVersion, int uptime) {
        this.firmwareVersion = firmwareVersion;
        this.uptime = uptime;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public int getUptime() {
        return uptime;
    }

    public boolean hasPhPlusDosing() {
        return phPlusDosing;
    }

    public void setPhPlusDosing(boolean phPlusDosing) {
        this.phPlusDosing = phPlusDosing;
    }

    public boolean hasPhMinusDosing() {
        return phMinusDosing;
    }

    public void setPhMinusDosing(boolean phMinusDosing) {
        this.phMinusDosing = phMinusDosing;
    }

    public boolean hasChlorineDosing() {
        return chlorineDosing;
    }

    public void setChlorineDosing(boolean chlorineDosing) {
        this.chlorineDosing = chlorineDosing;
    }

    public ChlorineType getChlorineType() {
        return chlorineType;
    }

    public void setChlorineType(ChlorineType chlorineType) {
        this.chlorineType = chlorineType;
    }

    public void setName(int idx, String name) {
        names[idx] = name;
    }

    public String[] getNames() {
        return names;
    }

    public void setAdc(int idx, double adc) {
        adcs[idx] = adc;
    }

    public double[] getAdcs() {
        return adcs;
    }

    public double getRedox() {
        return redox;
    }

    public void setRedox(double redox) {
        this.redox = redox;
    }

    public double getPh() {
        return ph;
    }

    public void setPh(double ph) {
        this.ph = ph;
    }

    public void setTemperature(int idx, double temperature) {
        temperatures[idx] = temperature;
    }

    public double[] getTemperatures() {
        return temperatures;
    }

    public void setRelais(int idx, boolean state) {
        relais[idx] = state;
    }

    public boolean[] getRelais() {
        return relais;
    }

    public void setDigitalInput(int idx, double value) {
        digitalInputs[idx] = value;
    }

    public double[] getDigitalInputs() {
        return digitalInputs;
    }

    public void setExternalRelais(int idx, boolean state) {
        externalRelais[idx] = state;
    }

    public boolean[] getExternalRelais() {
        return externalRelais;
    }

    public double getChlorineRemaining() {
        return chlorineRemaining;
    }

    public void setChlorineRemaining(double chlorineRemaining) {
        this.chlorineRemaining = chlorineRemaining;
    }

    public double getChlorineUsage() {
        return chlorineUsage;
    }

    public void setChlorineUsage(double chlorineUsage) {
        this.chlorineUsage = chlorineUsage;
    }

    public double getPhMinusRemaining() {
        return phMinusRemaining;
    }

    public void setPhMinusRemaining(double phMinusRemaining) {
        this.phMinusRemaining = phMinusRemaining;
    }

    public double getPhMinusUsage() {
        return phMinusUsage;
    }

    public void setPhMinusUsage(double phMinusUsage) {
        this.phMinusUsage = phMinusUsage;
    }

    public double getPhPlusRemaining() {
        return phPlusRemaining;
    }

    public void setPhPlusRemaining(double phPlusRemaining) {
        this.phPlusRemaining = phPlusRemaining;
    }

    public double getPhPlusUsage() {
        return phPlusUsage;
    }

    public void setPhPlusUsage(double phPlusUsage) {
        this.phPlusUsage = phPlusUsage;
    }
}
