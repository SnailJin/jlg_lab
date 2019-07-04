package com.jin.tool.util;

import java.util.Calendar;

public class Test {
    public static  void testIdUtils(){
            System.out.println(System.currentTimeMillis());
//        IDUtils idWorker = new IDUtils(1, 1);
            IDUtils idWorker = new IDUtils();
            idWorker.IDUtils();
            long startTime = System.nanoTime();
            for (int i = 0; i < 2; i++) {
                Long id = idWorker.nextId();
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(idWorker.getTimeMillis(id));
                System.out.println(DateTools.format(calendar.getTime())+":getTimeMillis");
                System.out.println(Long.toBinaryString(id)+":id");
                System.out.println(id);
            }
            System.out.println((System.nanoTime() - startTime) / 1000000 + "ms");
        }


    public static void main(String[] args) {
        testIdUtils();
    }
}
