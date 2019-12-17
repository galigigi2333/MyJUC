package com.atguigu.MyJUC;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDome {


    public static void main(String[] args) throws InterruptedException {
        /*
        * 倒计时机制
        *避免线程未进行完主线程先结束
        * */
        CountDownLatch countDownLatch=new CountDownLatch(3);

      for(int i=1;i<=3;i++){
          final int Tamp=i;
          new Thread(()->{

              System.out.println(Thread.currentThread().getName()+"\t"+Tamp);
          countDownLatch.countDown();
      }, String.valueOf(i)).start();
      }

        countDownLatch.await();
        System.out.println("结束");
    }
}
