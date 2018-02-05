package org.yoqu.fish.common.codec.serialize;

/**
 * @author yoqu
 * @date 2018/2/5 - 下午2:35
 */
public class FastJsonSerialization implements Serialization {

    @Override
    public byte[] enSerialize(Object obj) {
        return new byte[0];
    }

    @Override
    public <T> T deSerialize(byte[] bytes, Class<T> clz) {
        return null;
    }
}
