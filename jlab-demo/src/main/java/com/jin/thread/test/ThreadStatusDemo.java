package com.jin.thread.test;

import java.util.concurrent.TimeUnit;

/**
 * 线程各个状态判断
 * @author jinlg
 * @date 2020/5/9 16:30
 */
public class ThreadStatusDemo {
    public static void main(String[] args) {
        new Thread(()->{
            while (true){
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Timed_Waiting_Thread").start();

        new Thread(()->{
            while (true){
                synchronized (ThreadStatusDemo.class){
                    try {
                        ThreadStatusDemo.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"Waiting_Thread").start();
        new Thread(new BlockedDemo(),"Block01_Thread").start();
        new Thread(new BlockedDemo(),"Block02_Thread").start();
    }
    static class BlockedDemo extends Thread{
        @Override
        public void run() {
            synchronized(BlockedDemo.class){
                while (true){
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}
