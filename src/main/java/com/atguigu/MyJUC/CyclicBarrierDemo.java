package com.atguigu.MyJUC;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        /*
        * 计数机制  线程进行完规定数量后即可执行其它线程
        *
        * */

        CyclicBarrier cyclicBarrier=new CyclicBarrier(5,()->{
            System.out.println("收集结束");
        });

        for(int i=1;i<=7;i++){
            final int Tmap=i;
            new Thread(()->{
                try {
                   System.out.println(Thread.currentThread().getName()+"\t收集第"+Tmap+"颗龙珠");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();

        }

    }

}
