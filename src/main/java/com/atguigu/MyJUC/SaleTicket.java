package com.atguigu.MyJUC;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{
    Lock lock=new ReentrantLock();
    private int number=30;
    public  void sale(){
        lock.lock();
        try {

            if(number>0){
                System.out.println(Thread.currentThread().getName()+"卖出第"+(number--)+"张票"+"还剩"+number+"张票");
            }

        } finally {
            lock.unlock();
        }

    }
public class SaleTicket {


//        public synchronized void slaye(){
//            if(number>0){
//                System.out.println(Thread.currentThread().getName()+"卖出第"+(number--)+"张票"+"还剩"+number+"张票");
//            }
//        }
    }

    public static void main(String[]args){

        ExecutorService threadPool= Executors.newFixedThreadPool(3);
            Ticket ticket=new Ticket();
        try {
            for (int i = 0; i <30 ; i++) {
                threadPool.execute(()->{
                   ticket.sale();
                });
            }
        }finally {
            threadPool.shutdown();
        }



       /*new Thread(()->{ for (int i = 1; i <35 ; i++) ticket.sale(); },"A").start();
        new Thread(()->{ for (int i = 1; i <35 ; i++) ticket.sale(); },"B").start();
        new Thread(()->{ for (int i = 1; i <35 ; i++) ticket.sale(); },"C").start();*/


    }



}
