package com.test.java.jvm;

import java.util.ArrayList;
import java.util.List;

public class Kafka {

    private static  List<RelicaManger>  list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException, ClassNotFoundException {
        Class classN = Class.forName("com.test.java.jvm.RelicaManger",false,Thread.currentThread().getContextClassLoader());
//        Class classN = Kafka.class.getClassLoader().loadClass("com.test.java.jvm.RelicaManger");
        System.out.println(classN);
//        while (true){
//            loadRelicasFromDisk();
//            Thread.sleep(1000L);
//        }
    }
    private static void loadRelicasFromDisk(){
        RelicaManger relicaManger = new RelicaManger();
        list.add(relicaManger);
    }
}
