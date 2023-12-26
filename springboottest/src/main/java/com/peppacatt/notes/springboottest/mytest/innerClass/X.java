package com.peppacatt.notes.springboottest.mytest.innerClass;

public class X {
    public static class A {
        public static void aa() {
            System.out.printf("%s\n", "aa()");
        }
    }

    public class B {
        public void bb() {
            System.out.printf("%s\n", "bb()");
        }
    }
}
