/**
 * Copyright (c) 2010-2022 Contributors to the openHAB project
 * <p>
 * See the NOTICE file(s) distributed with this work for additional information.
 * <p>
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License 2.0
 * which is available at http://www.eclipse.org/legal/epl-2.0
 * <p>
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.proconip.internal.model;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * Parser to handle the convertion between the http interface and the model of the binding.
 *
 * @author Martin Renner - Initial contribution
 */
@NonNullByDefault
public class Parser {

    /**
     * Parses the output of GetState.csv.
     *
     * @param stateAsString The state as returned by the device.
     * @throws IllegalStateException If the state is not in the expected format.
     * @throws NumberFormatException If a number could not be parsed.
     */
    public DeviceState parseState(String stateAsString) {
        String[] lines = stateAsString.split("\n");
        if (lines.length != 6) {
            throw new IllegalStateException("Expected device state to have 6 lines, got " + lines.length);
        }

        var names = parseStringLine(lines[1], 2, 42);
        var units = parseStringLine(lines[2], 3, 42);
        var offsets = parseDoubleLine(lines[3], 4, 42);
        var gains = parseDoubleLine(lines[4], 5, 42);
        var values = parseIntegerLine(lines[5], 6, 42);

        DeviceState state = parseLine1(lines[0]);
        for (int i = 0; i < 42; i++) {
            state.setName(i, names[i]);
        }
        for (int i = 0; i < 5; i++) {
            state.setAdc(i, offsets[i + 1] + gains[i + 1] * values[i + 1]);
        }
        state.setRedox(offsets[6] + gains[6] * values[6]);
        state.setPh(offsets[7] + gains[7] * values[7]);
        for (int i = 0; i < 8; i++) {
            state.setTemperature(i, offsets[i + 8] + gains[i + 8] * values[i + 8]);
        }
        for (int i = 0; i < 8; i++) {
            state.setRelais(i, (values[i + 16] & 1) != 0);
        }
        for (int i = 0; i < 4; i++) {
            state.setDigitalInput(i, offsets[i + 24] + gains[i + 24] * values[i + 24]);
        }
        for (int i = 0; i < 8; i++) {
            state.setExternalRelais(i, (values[i + 28] & 1) != 0);
        }
        state.setChlorineRemaining(offsets[36] + gains[36] * values[36]);
        state.setPhMinusRemaining(offsets[37] + gains[37] * values[37]);
        state.setPhPlusRemaining(offsets[38] + gains[38] * values[38]);
        state.setChlorineUsage(offsets[39] + gains[39] * values[39]);
        state.setPhMinusUsage(offsets[40] + gains[40] * values[40]);
        state.setPhPlusUsage(offsets[41] + gains[41] * values[41]);
        return state;
    }

    /**
     * Parses line 1 of the response, which contains static information and configuration of the device.
     */
    private DeviceState parseLine1(String line1) {
        String[] line1Columns = line1.split(",");
        if (line1Columns.length < 10) {
            throw new IllegalStateException("Expected line 1 to have at least 10 columns, got " + line1Columns.length);
        }

        DeviceState state = new DeviceState(line1Columns[1], Integer.parseInt(line1Columns[2]));
        int dosage = Integer.parseInt(line1Columns[6]);
        state.setPhPlusDosing((dosage & (1 << 12)) != 0);
        state.setPhMinusDosing((dosage & (1 << 8)) != 0);
        state.setChlorineDosing((dosage & 1) != 0);
        state.setChlorineType((dosage & (1 << 4)) != 0 ? ChlorineType.Electrolysis : ChlorineType.Liquid);
        return state;
    }

    private String[] parseStringLine(String line, int lineNumber, int expected) {
        String[] lineColumns = line.split(",");
        if (lineColumns.length < expected) {
            throw new IllegalStateException("Expected line " + lineNumber + " to have at least " + expected
                    + " columns, got " + lineColumns.length);
        }
        return lineColumns;
    }

    private double[] parseDoubleLine(String line, int lineNumber, int expected) {
        String[] lineColumns = line.split(",");
        if (lineColumns.length < expected) {
            throw new IllegalStateException("Expected line " + lineNumber + " to have at least " + expected
                    + " columns, got " + lineColumns.length);
        }

        double[] result = new double[lineColumns.length];
        for (int i = 0; i < lineColumns.length; i++) {
            result[i] = Double.parseDouble(lineColumns[i]);
        }
        return result;
    }

    private int[] parseIntegerLine(String line, int lineNumber, int expected) {
        String[] lineColumns = line.split(",");
        if (lineColumns.length < expected) {
            throw new IllegalStateException("Expected line " + lineNumber + " to have at least " + expected
                    + " columns, got " + lineColumns.length);
        }

        int[] result = new int[lineColumns.length];
        for (int i = 0; i < lineColumns.length; i++) {
            result[i] = Integer.parseInt(lineColumns[i]);
        }
        return result;
    }
}
