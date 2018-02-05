package org.yoqu.fish.common;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yoqu
 * @date 2018/2/5 - 下午4:01
 */
public class ServerInfo<T> {
    private String host;
    private int port;
    private T client;

    private AtomicInteger activeCount = new AtomicInteger(0);

    public ServerInfo() {

    }

    public ServerInfo(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public T getClient() {
        return client;
    }

    public void setClient(T client) {
        this.client = client;
    }

    public AtomicInteger getActiveCount() {
        return activeCount;
    }

    public void setActiveCount(AtomicInteger activeCount) {
        this.activeCount = activeCount;
    }
}
