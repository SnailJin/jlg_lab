package com.jin.rmi.rpc.server;

public class RpcHelloServerImpl implements RpcHello{

    @Override
    public String sayHello(String name) {
        System.out.println("name = [" + name + "]");
        return "hello "+name;
    }
}
