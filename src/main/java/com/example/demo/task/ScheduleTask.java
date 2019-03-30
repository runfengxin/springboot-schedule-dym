package com.example.demo.task;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class ScheduleTask implements Runnable{

    protected String id;

    public String getId() {
        return id;
    }

    public ScheduleTask(String id) {
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void run() {
        log.info(id+"===>"+new Date()+"");
    }

    public static void main(String[] args) {
//        log.info(new Date()+"");
//        String dateStr="2019-01-28 02:23:05";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//            ScheduleUtil.start(new ScheduleTask("task1"),sdf.parse(dateStr));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        ScheduleUtil.cancel(new ScheduleTask("task1"));

    }
}
