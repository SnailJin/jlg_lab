package com.jin.rmi.rpc.client;

import java.io.Serializable;

public class RpcRequest implements Serializable {
    private static final long serialVersionUID = -6097429071505383134L;
    private String className;
    private String menthodName;
    private Object[] paramters;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMenthodName() {
        return menthodName;
    }

    public void setMenthodName(String menthodName) {
        this.menthodName = menthodName;
    }

    public Object[] getParamters() {
        return paramters;
    }

    public void setParamters(Object[] paramters) {
        this.paramters = paramters;
    }
}
