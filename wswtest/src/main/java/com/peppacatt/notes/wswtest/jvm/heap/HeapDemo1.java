package com.peppacatt.notes.wswtest.jvm.heap;

public class HeapDemo1 {
    public static void main(String[] args) {
        System.out.printf("HeapDemo1, %s", "start");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("HeapDemo1, %s", "end");
    }
}
