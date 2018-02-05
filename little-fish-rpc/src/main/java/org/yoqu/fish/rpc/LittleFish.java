package org.yoqu.fish.rpc;

import org.yoqu.fish.config.ServerConfig;
import org.yoqu.fish.config.ServerRouterType;
import org.yoqu.fish.config.ServerType;
import org.yoqu.fish.rpc.server.FishRouter;
import org.yoqu.fish.rpc.server.NettyServer;

import java.util.List;
import java.util.Objects;

/**
 * @author yoqu
 * @date 2018/2/2 - 下午3:37
 */
public class LittleFish {

    public ServerConfig serverConfig;

    private Server server;

    private Router router;

    private List registerService;

    public LittleFish() {

    }

    public static Builder builder(){
        return new Builder();
    }

    public LittleFish(ServerConfig serverConfig, Server server) {
        this.serverConfig = serverConfig;
        this.server = server;
    }

    public LittleFish(ServerConfig serverConfig, Server server, Router router) {
        this(serverConfig, server);
        this.router = router;
    }

    public boolean start() {
        server.init(router, serverConfig);
        return server.start();
    }

    public static class Builder {

        private ServerConfig serverConfig = new ServerConfig();

        private Server server;

        private Router router;

        public Builder server(ServerType type) {
            if (type == ServerType.NETTY) {
                server = new NettyServer();
            }
            return this;
        }

        public Builder router(ServerRouterType routerType) {
            if (routerType == ServerRouterType.LittleFish) {
                router = new FishRouter();
            }
            return this;
        }

        public Builder port(int port) {
            serverConfig.setPort(port);
            return this;
        }

        public LittleFish build() {
            if (server == null) {
                server = new NettyServer();
            }
            if (router == null) {
                router = new FishRouter();
            }
            return new LittleFish(Objects.requireNonNull(serverConfig), server);
        }

    }
}
