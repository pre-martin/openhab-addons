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
package org.openhab.binding.proconip.internal.filter;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.types.State;
import org.openhab.core.types.UnDefType;

/**
 * Applies a filter on a given state. If a given relais is not ON for at least "n" minutes, the state for the
 * channel will be returned as UNDEF.
 *
 * @author Martin Renner - Initial contribution.
 */
@NonNullByDefault
public class RelaisFilter {
    private final Map<String, RelaisState> relaisStates = new HashMap<>();

    public void setRelaisState(String id, OnOffType state) {
        synchronized (relaisStates) {
            if (!relaisStates.containsKey(id)) {
                relaisStates.put(id, new RelaisState(id));
            }

            RelaisState relaisState = relaisStates.get(id);
            if (relaisState != null) {
                relaisState.setState(state);
            }
        }
    }

    public State filterState(@Nullable String relaisFilter, State state) {
        if (relaisFilter == null) {
            return state;
        }

        String[] parts = relaisFilter.split(";");
        if (parts.length != 2) {
            return UnDefType.UNDEF;
        }

        String relaisId = parts[0];
        int requiredMinutes = Integer.parseInt(parts[1]);

        synchronized (relaisStates) {
            RelaisState relaisState = relaisStates.get(relaisId);
            if (relaisState == null) {
                // We do not yet know the state of this relais
                return UnDefType.UNDEF;
            }

            if (relaisState.isOnForAtLeast(requiredMinutes)) {
                return state;
            }
        }

        return UnDefType.UNDEF;
    }
}
