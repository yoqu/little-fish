package org.yoqu.fish.common.codec.compress;

/**
 * @author yoqu
 * @date 2018/2/5 - 下午2:57
 */
public class SnappyCompress implements Compress {
    @Override
    public byte[] compress(byte[] bytes) {
        return new byte[0];
    }

    @Override
    public byte[] unCompress(byte[] bytes) {
        return new byte[0];
    }
}
