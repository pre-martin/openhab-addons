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
package org.openhab.binding.modbus.kostal.internal.ksem;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * The {@link KostalKsemConfiguration} class contains fields mapping thing configuration parameters.
 *
 * @author Martin Renner - Initial contribution
 */
@NonNullByDefault
public class KostalKsemConfiguration {

    /** Refresh interval in seconds */
    public long refresh = 30;

    public long getRefreshMillis() {
        return refresh * 1000;
    }
}
