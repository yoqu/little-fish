package org.yoqu.fish.common;

import com.sun.tools.internal.ws.processor.model.Response;
import org.yoqu.fish.common.codec.Request;

/**
 * @author yoqu
 * @date 2018/2/5 - 下午3:39
 */
public enum MessageType {
    REQUEST((byte) 0), RESPONSE((byte) 1);
    private byte value;

    MessageType(byte value) {
        this.value = value;
    }

    public static Class getMessageTypeByExtend(byte value) {
        switch (value & 1 << 7) {
            case 0:
                return Request.class;
            case 1:
                return Response.class;
            default:
                return Request.class;
        }
    }
}
