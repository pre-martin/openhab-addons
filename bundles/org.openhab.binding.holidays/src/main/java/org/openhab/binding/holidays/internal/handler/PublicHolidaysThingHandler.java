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
package org.openhab.binding.holidays.internal.handler;

import java.time.LocalDate;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.openhab.binding.holidays.internal.EventManager;
import org.openhab.binding.holidays.internal.impl.PublicHolidaysManager;
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.scheduler.CronScheduler;
import org.openhab.core.thing.*;
import org.openhab.core.types.Command;
import org.openhab.core.types.RefreshType;
import org.openhab.core.types.State;

/**
 * The {@link PublicHolidaysThingHandler} is responsible for handling public holidays.
 *
 * @author Martin Renner
 */
@NonNullByDefault
public class PublicHolidaysThingHandler extends AbstractHolidaysThingHandler implements EventManager {

    // static instance because public holidays are "constant".
    private static final PublicHolidaysManager publicHolidaysManager = new PublicHolidaysManager();

    public PublicHolidaysThingHandler(Thing thing, CronScheduler cronScheduler) {
        super(thing, cronScheduler);
    }

    @Override
    public void initialize() {
        final ThingUID thingUID = getThing().getUID();

        // Daily job to update the channel at midnight.
        deleteDailyJob();
        addDailyJob();

        updateStatus(ThingStatus.ONLINE);

        logger.debug("Initialized thing {} with status {}", thingUID, getThing().getStatus());
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        logger.debug("handleCommand for channel {} with command {}", channelUID, command);
        if (command == RefreshType.REFRESH) {
            // Refresh the (newly) linked channel.
            State state = isPublicHoliday() ? OnOffType.ON : OnOffType.OFF;
            publishChannelState(channelUID, state);
        }
    }

    private boolean isPublicHoliday() {
        LocalDate today = LocalDate.now();
        return publicHolidaysManager.isPublicHoliday(today);
    }

    @Override
    public void fireEvents() {
        State state = isPublicHoliday() ? OnOffType.ON : OnOffType.OFF;
        for (Channel channel : thing.getChannels()) {
            publishChannelState(channel.getUID(), state);
        }
    }
}
