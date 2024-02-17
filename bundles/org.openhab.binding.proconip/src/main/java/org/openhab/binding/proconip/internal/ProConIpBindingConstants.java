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
package org.openhab.binding.proconip.internal;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.openhab.core.thing.ThingTypeUID;

/**
 * The {@link ProConIpBindingConstants} class defines common constants, which are
 * used across the whole binding.
 *
 * @author Martin Renner - Initial contribution
 */
@NonNullByDefault
public class ProConIpBindingConstants {

    private static final String BINDING_ID = "proconip";

    // List of all Thing Type UIDs
    public static final ThingTypeUID THING_TYPE_PROCONIP = new ThingTypeUID(BINDING_ID, "device");

    public static final String PROPERTY_UPTIME = "uptime";

    // List of all Channel groups
    public static final String CG_ADCS_PREFIX = "adcs#";
    public static final String CG_ELECTRODES_PREFIX = "electrodes#";
    public static final String CG_TEMPERATURES_PREFIX = "temperatures#";
    public static final String CG_RELAIS_PREFIX = "relais#";
    public static final String CG_DIGITAL_INPUT_PREFIX = "digitalInputs#";
    public static final String CG_EXTERNAL_RELAIS_PREFIX = "externalRelais#";
    public static final String CG_DOSING_PREFIX = "dosing#";

    // List of all Channel ids
    public static final String ADC_1 = CG_ADCS_PREFIX + "adc1";
    public static final String ADC_2 = CG_ADCS_PREFIX + "adc2";
    public static final String ADC_3 = CG_ADCS_PREFIX + "adc3";
    public static final String ADC_4 = CG_ADCS_PREFIX + "adc4";
    public static final String ADC_5 = CG_ADCS_PREFIX + "adc5";
    public static final String REDOX = CG_ELECTRODES_PREFIX + "redox";
    public static final String PH = CG_ELECTRODES_PREFIX + "ph";
    public static final String TEMPERATURE_1 = CG_TEMPERATURES_PREFIX + "temperature1";
    public static final String TEMPERATURE_2 = CG_TEMPERATURES_PREFIX + "temperature2";
    public static final String TEMPERATURE_3 = CG_TEMPERATURES_PREFIX + "temperature3";
    public static final String TEMPERATURE_4 = CG_TEMPERATURES_PREFIX + "temperature4";
    public static final String TEMPERATURE_5 = CG_TEMPERATURES_PREFIX + "temperature5";
    public static final String TEMPERATURE_6 = CG_TEMPERATURES_PREFIX + "temperature6";
    public static final String TEMPERATURE_7 = CG_TEMPERATURES_PREFIX + "temperature7";
    public static final String TEMPERATURE_8 = CG_TEMPERATURES_PREFIX + "temperature8";
    public static final String RELAIS_1 = CG_RELAIS_PREFIX + "relais1";
    public static final String RELAIS_2 = CG_RELAIS_PREFIX + "relais2";
    public static final String RELAIS_3 = CG_RELAIS_PREFIX + "relais3";
    public static final String RELAIS_4 = CG_RELAIS_PREFIX + "relais4";
    public static final String RELAIS_5 = CG_RELAIS_PREFIX + "relais5";
    public static final String RELAIS_6 = CG_RELAIS_PREFIX + "relais6";
    public static final String RELAIS_7 = CG_RELAIS_PREFIX + "relais7";
    public static final String RELAIS_8 = CG_RELAIS_PREFIX + "relais8";
    public static final String DIGITAL_INPUT_1 = CG_DIGITAL_INPUT_PREFIX + "digitalInput1";
    public static final String DIGITAL_INPUT_2 = CG_DIGITAL_INPUT_PREFIX + "digitalInput2";
    public static final String DIGITAL_INPUT_3 = CG_DIGITAL_INPUT_PREFIX + "digitalInput3";
    public static final String DIGITAL_INPUT_4 = CG_DIGITAL_INPUT_PREFIX + "digitalInput4";
    public static final String EXTERNAL_RELAIS_1 = CG_EXTERNAL_RELAIS_PREFIX + "relais1";
    public static final String EXTERNAL_RELAIS_2 = CG_EXTERNAL_RELAIS_PREFIX + "relais2";
    public static final String EXTERNAL_RELAIS_3 = CG_EXTERNAL_RELAIS_PREFIX + "relais3";
    public static final String EXTERNAL_RELAIS_4 = CG_EXTERNAL_RELAIS_PREFIX + "relais4";
    public static final String EXTERNAL_RELAIS_5 = CG_EXTERNAL_RELAIS_PREFIX + "relais5";
    public static final String EXTERNAL_RELAIS_6 = CG_EXTERNAL_RELAIS_PREFIX + "relais6";
    public static final String EXTERNAL_RELAIS_7 = CG_EXTERNAL_RELAIS_PREFIX + "relais7";
    public static final String EXTERNAL_RELAIS_8 = CG_EXTERNAL_RELAIS_PREFIX + "relais8";
    public static final String CHLORINE_REMAINING = CG_DOSING_PREFIX + "chlorineRemaining";
    public static final String CHLORINE_USAGE = CG_DOSING_PREFIX + "chlorineUsage";
    public static final String PH_MINUS_REMAINING = CG_DOSING_PREFIX + "phMinusRemaining";
    public static final String PH_MINUS_USAGE = CG_DOSING_PREFIX + "phMinusUsage";
    public static final String PH_PLUS_REMAINING = CG_DOSING_PREFIX + "phPlusRemaining";
    public static final String PH_PLUS_USAGE = CG_DOSING_PREFIX + "phPlusUsage";
}
