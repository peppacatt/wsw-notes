package com.peppacatt.notes.wswtest.jvm.heap;

public class HeapDemo2 {
    public static void main(String[] args) {
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
