package com.example.multhread;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class NumberPrintTest {
    static Thread t1=null;
    static Thread t2=null;
    public  static void m1(char[] a,char[] b){
        Object o=new Object();
        new Thread(()->{
            for (int i = 0; i < a.length; i++) {
                synchronized (o){
                    try {
                        o.notify();
                        System.out.println(a[i]);
                        o.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }finally {
                        o.notify();
                    }
                    //
                }
            }
        },"t1").start();
        new Thread(()->{
            for (int i = 0; i < b.length; i++) {
                synchronized (o){
                    try {
                        o.notify();
                        System.out.println(b[i]);
                        o.wait();

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }finally {
                        o.notify();
                    }

                }
            }
        },"t2").start();

    }
    public static void m2(char[] a,char[] b){
        t1=new Thread(()->{
            for (int i = 0; i < a.length; i++) {
                //LockSupport.unpark(Thread.currentThread());//解除阻塞状态
                //LockSupport.park(o);
                System.out.println(a[i]);
                LockSupport.unpark(t2);//唤醒指定线程
                LockSupport.park();//阻塞当前线程
            }
        });
        t2=new Thread(()->{
            for (int i = 0; i < b.length; i++) {
                LockSupport.park();
                System.out.println(b[i]);
                //进入阻塞状态
                //LockSupport.park(Thread.currentThread());
                LockSupport.unpark(t1);
            }
        },"t2");
        t1.start();
        t2.start();

    }
    public static void m3(char[] a,char[] b){
        Object o=new Object();
        Lock lock=new ReentrantLock();
        Condition condition=lock.newCondition();
        new Thread(()->{
            for (int i = 0; i < a.length; i++) {
                lock.lock();
                try {
                    condition.signal();
                    System.out.println(a[i]);
                    condition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                lock.unlock();
            }
        },"t1").start();
        new Thread(()->{
            for (int i = 0; i < b.length; i++) {
               lock.lock();
                    try {
                        condition.signal();
                        System.out.println(b[i]);
                        condition.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }finally {
                    }
            lock.unlock();
            }
        },"t2").start();

    }

    public static void main(String[] args) {
        //交替输出数字和字母
        char[] a="1234567".toCharArray();
        char[] b= "abcdefg".toCharArray();
        m1(a,b);
        //m2(a,b);
        //m3(a,b);


    }


}
