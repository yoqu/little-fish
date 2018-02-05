package org.yoqu.fish.rpc.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.yoqu.fish.common.ServerInfo;
import org.yoqu.fish.rpc.coder.DeCoder;
import org.yoqu.fish.rpc.coder.EnCoder;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author yoqu
 * @date 2018/2/5 - 下午3:58
 */
public class NettyClient implements Closeable {

    protected Bootstrap bootstrap;
    protected EventLoopGroup group;
    protected String host;
    protected int port;

    public NettyClient(ServerInfo serverInfo) {
        this.host = serverInfo.getHost();
        this.port = serverInfo.getPort();
        init();
    }

    private void init() {
        bootstrap = new Bootstrap();
        group = new NioEventLoopGroup(4);
        bootstrap.group(group)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        initClientChannel(channel);
                    }
                });
    }

    private void initClientChannel(SocketChannel channel) {
        channel.pipeline().addLast("encode", new EnCoder());
        channel.pipeline().addLast("decode", new DeCoder());
        channel.pipeline().addLast("handler", new ClientHandler());
    }

    public ChannelFuture connect() {
        ChannelFuture connect = bootstrap.connect(host, port);
        connect.awaitUninterruptibly();
        return connect;
    }

    @Override
    public void close() throws IOException {

    }
}
