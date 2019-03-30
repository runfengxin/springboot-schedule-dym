package com.example.demo.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Slf4j
public class ScheduleUtil {

    private static ThreadPoolTaskScheduler threadPoolTaskScheduler=new ThreadPoolTaskScheduler();
    private static Map<String, ScheduledFuture<?>> scheduledFutureMap=new HashMap<>();
    static{
        threadPoolTaskScheduler.initialize();
        log.info("定时任务线程池启动");
    }

    /**
     * 启动某定时任务
     * @param scheduleTask
     * @param startTime
     */
    public static void start(ScheduleTask scheduleTask, Date startTime){
        ScheduledFuture<?> scheduledFuture=threadPoolTaskScheduler.schedule(scheduleTask,startTime);
        scheduledFutureMap.put(scheduleTask.getId(),scheduledFuture);
        log.info("启动定时任务"+scheduleTask.getId());
    }

    /**
     * 取消某定时任务
     * @param scheduleTask
     */
    public static void cancel(ScheduleTask scheduleTask){
        ScheduledFuture<?> scheduledFuture=scheduledFutureMap.get(scheduleTask.getId());
        if (scheduledFuture!=null&&!scheduledFuture.isCancelled()){
            scheduledFuture.cancel(false);
            log.info("取消定时任务"+scheduleTask.getId());
        }
    }

    public static void reset(ScheduleTask scheduleTask,Date startTime){
        //先取消定时任务
        ScheduledFuture<?> scheduledFuture=scheduledFutureMap.get(scheduleTask.getId());
        if (scheduledFuture!=null&&!scheduledFuture.isCancelled()){
            scheduledFuture.cancel(false);
            log.info("取消定时任务"+scheduleTask.getId());
        }
        scheduledFutureMap.remove(scheduleTask.getId());
        //启动新的定时任务
        scheduledFuture=threadPoolTaskScheduler.schedule(scheduleTask,startTime);
        scheduledFutureMap.put(scheduleTask.getId(),scheduledFuture);
        log.info("修改定时任务"+scheduleTask.getId());
    }
}
