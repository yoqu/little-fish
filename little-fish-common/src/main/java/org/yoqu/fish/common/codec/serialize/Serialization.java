package org.yoqu.fish.common.codec.serialize;

/**
 * @author yoqu
 * @date 2018/2/5 - 下午2:33
 */
public interface Serialization {

    byte[] enSerialize(Object obj);

    <T> T deSerialize(byte[] bytes, Class<T> clz);

}
