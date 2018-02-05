package org.yoqu.fish.common.codec.compress;

/**
 * @author yoqu
 * @date 2018/2/5 - 下午2:55
 */
public class NoCompress implements Compress {
    @Override
    public byte[] compress(byte[] bytes) {
        return bytes;
    }

    @Override
    public byte[] unCompress(byte[] bytes) {
        return bytes;
    }
}
