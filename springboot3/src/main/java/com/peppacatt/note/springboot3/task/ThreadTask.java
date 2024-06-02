package com.peppacatt.note.springboot3.task;

import org.springframework.stereotype.Component;

@Component
public class ThreadTask {
    public void xxx(){
        System.out.println("当前任务执行了");
        System.out.println(Thread.currentThread());
    }
}
