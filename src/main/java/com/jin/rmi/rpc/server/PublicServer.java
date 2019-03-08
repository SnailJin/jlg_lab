package com.jin.rmi.rpc.server;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PublicServer {

    private ExecutorService  executorService = Executors.newCachedThreadPool();

    private String host;
    private int port;

    public PublicServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void publicIt(Object server){
        executorService.execute(new SocketHandler(server, 8888));
    }
}
