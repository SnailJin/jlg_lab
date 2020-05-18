package com.jin.thread.test;

import java.util.concurrent.Semaphore;

/**
 * 限流
 * 给出固定的令牌数，争抢令牌
 * 通过共享锁实现
 * @author jinlg
 * @date 2020/5/14 15:52
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        for(int i = 0 ;i<10;i++){
            new Car(semaphore,i).start();
        }
    }

    static public class Car extends Thread{
        private Semaphore semaphore;
        private int num;

        public Car(Semaphore semaphore,int num){
            this.semaphore = semaphore;
            this.num = num;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("第"+num+" 抢占一个车位");
                Thread.sleep(100);
                semaphore.release();
                System.out.println("第"+num+" 开走了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
