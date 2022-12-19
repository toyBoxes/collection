package com.example.collection;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AqsTest {
     int count=0;
    Lock lock=new ReentrantLock();
    //AtomicLong aLong=new AtomicLong(0);
    public   void add(){
        lock.lock();
        try{
            count++;
        }finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        AqsTest aqs=new AqsTest();
        for (int i = 0; i < 10; i++) {new Thread(()->{
            for (int j = 0; j < 100; j++) {
                aqs.add();
            }
        }).start();

        }
        Thread.sleep(1000);

        System.out.println("方法访问次数:" + aqs.count);


    }
}
