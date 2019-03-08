package com.jin.rmi.base.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServerRun {

    public static void main(String[] args) throws RemoteException, MalformedURLException {
        HelloService helloService = new HellloServerImpl();
        LocateRegistry.createRegistry(1099);
        Naming.rebind("rmi://127.0.0.1/hello",helloService);
        System.out.printf("启动成功");
    }
}
