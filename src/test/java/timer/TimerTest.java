package timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.DelayQueue;

public class TimerTest {
    public static void main(String[] args) {
        //基于绝对时间，无法设置为系统时间
        Timer t=new Timer();
        for (int i = 0; i < 2; i++) {
            FoTimeTask foTimeTask=new FoTimeTask("任务"+i);
            //t.schedule(foTimeTask,new Date(),2);//出现丢任务情况
            t.scheduleAtFixedRate(foTimeTask,new Date(),2000);//任务时间乱序，与预设间隔时间不符
            //单线程，任务阻塞 任务超时

        }

    }
}
class FoTimeTask extends TimerTask{
    private String name;

    public FoTimeTask(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            System.out.println("name="+name+" "+"startTime:"+new Date());
            Thread.sleep(3000);
            //需要加入线程池才可以保证任务在正确时间执行
            System.out.println("name="+name+" "+"endTime:"+new Date());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}