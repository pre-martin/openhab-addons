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
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.holidays.internal.impl.SchoolHolidaysManager;
import org.openhab.core.config.core.Configuration;
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.scheduler.CronScheduler;
import org.openhab.core.thing.*;
import org.openhab.core.types.Command;
import org.openhab.core.types.RefreshType;
import org.openhab.core.types.State;

/**
 * The {@link SchoolHolidaysThingHandler} is responsible for handling school holidays.
 *
 * @author Martin Renner
 */
@NonNullByDefault
public class SchoolHolidaysThingHandler extends AbstractHolidaysThingHandler {

    private @Nullable SchoolHolidaysManager schoolHolidaysManager;
    private @Nullable ScheduledFuture<?> reloadJob;

    public SchoolHolidaysThingHandler(Thing thing, CronScheduler cronScheduler) {
        super(thing, cronScheduler);
    }

    @Override
    public void initialize() {
        final ThingUID thingUID = getThing().getUID();
        Configuration configuration = getThing().getConfiguration();

        logger.info("Initializing thing {} with configuration {}", thingUID, configuration);

        boolean configComplete = true;

        final String holidaysSchoolFile = StringUtils.trimToNull((String) configuration.get("file"));
        if (holidaysSchoolFile == null) {
            logger.error("Holidays parameter 'file' is mandatory, thing {} will be disabled.", thingUID);
            configComplete = false;
        }

        if (configComplete) {
            // Daily job to update the channel at midnight.
            deleteDailyJob();
            addDailyJob();

            var manager = new SchoolHolidaysManager(this, holidaysSchoolFile);
            manager.init();
            schoolHolidaysManager = manager;
            // Reload job to check if holidays file was modified.
            deleteReloadJob();
            addReloadJob();

            updateStatus(ThingStatus.ONLINE);
        } else {
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.CONFIGURATION_ERROR);
        }

        logger.debug("Initialized thing {} with status {}", thingUID, getThing().getStatus());
    }

    @Override
    protected void disposeInternal() {
        deleteReloadJob();
    }

    private void addReloadJob() {
        reloadJob = scheduler.scheduleAtFixedRate(() -> {
            var manager = schoolHolidaysManager;
            if (manager != null) {
                manager.readFileIfChanged();
            }
        }, 1, 1, TimeUnit.MINUTES);
    }

    private void deleteReloadJob() {
        var job = reloadJob;
        if (job != null) {
            job.cancel(true);
        }
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        logger.debug("handleCommand for channel {} with command {}", channelUID, command);
        if (command == RefreshType.REFRESH) {
            // Refresh the (newly) linked channel.
            State state = isSchoolHoliday() ? OnOffType.ON : OnOffType.OFF;
            publishChannelState(channelUID, state);
        }
    }

    private boolean isSchoolHoliday() {
        var manager = schoolHolidaysManager;
        if (manager == null) {
            return false;
        }

        LocalDate today = LocalDate.now();
        return manager.isSchoolHoliday(today);
    }

    @Override
    public void fireEvents() {
        State state = isSchoolHoliday() ? OnOffType.ON : OnOffType.OFF;
        for (Channel channel : thing.getChannels()) {
            publishChannelState(channel.getUID(), state);
        }
    }
}
