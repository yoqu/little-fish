package org.yoqu.fish.rpc.server;

import org.yoqu.fish.rpc.Server;

public class AbstractServer implements Server {

    public boolean start() {
        return false;
    }

    public boolean stop() {
        return false;
    }
}
