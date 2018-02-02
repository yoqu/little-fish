package org.yoqu.fish.rpc.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.yoqu.fish.common.codec.Message;

public class EnCoder extends MessageToByteEncoder<Message> {


    protected void encode(ChannelHandlerContext channelHandlerContext, Message message, ByteBuf byteBuf) throws Exception {
        //todo convert the message header set serializer type.
    }
}
