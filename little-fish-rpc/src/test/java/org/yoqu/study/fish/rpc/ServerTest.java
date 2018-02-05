package org.yoqu.study.fish.rpc;

import org.junit.Assert;
import org.junit.Test;
import org.yoqu.fish.rpc.LittleFish;

/**
 * @author yoqu
 * @date 2018/2/2 - 下午4:08
 */

public class ServerTest {


    @Test
    public void startServer() {
        boolean result = LittleFish.builder()
                .port(8080)
                .build()
                .start();
        Assert.assertEquals(result, true);
    }
}
