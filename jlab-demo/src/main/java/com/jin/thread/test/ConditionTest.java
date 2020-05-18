package com.jin.thread.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition 等待唤醒 的处理
 * @author jinlg
 * @date 2020/5/14 13:51
 */
public class ConditionTest {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(new ConditionWait(lock,condition)).start();
        new Thread(new ConditionSingal(lock,condition)).start();
    }

    static class ConditionWait implements Runnable{
        ReentrantLock lock;
        Condition condition;
        public ConditionWait(ReentrantLock lock,Condition condition){
            this.lock = lock ;
            this.condition = condition;
        }
        @Override
        public void run() {
            try{
                System.out.println("condition.await start");
                lock.lock();
                condition.await();
                System.out.println("condition.await end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    static class ConditionSingal implements Runnable{
        ReentrantLock lock;
        Condition condition;
        public ConditionSingal(ReentrantLock lock,Condition condition){
            this.lock = lock ;
            this.condition = condition;
        }
        @Override
        public void run() {
            try{
                System.out.println("condition.signal start");
                lock.lock();
                condition.signal();
                System.out.println("condition.signal end");
            }finally {
                lock.unlock();
            }
        }
    }
}
