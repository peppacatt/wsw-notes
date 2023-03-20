package com.peppacatt.notes.wswtest.jvm.heap;

public class HeapDemo {
    public static void main(String[] args) {
        System.out.printf("HeapDemo, %s", "start");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("HeapDemo, %s", "end");
    }
}
