package com.jin.rmi.client;

import com.jin.rmi.base.server.HelloService;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class HelloClient {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        HelloService helloService = (HelloService) Naming.lookup("rmi://127.0.0.1/hello");
        System.out.printf(helloService.syaHello("jin"));
    }
}
