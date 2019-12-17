package com.atguigu.MyJUC;


import java.util.concurrent.*;

public class ThreadPoolDemo {
    public static void main(String[] args) {
      //  ExecutorService threadPool= Executors.newFixedThreadPool(4);//一次固定数量线程
        //问题：队列容量太大  队列阻塞太多 但达不到线程池扩容条件
        //ExecutorService threadPool=Executors.newSingleThreadExecutor();//一次固定1 个线程


       //ExecutorService threadPool= Executors.newCachedThreadPool();//一次固定N个线程
        //问题 ：队列不存储  扩容数量为最大值 只要有请求 就会扩容

        /*
        *线程池的7个参数
        * corePoolSize,   线程常驻核心数量
        * maximumPoolSize,  队列阻塞 线程池扩容的最大容量
        * keepAliveTime,  队列空闲 大于核心线程数量的线程存活时间
        * unit,    存活时间单位  如 TimeUtil.SECONDS
        * workQueue, 阻塞队列类型
        * Executors.defaultThreadFactory(), 线程池工厂 一般默认
        * defaultHandler , 拒绝策略 阻塞对列满 线程池达到最大容量后 所采取的处理
        *
        *
        *
        *
        *
        *
        * 采用自定义线程  最大线程容量+阻塞队列数量 >=请求数量
        *
        *
        * 拒绝策略
        * 当队列已满 正在运行的线程以达到maximumPoolTime
        *
        * new ThreadPoolExecutor.AbortPolicy() 抛出异常 java.util.concurrent.RejectedExecutionException
         new ThreadPoolExecutor.CallerRunsPolicy() 调用者运行:一种调节机制，该策略既不会抛弃任务，也不
                                                    会抛出异常，而是将某些任务回退到调用者，从而降低新任务的流量。

         new ThreadPoolExecutor.DiscardOldestPolicy() 抛弃队列中等待最久的任务，然后把当前任务加人队列中
                                                     尝试再次提交当前任务。
        * new ThreadPoolExecutor.DiscardPolicy() 该策略默默地丢弃无法处理的任务，不予任何处理也不抛出异常。
                                                        如果允许任务丢失，这是最好的一种策略。
        * */
    ExecutorService threadPool=new ThreadPoolExecutor(
            4,
            10,
            1L,
            TimeUnit.SECONDS,
           new LinkedBlockingQueue<>(10),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.DiscardPolicy());

    /*
    * 模拟银行办理业务
    *
     * */
                try{
                    for (int i = 1; i <=30 ; i++) {
                        final int Temp=i;
                        threadPool.execute(()->{
                            System.out.println("请"+"\t"+Temp+"顾客  到  "+Thread.currentThread().getName()+"办理业务");
                        });
                    }

                }finally{
                    threadPool.shutdown();
                    }



    }


}
