package com.jin.thread.test;

import java.util.concurrent.TimeUnit;

/**
 * 线程各个状态判断
 * @author jinlg
 * @date 2020/5/9 16:30
 */
public class ThreadInurrputDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
//                try {
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("before:"+Thread.currentThread().isInterrupted());
                        Thread.currentThread().interrupt();
                        System.out.println("after:"+Thread.currentThread().isInterrupted());
                    }
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }, "ThreadInurrput_Thread");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();

    }
}
