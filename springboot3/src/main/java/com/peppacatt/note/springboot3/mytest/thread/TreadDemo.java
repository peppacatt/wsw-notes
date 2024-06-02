package com.peppacatt.note.springboot3.mytest.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TreadDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("xxxxx");
            }
        });
//        future.get();
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
    }
}
