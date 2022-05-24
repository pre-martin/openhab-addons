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
package org.openhab.binding.proconip.internal;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * This exception is thrown for all kind of communication errors.
 *
 * @author Martin Renner - Initial contribution
 */
@NonNullByDefault
public class ProConIpCommunicationException extends RuntimeException {

    private final static long serialVersionUID = 1L;

    public ProConIpCommunicationException(String msg) {
        super(msg);
    }

    public ProConIpCommunicationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
