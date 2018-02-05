package org.yoqu.fish.common;

/**
 * @author yoqu
 * @date 2018/2/5 - 下午2:07
 */
public enum EventType {
    NORMAL((byte) 0), HEARTBEAT((byte) (1 << 6));
    private byte value;

    EventType(byte value) {
        this.value = value;
    }

    public static boolean isHeartBeat(byte extend) {
        if ((extend & HEARTBEAT.getValue()) == HEARTBEAT.getValue()) {
            return true;
        }
        return false;

    }

    public byte getValue() {
        return value;
    }
}
