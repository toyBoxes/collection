package com.example.collection;

import com.sun.javafx.collections.UnmodifiableListSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;


public class Col {
    public static  Boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        //Boolean flag = false;

        Object lock = new Object();
        Lock lock1=new ReentrantLock();
        LinkedBlockingQueue<Integer> qu =new LinkedBlockingQueue<>(20);
        Thread a=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (qu.remainingCapacity()>0) {
                        qu.put(1);
                        System.out.println("线程1插入");
                    }
                    if(qu.remainingCapacity()==0){
                        flag=true;
                    }

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread b=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (qu.remainingCapacity()>0) {
                        qu.put(2);
                        System.out.println("线程2插入");
                    }
                    if(qu.remainingCapacity()==0){
                        flag=true;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        //Thread.sleep(1000);
        a.start();
        //Thread.sleep(1000);
        b.start();
        a.join();
        b.join();
        System.out.println("args = " + qu.toString());
        //Thread.sleep(1000);
        //if(flag){
           // System.out.println("args = " + qu.toString());
        //}
    }
}
