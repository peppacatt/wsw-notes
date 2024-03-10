package com.peppacatt.notes.springboottest.mytest;

import java.security.SecureRandom;
import java.util.Arrays;

public class MainTest {
    public static void main(String[] args) {
        int random = (int) (Math.random() * 13 + 1);
        System.out.println(random);
    }
}
