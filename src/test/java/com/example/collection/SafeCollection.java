package com.example.collection;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class SafeCollection {
    public static void main(String[] args) {

        //加入lock锁保证线程安全
        CopyOnWriteArrayList<Integer> cs=new CopyOnWriteArrayList<>();
        CopyOnWriteArraySet<Integer> ct=new CopyOnWriteArraySet();

        //jdk1.7 数组+链表
        //jdk1.8 数组+链表+红黑树
        HashMap<String,String> hp=new HashMap<>();



        //jdk1.7 分段锁机制segment+hashEntry，对每个segment加锁
        //jdk1.8 synchronized+数组+链表+红黑树，锁颗粒度更细
        ConcurrentHashMap<Integer,String> cp=new ConcurrentHashMap<>();

        cs.add(3);
        cs.add(4);
        ct.add(5);
        ct.add(5);
        cp.put(1,"张三");
        cp.put(2,"李四");
        System.out.println(cs.toString());
        System.out.println(ct.toString());
        System.out.println(cp.toString());

        for (int i = 0; i <5 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10; j++) {
                        hp.put(Thread.currentThread().getName()+j,String.valueOf(j));
                        System.out.println(hp);
                    }
                }
            }


            ).start();
        }


    }
}
