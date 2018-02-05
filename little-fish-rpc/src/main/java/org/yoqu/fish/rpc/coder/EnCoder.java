package org.yoqu.fish.rpc.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.yoqu.fish.common.CompressType;
import org.yoqu.fish.common.EventType;
import org.yoqu.fish.common.SerializeType;
import org.yoqu.fish.common.codec.Header;
import org.yoqu.fish.common.codec.Message;
import org.yoqu.fish.common.codec.compress.Compress;
import org.yoqu.fish.common.codec.serialize.Serialization;

public class EnCoder extends MessageToByteEncoder<Message> {

    protected void encode(ChannelHandlerContext channelHandlerContext, Message message, ByteBuf byteBuf) throws Exception {
        Header header = message.getHeader();
        byteBuf.writeShort(header.getMagic());
        byteBuf.writeByte(header.getVersion());
        byteBuf.writeByte(header.getExtend());
        byteBuf.writeLong(header.getSeq());
        Object content = message.getContent();
        if (content == null && EventType.isHeartBeat(header.getExtend())) {
            byteBuf.writeInt(0);
            return;
        }
        Serialization serialization = SerializeType.getSerializeByExtend(header.getExtend());
        Compress compress = CompressType.getCompressByExtend(header.getExtend());
        byte[] payload = compress.compress(serialization.enSerialize(message.getContent()));
        byteBuf.writeBytes(payload);
    }
}
