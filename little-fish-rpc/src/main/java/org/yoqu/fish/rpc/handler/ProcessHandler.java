package org.yoqu.fish.rpc.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.yoqu.fish.common.codec.Message;
import org.yoqu.fish.common.codec.Request;
import org.yoqu.fish.rpc.Router;

import java.util.concurrent.Executor;

/**
 * @author yoqu
 * @date 2018/2/2 - 下午2:55
 */
public class ProcessHandler extends SimpleChannelInboundHandler<Message<Request>> {

    private static Executor executor;
    private Router router;

    public ProcessHandler(Router router) {
        this.router = router;
    }

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message<Request> requestMessage) throws Exception {
        //todo add filter invoke service.
    }
}
