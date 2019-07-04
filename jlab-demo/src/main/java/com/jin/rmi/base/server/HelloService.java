package com.jin.rmi.base.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloService extends Remote {
    public String syaHello(String name) throws RemoteException;

}
