package org.yoqu.fish.common.codec.serialize;

/**
 * @author yoqu
 * @date 2018/2/5 - 下午2:36
 */
public class Hessian2Serialization implements Serialization {
    @Override
    public byte[] enSerialize(Object obj) {
        return new byte[0];
    }

    @Override
    public <T> T deSerialize(byte[] bytes, Class<T> clz) {
        return null;
    }
}
