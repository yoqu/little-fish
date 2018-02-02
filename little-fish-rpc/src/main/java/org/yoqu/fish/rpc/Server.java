package org.yoqu.fish.rpc;

import org.yoqu.fish.config.ServerConfig;

public interface Server {

    void init(Router router, ServerConfig serverConfig);

    /**
     * start the server
     *
     * @return
     */
    boolean start();

    /**
     * stop the server.
     *
     * @return
     */
    boolean stop();

}
