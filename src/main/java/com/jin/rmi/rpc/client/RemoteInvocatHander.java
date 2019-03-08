package com.jin.rmi.rpc.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class RemoteInvocatHander implements InvocationHandler {
    private String host;
    private int post;

    public RemoteInvocatHander(String host, int post) {
        this.host = host;
        this.post = post;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(proxy.getClass().getName());
        rpcRequest.setMenthodName(method.getName());
        rpcRequest.setParamters(args);
        Socket socket = new Socket(host,post);
        OutputStream os = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(os);
        objectOutputStream.writeObject(rpcRequest);
        objectOutputStream.flush();

        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Object result = objectInputStream.readObject();

        return result;
    }


}
