/**
 * Copyright (c) 2010-2022 Contributors to the openHAB project
 * <p>
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 * <p>
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * <p>
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.proconip.internal.client;

import java.net.URI;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.http.HttpMethod;
import org.openhab.binding.proconip.internal.ConnectionInformation;
import org.openhab.binding.proconip.internal.ProConIpCommunicationException;
import org.openhab.binding.proconip.internal.model.DeviceState;
import org.openhab.binding.proconip.internal.model.Parser;

/**
 * This client handles the communication with the ProConIp.
 *
 * @author Martin Renner - Initial contribution
 */
@NonNullByDefault
public class ProConIpClient {
    private final HttpClient httpClient;
    private final ConnectionInformation conInfo;
    private final int timeout = 5;
    private final Parser parser = new Parser();

    /**
     * Client that handles the http communication with the device.
     *
     * @param httpClient Preconfigured and usable HttpClient.
     * @param conInfo The connection configuration for the device.
     */
    public ProConIpClient(HttpClient httpClient, ConnectionInformation conInfo) {
        this.httpClient = httpClient;
        this.conInfo = conInfo;
    }

    /**
     * Reads the state of the device.
     *
     * @throws ProConIpCommunicationException If there is a problem with the communication.
     * @throws IllegalStateException If the received state is not in the expected format.
     */
    public DeviceState readState() {
        URI url = conInfo.getBaseUrl().resolve("/GetState.csv");
        Request request = httpClient.newRequest(url).timeout(timeout, TimeUnit.SECONDS).method(HttpMethod.GET);

        ContentResponse response;
        try {
            response = request.send();
        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            throw new ProConIpCommunicationException("Could not read state of ProCon.IP: " + e);
        }

        if (response.getStatus() != 200) {
            throw new ProConIpCommunicationException("Could not read state of ProCon.IP. Got response "
                    + response.getStatus() + " " + response.getReason());
        }

        String content = response.getContentAsString();
        return parser.parseState(content);
    }
}
