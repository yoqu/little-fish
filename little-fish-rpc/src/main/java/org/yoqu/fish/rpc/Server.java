package org.yoqu.fish.rpc;

public interface Server {
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
