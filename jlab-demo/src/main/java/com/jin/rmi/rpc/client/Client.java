package com.jin.rmi.rpc.client;


public class Client {
    public static void main(String[] args) {
        RcpClientProxy rcpClientProxy = new RcpClientProxy("127.0.0.1",8888);
        RpcHello rpcHello = rcpClientProxy.clientProxy(RpcHello.class);
        System.out.printf(rpcHello.sayHello("jin"));
    }
}
