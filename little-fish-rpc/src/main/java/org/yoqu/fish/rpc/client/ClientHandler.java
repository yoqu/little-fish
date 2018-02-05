package org.yoqu.fish.rpc.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.yoqu.fish.common.codec.Message;
import org.yoqu.fish.common.codec.Response;

/**
 * @author yoqu
 * @date 2018/2/5 - 下午4:11
 */
public class ClientHandler extends SimpleChannelInboundHandler<Message<Response>> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message<Response> responseMessage) throws Exception {

    }
}
