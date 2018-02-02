package org.yoqu.fish.rpc.server;

import org.yoqu.fish.config.ServerConfig;
import org.yoqu.fish.rpc.Router;
import org.yoqu.fish.rpc.Server;

public abstract class AbstractServer implements Server {


    protected ServerConfig serverConfig;
    protected Router router;

    public abstract void init(Router router, ServerConfig serverConfig);


    public abstract boolean start();

    public abstract boolean stop();
}
