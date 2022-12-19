package com.example.multhread;

import java.util.Arrays;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


public class ThreadPool {
    public static final AtomicInteger ai=new AtomicInteger();//拒绝策略执行次数
    public static final AtomicInteger af=new AtomicInteger();//线程池及等待队列满是执行次数
    public static class MyThread1 implements Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }
    public static class CusReject implements RejectedExecutionHandler{

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("拒绝策略执行"+ai.incrementAndGet());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //单一线程,实现串行
        ExecutorService sin=Executors.newSingleThreadExecutor();
        //固定大小，当任务数超过容量时，任务进入等待队列，直到有空余线程
        ExecutorService ex=Executors.newFixedThreadPool(3);
        //可缓冲现线程，该线程池根据需要创建新线程，但在可用时将重用以前构建的线程，如果没有可用的现有线程，将创建一个新线程并将其添加到池中
        ExecutorService cache=Executors.newCachedThreadPool();

        //定时线程池
        ScheduledExecutorService sch=Executors.newScheduledThreadPool(3);

        /*线程池中execute和submit的区别:
        submit()有返回值,而execute()没有
        例如,有个task,希望该task执行完后告诉我它的执行结果,是成功还是失败,然后继续下面的操作,这时需要用submit

        */
        //实现callable，返回future

        //线程池七大参数，四大拒绝策略
        /* ADDC
        AbortPolicy:丢弃任务并抛出异常。
        DiscardPolicy:丢弃任务，但是不抛出异常。可能导致无法发现系统的异常状态。
        DiscardOldestPolicy:丢弃队列最前面的任务，然后重新提交被拒绝的任务。
        CallerRunsPolicy:由调用线程处理该任务,即直接执行该任务。
        */
        /*核心线程数设置(N为cpu核数),最大线程数默认设置与核心线程数相等，即corePoolSize=maximumPoolSize:
        计算密集型:N+1
        I/O密集型:2N
        */
        ThreadPoolExecutor tpc=new ThreadPoolExecutor(
                5,
                10,
                60,//非核心线程在空闲时间超过keepAliveTime后会被回收
                TimeUnit.SECONDS,//时间单位
                new LinkedBlockingQueue<>(50),//任务等待队列
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return null;
                    }
                },//线程工厂
                new ThreadPoolExecutor.CallerRunsPolicy());

        ThreadPoolExecutor tpe=new ThreadPoolExecutor(
                5,
                10,
                60,//非核心线程在空闲时间超过keepAliveTime后会被回收
                TimeUnit.SECONDS,//时间单位
                new LinkedBlockingQueue<>(50),//任务等待队列
                new CusThreadFactory("testThread-"),//自定义线程工厂
                new CusReject());//线程拒绝策略
        //当i分别设置为40，100，运行结果测试
        for (int i = 0; i <40 ; i++) {
            tpe.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("---> " + Thread.currentThread().getName()+"run");
                    af.incrementAndGet();
                    try {
                        Thread.sleep(3*1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
            System.out.println("线程池及等待队列任务总数= " +tpe.getTaskCount());
            System.out.println("拒绝策略执行次数= " +ai);
            System.out.println("任务队列中任务数 " + tpe.getQueue().size());
    }

}
