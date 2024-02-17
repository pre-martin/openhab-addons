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

import java.net.URI;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * Data required to connect to the ProConIp.
 *
 * @author Martin Renner - Initial contribution
 */
@NonNullByDefault
public class ConnectionInformation {
    private final URI baseUrl;

    /**
     * Creates a new instance.
     *
     * @param hostname The hostname that should be used to connect to the device.
     * @throws IllegalArgumentException If the hostname is not a valid URI.
     */
    public ConnectionInformation(String hostname) {
        String host;
        if (hostname.startsWith("http://") || hostname.startsWith("https://")) {
            host = hostname;
        } else {
            host = "http://" + hostname;
        }

        this.baseUrl = URI.create(host);
    }

    public URI getBaseUrl() {
        return baseUrl;
    }
}
