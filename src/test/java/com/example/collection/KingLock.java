package com.example.collection;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

public class KingLock implements Lock {
    //等待队列
    LinkedBlockingQueue<Thread> waiter=new LinkedBlockingQueue();
    //原子操作类
    AtomicReference<Thread> owner=new AtomicReference<>();
    @Override
    public void lock() {
        while(!owner.compareAndSet(null,Thread.currentThread())){//CAS失败
            waiter.add(Thread.currentThread());
            LockSupport.park();
            waiter.remove(Thread.currentThread());//
        }


    }
    @Override
    public void unlock() {
        if(owner.compareAndSet(Thread.currentThread(),null)){
            for (Object obj:waiter.toArray()
                 ) {
                Thread next=(Thread) obj;
                LockSupport.unpark(next);
                
            }
        }

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }



    @Override
    public Condition newCondition() {
        return null;
    }
}
