/**
 * Copyright (c) 2010-2023 Contributors to the openHAB project
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
package org.openhab.binding.modbus.sunspec.internal.handler;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.openhab.binding.modbus.sunspec.internal.dto.MpptModelBlock;
import org.openhab.binding.modbus.sunspec.internal.parser.MpptModelParser;
import org.openhab.core.io.transport.modbus.ModbusRegisterArray;
import org.openhab.core.thing.Thing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles data received from a SunSpec MPPT.
 *
 * @author Martin Renner
 */
@NonNullByDefault
public class MpptHandler extends AbstractSunSpecHandler {

    private final Logger logger = LoggerFactory.getLogger(MpptHandler.class);
    private final MpptModelParser parser = new MpptModelParser();

    public MpptHandler(Thing thing) {
        super(thing);
    }

    @Override
    protected void handlePolledData(ModbusRegisterArray registers) {
        logger.trace("Model block received, size: {}", registers.size());

        MpptModelBlock block = parser.parse(registers);

        resetCommunicationError();
    }
}
