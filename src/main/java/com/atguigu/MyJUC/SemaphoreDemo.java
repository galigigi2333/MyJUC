package com.atguigu.MyJUC;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {

    /*
    * 规定执行线程数量
    *  semaphore.acquire() 执行信号 数量减一 收到释放信号 数量还原 否则一直等下去
    *semaphore.release(); 释放信号
    * */
    public static void main(String[] args) {

        Semaphore semaphore=new Semaphore(1);

        for(int i=1;i<=6;i++){

            new Thread(()->{
                boolean flag=false;
                try {
                    semaphore.acquire();
                    flag=true;
                     System.out.println(Thread.currentThread().getName()+"\t"+"抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"\t"+"离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    if (flag){
                        //线程释放信号
                        semaphore.release();
                    }
                }

            }, String.valueOf(i)).start();

        }

    }

}
