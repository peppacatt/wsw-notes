package com.peppacatt.notes.wswtest.mytest.t20230216;

public class StrEmpty {
    private int count = 0;

    public static void main(String[] args) {
        m(1, 2);
        new StrEmpty().m1();
    }

    private static void m(int a, int b) {
        int c = 3;
        int sum = a + b + c;
        System.out.println(sum);
    }

    private void m1() {
        int a = 1;
        int sum = a + this.count;
        System.out.println(sum);
    }
}