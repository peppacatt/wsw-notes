package com.peppacatt.note.springboot3.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ThreadTask {
    @Scheduled(fixedRate = 5000)
    public void aaa() throws InterruptedException {
        Thread.sleep(10000);
        System.out.println("aaa执行---" + Thread.currentThread() + "---" + new Date());
    }

    @Scheduled(fixedRate = 6000)
    public void bbb() throws InterruptedException {
        System.out.println("bbb执行---" + Thread.currentThread() + "---" + new Date());
    }
}
