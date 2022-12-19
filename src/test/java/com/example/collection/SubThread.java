package com.example.collection;

public class SubThread extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                System.out.println("子线程"+i+"运行:");
            }
        }
    public static void main(String[] args) throws InterruptedException {

        //
        SubThread a=new SubThread();
        a.start();
        a.join();
        for (int i = 0; i < 50; i++) {
            //Thread.yield();
            System.out.println("mian:主线程");
        }
        //System.out.println("mian:主线程");

    }
}
