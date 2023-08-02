package com.peppacatt.test.springboottest.mytest.mockito;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Demo {
    public int add(int a, int b) {
        return a + b;
    }

    public int myParse(String str) {
        int i = 0;
        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            log.error("解析错误");
        }
        return i;
    }
}
