package com.atguigu.MyJUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Cake{

     private int number=0;
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();
     public void custom() throws InterruptedException {
        lock.lock();
        try{
            while(number!=0){
               // this.wait();
                condition.await();

            }
            number++;
            System.out.println(Thread.currentThread().getName()+"      "+number);
            //this.notifyAll();
            condition.signalAll();
        }finally{
            lock.unlock();
        }

     }
     public  void buy() throws InterruptedException {

         lock.lock();
         try{

             while(number==0){
                 //this.wait();
                 condition.await();

             }
             number--;
             System.out.println(Thread.currentThread().getName()+"      "+number);
             //this.notifyAll()
             condition.signalAll();
         }finally{
             lock.unlock();
         }
       ;
     }

 }


public class WaitofNotify {
    //生产者与消费者线程问题
    //轮番加减 10轮
    //多线程存在虚假唤醒问题
    public static void main(String[] args) {

        Cake cake=new Cake();

        new Thread(()->{
            try {
                for (int i = 0; i <10 ; i++) {
                    cake.custom();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(()->{
            try {
                for (int i = 0; i <10 ; i++) {
                    cake.buy();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
        new Thread(()->{
            try {
                for (int i = 0; i <10 ; i++) {
                    cake.custom();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"C").start();

        new Thread(()->{
            try {
                for (int i = 0; i <10 ; i++) {
                    cake.buy();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"D").start();
    }


}
