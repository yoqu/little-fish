package org.yoqu.fish.rpc;

public interface Router {
    ActionMethod router(String uri);
}
