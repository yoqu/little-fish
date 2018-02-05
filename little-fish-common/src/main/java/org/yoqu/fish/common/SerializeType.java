package org.yoqu.fish.common;

import org.yoqu.fish.common.codec.serialize.FastJsonSerialization;
import org.yoqu.fish.common.codec.serialize.Hessian2Serialization;
import org.yoqu.fish.common.codec.serialize.KryoSerialization;
import org.yoqu.fish.common.codec.serialize.Serialization;

/**
 * @author yoqu
 * @date 2018/2/5 - 下午2:21
 */
public enum SerializeType {
    KYRO((byte) 0), FAST_JSON((byte) 1), HESSIAN2((byte) 2);

    private byte value;

    SerializeType(byte value) {
        this.value = value;
    }

    public static SerializeType getSerializeTypeByName(String name) {
        if (name.equalsIgnoreCase(KYRO.name())) {
            return KYRO;
        } else if (name.equalsIgnoreCase(FAST_JSON.name())) {
            return FAST_JSON;
        } else if (name.equalsIgnoreCase(HESSIAN2.name())) {
            return HESSIAN2;
        } else {
            return null;
        }
    }

    public static Serialization getSerializeByExtend(byte value) {
        switch (value & 0x7) {
            case 0x0:
                return new KryoSerialization();
            case 0x1:
                return new FastJsonSerialization();
            case 0x2:
                return new Hessian2Serialization();
            default:
                return new FastJsonSerialization();
        }
    }

    public byte getValue() {
        return value;
    }
}
