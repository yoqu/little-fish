package org.yoqu.fish.common.codec.compress;

/**
 * @author yoqu
 * @date 2018/2/5 - 下午2:53
 */
public interface Compress {

    byte[] compress(byte[] bytes);

    byte[] unCompress(byte[] bytes);

}
