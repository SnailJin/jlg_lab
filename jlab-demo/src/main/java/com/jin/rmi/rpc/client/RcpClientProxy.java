package com.jin.rmi.rpc.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class RcpClientProxy {
    private String host;
    private int post;

    public RcpClientProxy(String host, int post) {
        this.host = host;
        this.post = post;
    }

    public <T> T clientProxy(final Class<T> interfaceCls){

        return (T)Proxy.newProxyInstance(interfaceCls.getClassLoader(),new Class[]{interfaceCls},
                new RemoteInvocatHander(this.host,this.post
    ));
    }

}
