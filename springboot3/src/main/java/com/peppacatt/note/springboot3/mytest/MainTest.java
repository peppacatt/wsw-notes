package com.peppacatt.note.springboot3.mytest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainTest {
    public static void main(String[] args) {
        CompletableFuture.runAsync(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.printf("线程:%s执行了\n", Thread.currentThread());
            }
        });
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.execute(() -> {
//            for (int i = 0; i < 5; i++) {
//                System.out.printf("线程:%s执行了\n", Thread.currentThread());
//            }
//        });
//        executorService.execute(() -> {
//            for (int i = 0; i < 5; i++) {
//                System.out.printf("线程:%s执行了\n", Thread.currentThread());
//            }
//        });
//        executorService.submit(() -> {
//            for (int i = 0; i < 5; i++) {
//                System.out.printf("线程:%s执行了\n", Thread.currentThread());
//            }
//        });
//        executorService.shutdown();
        CompletableFuture.runAsync(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.printf("线程:%s执行了\n", Thread.currentThread());
            }
        });
    }
}
