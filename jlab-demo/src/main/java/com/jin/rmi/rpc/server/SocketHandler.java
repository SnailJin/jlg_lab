package com.jin.rmi.rpc.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;
import com.jin.rmi.rpc.client.RpcRequest;

public class SocketHandler implements  Runnable {

    private int port;
    private Object server;

    public SocketHandler(Object server,int port) {
        this.port = port;
        this.server = server;
    }

    @Override
    public void run() {
        System.out.println("启动连接");
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(this.port);
            Socket socket = serverSocket.accept();
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest result = (RpcRequest) objectInputStream.readObject();

            Class<?> clazz = server.getClass();
            Object[] args= result.getParamters();
            Class<?>[] types = new Class[args.length];
            for(int i =0;i<args.length;i++){
                types[i] = args[i].getClass();
            }
            Method method = clazz.getMethod(result.getMenthodName(),types);
            Object object =  method.invoke(server,args);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            return ;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
