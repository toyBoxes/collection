package timer;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ScheduleThreadTest {
    public static void main(String[] args) {
        ScheduledExecutorService  sc= Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 2; i++) {
            sc.scheduleAtFixedRate((new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("name="+Thread.currentThread().getName()+" "+"startTime:"+new Date());
                        Thread.sleep(3000);
                        System.out.println("name="+Thread.currentThread().getName()+" "+"endTime:"+new Date());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }),0,2, TimeUnit.SECONDS);

        }

    }
}
