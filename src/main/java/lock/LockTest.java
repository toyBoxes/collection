package lock;

import sun.rmi.runtime.Log;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;


public class LockTest {

    //GitHub push test
    public static void main(String[] args) {
        Lock rq=new ReentrantLock();


            Thread a=new Thread(()->{

                    m1();



               // m2();

            },"T1");

           Thread b= new Thread(()->{

                m1();

            },"T2");
           a.start();
           b.start();


    }
    public static void m1(){
                //synchronized支持可重入锁
                synchronized (LockTest.class){
                    System.out.println("方法1调用-->");
                    m2();
                    m3();
                }


    }
    public static  void m2(){
    synchronized (LockTest.class){
        System.out.println("方法2调用-->");
    }



    }
    public static  void m3(){
        synchronized (LockTest.class){
            System.out.println("方法3调用-->");
        }
    }
}
