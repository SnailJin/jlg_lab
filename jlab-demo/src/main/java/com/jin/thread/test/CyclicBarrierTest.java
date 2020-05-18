package com.jin.thread.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 循环栅栏
 * @author jinlg
 * @date 2020/5/14 16:06
 */
public class CyclicBarrierTest extends Thread{
    private CyclicBarrier cyclicBarrier;
    private Integer i;
    public CyclicBarrierTest(CyclicBarrier cyclicBarrier,Integer i){
        this.cyclicBarrier = cyclicBarrier;
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println("开始导入数据："+i);
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    static class  ThreadDemo extends Thread{
        @Override
        public void run() {
            System.out.println("运行任务");
        }
    }
    public static void main(String[] args) {
        int num =3;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(num,new ThreadDemo());
        for(int i =0; i<num ;i++){
            new CyclicBarrierTest(cyclicBarrier,i).start();
        }
    }
}
