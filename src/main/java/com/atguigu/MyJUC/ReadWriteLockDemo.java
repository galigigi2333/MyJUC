package com.atguigu.MyJUC;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class ReadOrWrite{
    Map<String ,Integer> map=new HashMap<>();
        private Lock lock=new ReentrantLock();
        ReentrantReadWriteLock r=new ReentrantReadWriteLock();

    public void write(String key ,Integer value){
        r.writeLock().lock();
        try{

            System.out.println(Thread.currentThread().getName()+"\t"+"写开始");
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t"+"写结束");

        }finally{
           r.writeLock().unlock();
        }

    }
    public void read(String key){
        System.out.println(Thread.currentThread().getName()+"\t"+"读开始" +map.get(key));

        System.out.println(Thread.currentThread().getName()+"\t"+"读结束");
     /*  r.readLock().lock();
        try{



        }finally{
            r.readLock().unlock();
        }*/

    }

}

/*
* 读写锁
* .writeLock().lock()
* .readLock().lock
* 可以并发读
* 不可以并发写
* */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
       ReadOrWrite r=new ReadOrWrite();

        for(int i=1;i<=6;i++){
            final  int Temp=i;
            new Thread(()->{
                r.write(Thread.currentThread().getName(),Temp);

            }, String.valueOf(i)).start();

        }

        for(int i=1;i<=6;i++){

            new Thread(()->{
                r.read(Thread.currentThread().getName());

            }, String.valueOf(i)).start();

        }
    }




}
