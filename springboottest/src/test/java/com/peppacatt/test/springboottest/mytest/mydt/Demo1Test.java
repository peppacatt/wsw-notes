package com.peppacatt.test.springboottest.mytest.mydt;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

// 方式1
@RunWith(MockitoJUnitRunner.class)
public class Demo1Test {
    @Spy
    Demo demo;

    // 方式2
//    @Before
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    public void test() {
        try (MockedStatic<Demo> demo = Mockito.mockStatic(Demo.class)) {
            Mockito.when(Demo.getStr("demo")).thenReturn("o");
            Assertions.assertEquals("o", Demo.getStr("demo"));
        }
    }

}