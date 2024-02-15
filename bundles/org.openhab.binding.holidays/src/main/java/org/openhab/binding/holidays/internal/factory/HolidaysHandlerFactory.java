/**
 * Copyright (c) 2010-2022 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional information.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License 2.0
 * which is available at http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.holidays.internal.factory;

import static org.openhab.binding.holidays.HolidaysBindingConstants.THING_TYPE_PUBLIC_HOLIDAYS;
import static org.openhab.binding.holidays.HolidaysBindingConstants.THING_TYPE_SCHOOL_HOLIDAYS;

import java.util.Set;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.holidays.internal.handler.PublicHolidaysThingHandler;
import org.openhab.binding.holidays.internal.handler.SchoolHolidaysThingHandler;
import org.openhab.core.scheduler.CronScheduler;
import org.openhab.core.thing.Thing;
import org.openhab.core.thing.ThingTypeUID;
import org.openhab.core.thing.binding.BaseThingHandlerFactory;
import org.openhab.core.thing.binding.ThingHandler;
import org.openhab.core.thing.binding.ThingHandlerFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * The {@link HolidaysHandlerFactory} is responsible for creating things and thing
 * handlers.
 *
 * @author Martin Renner
 */
@NonNullByDefault
@Component(configurationPid = "binding.holidays", service = ThingHandlerFactory.class)
public class HolidaysHandlerFactory extends BaseThingHandlerFactory {

    private final static Set<ThingTypeUID> SUPPORTED_THING_TYPES_UIDS = Set.of(THING_TYPE_PUBLIC_HOLIDAYS,
            THING_TYPE_SCHOOL_HOLIDAYS);
    private final CronScheduler cronScheduler;

    @Activate
    public HolidaysHandlerFactory(@Reference CronScheduler cronScheduler) {
        this.cronScheduler = cronScheduler;
    }

    @Override
    public boolean supportsThingType(ThingTypeUID thingTypeUID) {
        return SUPPORTED_THING_TYPES_UIDS.contains(thingTypeUID);
    }

    @Override
    protected @Nullable ThingHandler createHandler(Thing thing) {
        ThingTypeUID thingTypeUID = thing.getThingTypeUID();

        if (THING_TYPE_PUBLIC_HOLIDAYS.equals(thingTypeUID)) {
            return new PublicHolidaysThingHandler(thing, cronScheduler);
        } else if (THING_TYPE_SCHOOL_HOLIDAYS.equals(thingTypeUID)) {
            return new SchoolHolidaysThingHandler(thing, cronScheduler);
        }

        return null;
    }
}
