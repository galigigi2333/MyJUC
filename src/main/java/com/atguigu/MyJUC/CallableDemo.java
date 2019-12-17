package com.atguigu.MyJUC;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread implements Callable<String >{


    @Override
    public String call() throws Exception {
        System.out.println("************Callable");
        return Thread.currentThread().getName();
    }
}


/*
* Callable 有返回值的线程接口
* Thread ()参数只能 是Runnable 接口
* 所以需要适配器来 让Callable 与Runnable建立关系
*  FutureTask 实现了 RunnableFuture
*
* */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask task=new FutureTask(new MyThread());

        new Thread(task,"A").start();
        System.out.println(task.get());
    }

}
