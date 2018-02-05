package org.yoqu.fish.rpc.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.yoqu.fish.common.*;
import org.yoqu.fish.common.codec.Header;
import org.yoqu.fish.common.codec.Message;
import org.yoqu.fish.common.codec.compress.Compress;
import org.yoqu.fish.common.codec.serialize.Serialization;

import java.util.List;

public class DeCoder extends ByteToMessageDecoder {

    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        //todo convert
        if (byteBuf.readableBytes() < Constants.HEADER_SIZE) {
            return;
        }

        byteBuf.markReaderIndex();
        short magic = byteBuf.readShort();
        if (magic != Constants.MAGIC) {
            //todo modify use the little fish framework exception.
            byteBuf.resetReaderIndex();
            throw new RuntimeException("message header not support.");
        }
        byte version = byteBuf.readByte();
        byte extend = byteBuf.readByte();
        long seq = byteBuf.readLong();
        int size = byteBuf.readInt();
        Object content = null;
        if (!(size == 0 && EventType.isHeartBeat(extend))) {
            if (byteBuf.readableBytes() < size) {
                byteBuf.resetReaderIndex();
                return;
            }
            byte[] payload = new byte[size];
            byteBuf.readBytes(payload);
            Serialization serialization = SerializeType.getSerializeByExtend(extend);
            Compress compress = CompressType.getCompressByExtend(extend);
            content = serialization.deSerialize(compress.unCompress(payload), MessageType.getMessageTypeByExtend(extend));
        }

        Header header = new Header(magic, version, extend, seq, size);
        Message message = new Message(header, content);
        list.add(message);

    }
}
