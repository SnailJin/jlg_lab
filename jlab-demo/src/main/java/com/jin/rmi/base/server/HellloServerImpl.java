package com.jin.rmi.base.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HellloServerImpl extends UnicastRemoteObject implements HelloService {

    protected HellloServerImpl() throws RemoteException {
        super();
    }

    @Override
    public String syaHello(String name) throws  RemoteException{
        System.out.printf("server send success");
        return "hello "+name+"!";
    }
}
