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
package org.openhab.binding.proconip.internal.relaisfilter;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.openhab.core.library.types.OnOffType;

/**
 * Keeps the state of a relais and the time, when the relay was switched on for the last time.
 *
 * @author Martin Renner - Initial contribution.
 */
public class RelaisState {
    private final String relaisId;
    private OnOffType state;
    private Instant lastOnTime;

    public RelaisState(String relaisId) {
        this.relaisId = relaisId;
    }

    public String getRelaisId() {
        return relaisId;
    }

    public OnOffType getState() {
        return state;
    }

    public void setState(OnOffType state) {
        if (this.state != OnOffType.ON && state == OnOffType.ON) {
            lastOnTime = Instant.now();
        }
        this.state = state;
    }

    public boolean isOnForAtLeast(int minutes) {
        if (state != OnOffType.ON || lastOnTime == null) {
            return false;
        }

        return !(lastOnTime.plus(minutes, ChronoUnit.MINUTES).isAfter(Instant.now()));
    }
}
