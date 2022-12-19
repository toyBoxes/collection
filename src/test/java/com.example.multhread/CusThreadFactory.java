package com.example.multhread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public  class CusThreadFactory implements ThreadFactory {

    private  String threadName;

    private  AtomicInteger atomicInteger;

    public CusThreadFactory(String threadName) {
        this.threadName = threadName;
        this.atomicInteger = new AtomicInteger(0);
    }


    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r,threadName+"-"+atomicInteger.incrementAndGet());
    }
}
