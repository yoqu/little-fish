package org.yoqu.fish.common;

import org.yoqu.fish.common.codec.compress.Compress;
import org.yoqu.fish.common.codec.compress.GZIPCompress;
import org.yoqu.fish.common.codec.compress.NoCompress;
import org.yoqu.fish.common.codec.compress.SnappyCompress;

/**
 * @author yoqu
 * @date 2018/2/5 - 下午2:50
 */

public enum CompressType {

    NORMAL((byte) 0), GZIP((byte) (1 << 3)), SNAPPY((byte) (1 << 4));

    private byte value;

    CompressType(byte value) {
        this.value = value;
    }

    public static CompressType getCompressTypeByName(String name) {
        if (GZIP.name().equalsIgnoreCase(name)) {
            return GZIP;
        } else if (SNAPPY.name().equalsIgnoreCase(name)) {
            return SNAPPY;
        } else if (NORMAL.name().equalsIgnoreCase(name)) {
            return NORMAL;
        } else {
            return null;
        }
    }

    public static Compress getCompressByExtend(byte extend) {
        switch (extend & 24) {
            case 0x0:
                return new NoCompress();
            case 1 << 3:
                return new GZIPCompress();
            case 1 << 4:
                return new SnappyCompress();
            default:
                return new NoCompress();
        }
    }

    public byte getValue() {
        return value;
    }
}
