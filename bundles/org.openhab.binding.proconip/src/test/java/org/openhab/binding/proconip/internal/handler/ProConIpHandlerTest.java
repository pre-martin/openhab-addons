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
package org.openhab.binding.proconip.internal.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.eclipse.jetty.client.HttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.openhab.core.thing.Thing;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ProConIpHandlerTest {

    private ProConIpHandler proConIpHandler;

    private @Mock Thing thingMock;

    private @Mock HttpClient httpClientMock;

    @BeforeEach
    public void setUp() {
        proConIpHandler = new ProConIpHandler(thingMock, httpClientMock);
    }

    @Test
    public void testUptime() {
        assertEquals("0h 0m 10s", proConIpHandler.uptimeToDays(10));
        assertEquals("0h 3m 30s", proConIpHandler.uptimeToDays(3 * 60 + 30));
        assertEquals("3h 19m 59s", proConIpHandler.uptimeToDays(3 * 60 * 60 + 19 * 60 + 59));
    }
}
