package com.atguigu.MyJUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShowPrint{

private int flag=1;
private Lock lock=new ReentrantLock();


Condition c1=lock.newCondition();
Condition c2=lock.newCondition();
Condition c3=lock.newCondition();

public void print5() throws InterruptedException {

    lock.lock();
    try{
        while (flag!=1){
            c1.await();
        }
        for (int i = 0; i <5 ; i++) {
            System.out.println(Thread.currentThread().getName()+"\t" +i);
        }
        flag=2;
        c2.signal();
    }finally{
        lock.unlock();
    }
}
    public void print10() throws InterruptedException {

        lock.lock();
        try{
            while (flag!=2){
                c2.await();

            }
            for (int i = 0; i <10 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t" +i);
            }
            flag=3;
            c3.signal();
        }finally{
            lock.unlock();
        }
    }
    public void print15() throws InterruptedException {

        lock.lock();
        try{
            while (flag!=3){
                c3.await();

            }
            for (int i = 0; i <15 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t" +i);
            }
            flag=1;
            c1.signal();
        }finally{
            lock.unlock();
        }
    }

    public void print() throws InterruptedException {
        lock.lock();
        try{
         if(Thread.currentThread().getName().equals("A")){
              while (flag!=1){
                  c1.await();
              }
               for (int i = 0; i < 5; i++) {
                   System.out.println(Thread.currentThread().getName()+"\t"+i);
               }
               flag=2;
               c2.signal();
           }
          if (Thread.currentThread().getName().equals("B")){
               while (flag!=2){
                   c2.await();
               }
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName()+"\t"+i);
                }
                flag=3;
                c3.signal();
            }
           if(Thread.currentThread().getName().equals("C")){
                while (flag!=3){
                    c3.await();
                }
                for (int i = 0; i < 15; i++) {
                    System.out.println(Thread.currentThread().getName()+"\t"+i);
                }
                flag=1;
                c1.signal();
            }
        }finally{
            lock.unlock();
        }
    }
}




//精确唤醒

public class TestJion {
    public static void main(String[] args) {
        ShowPrint showPrint=new ShowPrint();

        new Thread(()->{
                for (int i = 0; i <10 ; i++) {
                    try {
                            showPrint.print();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                  }
                }
            },"A").start();
        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                try {
                    showPrint.print();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                try {
                    showPrint.print();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
    }
}
