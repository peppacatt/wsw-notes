package com.peppacatt.test.springboottest.mytest;

import com.peppacatt.test.springboottest.mytest.innerClass.X;

public class MainTest {
    public static void main(String[] args) {
        X.A.aa();
        X x = new X();
        X.B b = x.new B();
        b.bb();
        Cat cat = new Cat("aa", 2);
        System.out.println(x);
        System.out.println(b);
        System.out.println(cat);
        System.out.printf("%s\n", "---");
    }
}
