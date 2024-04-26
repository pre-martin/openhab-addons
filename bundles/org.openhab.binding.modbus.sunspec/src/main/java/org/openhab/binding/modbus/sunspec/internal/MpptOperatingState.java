package org.openhab.binding.modbus.sunspec.internal;

import java.util.Arrays;

/**
 * Possible values for a MPPT operating state.
 *
 * @author Martin Renner
 */
public enum MpptOperatingState {

    OFF(1),
    SLEEPING(2),
    STARTING(3),
    ON(4),
    THROTTLED(5),
    SHUTTING_DOWN(6),
    FAULT(7),
    STANDBY(8),
    TEST(10),
    UNKNOWN(-1);

    private final int code;

    MpptOperatingState(int code) {
        this.code = code;
    }

    public static MpptOperatingState fromCode(int code) {
        return Arrays.stream(MpptOperatingState.values()).filter(s -> s.code == code).findFirst().orElse(UNKNOWN);
    }
}
