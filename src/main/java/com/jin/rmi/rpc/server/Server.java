package com.jin.rmi.rpc.server;

public class Server {
    public static void main(String[] args) throws InterruptedException {
        PublicServer publicServer = new PublicServer("127.0.0.1",8888);
        RpcHello rpcHello = new RpcHelloServerImpl();
        publicServer.publicIt(rpcHello);
        Thread.sleep(1000000);
    }
}
