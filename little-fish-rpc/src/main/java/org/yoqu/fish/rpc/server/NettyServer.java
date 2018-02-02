package org.yoqu.fish.rpc.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yoqu.fish.config.ServerConfig;
import org.yoqu.fish.config.ServerState;
import org.yoqu.fish.rpc.Router;
import org.yoqu.fish.rpc.coder.DeCoder;
import org.yoqu.fish.rpc.coder.EnCoder;
import org.yoqu.fish.rpc.handler.ProcessHandler;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author yoqu
 * @date 2018/2/2 - 下午3:23
 */
public class NettyServer extends AbstractServer {

    public static final Logger logger = LoggerFactory.getLogger(NettyServer.class);

    protected AtomicReference<ServerState> serverStateRef;

    private Channel channel;
    private EventLoopGroup boss;
    private EventLoopGroup worker;
    private ServerBootstrap bootstrap;


    public void init(Router router, ServerConfig serverConfig) {
        this.router = router;
        this.serverConfig = serverConfig;
        serverStateRef = new AtomicReference<ServerState>(ServerState.CREATED);
        boss = new NioEventLoopGroup();
        worker = new NioEventLoopGroup();
        bootstrap = new ServerBootstrap();
        bootstrap.group(boss, worker).channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, serverConfig.isKeepAlive())
                .option(ChannelOption.SO_BACKLOG, serverConfig.getSoBackLog())
                .option(ChannelOption.TCP_NODELAY, serverConfig.isTcpNoDelay())
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(newChannelInitializer(router));

    }

    public ChannelInitializer newChannelInitializer(final Router router) {
        return new ChannelInitializer() {
            @Override
            protected void initChannel(Channel channel) throws Exception {
                channel.pipeline().addLast("decoder", new DeCoder());
                channel.pipeline().addLast("encoder", new EnCoder());
                channel.pipeline().addLast("processor", new ProcessHandler(router));
            }
        };
    }

    public boolean start() {
        if (!serverStateRef.compareAndSet(ServerState.CREATED, ServerState.STARTING)) {
            throw new IllegalStateException("Server already started");
        }
        ChannelFuture f = bootstrap.bind(serverConfig.getPort());
        logger.info("Server start success,listen port:{}", serverConfig.getPort());
        serverStateRef.set(ServerState.STARTED);
        this.channel = f.channel();
        channel.closeFuture();
        return true;
    }

    public boolean stop() {
        if (!serverStateRef.compareAndSet(ServerState.ShutDown, ServerState.STARTING)) {
            throw new IllegalStateException("Server already stop");
        } else {
            try {
                channel.close().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
        logger.info("server stop success.");
        return true;
    }


}
