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

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.holidays.internal.EventManager;
import org.openhab.core.scheduler.CronScheduler;
import org.openhab.core.scheduler.ScheduledCompletableFuture;
import org.openhab.core.thing.ChannelUID;
import org.openhab.core.thing.Thing;
import org.openhab.core.thing.binding.BaseThingHandler;
import org.openhab.core.types.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Martin Renner
 */
@NonNullByDefault
public abstract class AbstractHolidaysThingHandler extends BaseThingHandler implements EventManager {
    private static final String DAILY_MIDNIGHT = "30 0 0 * * ? *";

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private final CronScheduler cronScheduler;
    private @Nullable ScheduledCompletableFuture<Void> dailyJob;

    protected AbstractHolidaysThingHandler(Thing thing, CronScheduler cronScheduler) {
        super(thing);
        this.cronScheduler = cronScheduler;
    }

    @Override
    public void dispose() {
        super.dispose();

        logger.debug("Disposing thing {}", getThing().getUID());
        deleteDailyJob();
        disposeInternal();
        logger.debug("Disposed thing {}", getThing().getUID());
    }

    /**
     * Can be used to add additional actions to "dispose".
     */
    protected void disposeInternal() {
        // empty
    }

    protected void addDailyJob() {
        dailyJob = cronScheduler.schedule(this::fireEvents, DAILY_MIDNIGHT);
    }

    protected void deleteDailyJob() {
        var job = dailyJob;
        if (job != null) {
            job.cancel(true);
        }
    }

    protected void publishChannelState(ChannelUID channelUID, State state) {
        logger.debug("Updating channel {} with state {}", channelUID, state);
        updateState(channelUID, state);
    }
}
