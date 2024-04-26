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
package org.openhab.binding.modbus.sunspec.internal.dto;

import java.util.Collections;
import java.util.List;

/**
 * Model for SunSpec "Multiple MPPT" (model no. 160)
 *
 * @author Martin Renner
 */
public class MpptModelBlock {

    /** Model identifier - always 160 for SunSpec MPPT */
    public Integer modelId;

    /** Length of the block in 16bit words */
    public Integer length;

    /** Current scale factor */
    public Short currentSF;

    public Short voltageSF;

    public Short powerSF;

    public Short energySF;

    public Long events;

    public Integer timestampPeriod;

    public List<MpptModuleBlock> modules = Collections.emptyList();

    public static class MpptModuleBlock {
        public Integer moduleId;

        public String moduleIdString;

        public Integer current;

        public Integer voltage;

        public Integer power;

        public Long lifetimeEnergy;

        public Long timestamp;

        public Short temperature;

        public Integer operatingState;

        public Long events;
    }
}
