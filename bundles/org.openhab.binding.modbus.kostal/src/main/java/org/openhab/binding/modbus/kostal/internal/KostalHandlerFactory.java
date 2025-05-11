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
package org.openhab.binding.modbus.kostal.internal;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.modbus.kostal.internal.ksem.handler.KostalKsemHandler;
import org.openhab.binding.modbus.kostal.internal.plenticore.handler.KostalPlenticoreHandler;
import org.openhab.core.thing.Thing;
import org.openhab.core.thing.ThingTypeUID;
import org.openhab.core.thing.binding.BaseThingHandlerFactory;
import org.openhab.core.thing.binding.ThingHandler;
import org.openhab.core.thing.binding.ThingHandlerFactory;
import org.osgi.service.component.annotations.Component;

/**
 * The {@link KostalHandlerFactory} is responsible for creating things and thing
 * handlers.
 *
 * @author Martin Renner - Initial contribution
 */
@NonNullByDefault
@Component(configurationPid = "binding.kostal", service = ThingHandlerFactory.class)
public class KostalHandlerFactory extends BaseThingHandlerFactory {

    @Override
    public boolean supportsThingType(ThingTypeUID thingTypeUID) {
        return KostalBindingConstants.THING_TYPE_KOSTAL_PLENTICORE.equals(thingTypeUID)
                || KostalBindingConstants.THING_TYPE_KOSTAL_KSEM.equals(thingTypeUID);
    }

    @Override
    protected @Nullable ThingHandler createHandler(Thing thing) {
        ThingTypeUID thingTypeUID = thing.getThingTypeUID();

        if (KostalBindingConstants.THING_TYPE_KOSTAL_PLENTICORE.equals(thingTypeUID)) {
            return new KostalPlenticoreHandler(thing);
        } else if (KostalBindingConstants.THING_TYPE_KOSTAL_KSEM.equals(thingTypeUID)) {
            return new KostalKsemHandler(thing);
        }

        return null;
    }
}
