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
package org.openhab.binding.holidays;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.openhab.core.thing.ThingTypeUID;

/**
 * The {@link HolidaysBindingConstants} class defines common constants, which are
 * used across the whole binding.
 *
 * @author Martin Renner
 */
@NonNullByDefault
public class HolidaysBindingConstants {

    public static final String BINDING_ID = "holidays";

    // List of all Thing Type UIDs
    public final static ThingTypeUID THING_TYPE_PUBLIC_HOLIDAYS = new ThingTypeUID(BINDING_ID, "public");
    public final static ThingTypeUID THING_TYPE_SCHOOL_HOLIDAYS = new ThingTypeUID(BINDING_ID, "school");

    // List of all Channel ids
    public final static String CHANNEL_PUBLIC_HOLIDAY = "publicHoliday";
    public final static String CHANNEL_SCHOOL_HOLIDAY = "schoolHoliday";
}
