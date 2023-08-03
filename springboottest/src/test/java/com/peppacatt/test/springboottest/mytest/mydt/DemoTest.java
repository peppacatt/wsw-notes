package com.peppacatt.test.springboottest.mytest.mydt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.security.SecureRandom;

class DemoTest {

    @Spy
    Demo demo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void add7() {
        Assertions.assertEquals(5, demo.add(2, 3));
    }

    @Test
    void add6() {
        Demo demo = Mockito.mock(Demo.class);
        Demo demo1 = Mockito.spy(new Demo());
        System.out.println(demo.add(2, 3));
        System.out.println(demo1.add(2, 3));
    }

    @Test
    void add5() {
        Assertions.assertEquals(5, demo.add(2, 3));
        Mockito.when(demo.add(2, 3)).thenReturn(6);
        Assertions.assertEquals(6, demo.add(2, 3));
        Mockito.when(demo.add(2, 3)).thenCallRealMethod();
        Assertions.assertEquals(5, demo.add(2, 3));
    }

    @Test
    void add4() {
        // 注解的方式
        Assertions.assertEquals(5, demo.add(2, 3));
        // 接口的方式
        Demo demoSpy = Mockito.spy(new Demo());
        Assertions.assertEquals(5, demoSpy.add(2, 3));

    }

    @Test
    void add() {
        SecureRandom secureRandom = Mockito.mock(SecureRandom.class);
        // 这里的输出永远都是0（如果没有对mock出来的对象行为进行模拟，那么执行该mock对象方法得到的返回值为该方法返回值的默认值）
        System.out.println(secureRandom.nextInt());
        // 验证方法执行的次数
        Mockito.verify(secureRandom).nextInt();
        // 这里会报错，因为预期结果是执行3次nextInt()方法，但实际上只执行了1次
//        Mockito.verify(secureRandom, Mockito.times(3)).nextInt();
    }

    @Test
    void add1() {
        Demo demo = Mockito.mock(Demo.class);
        System.out.println(demo.add(2, 3));
        // 正常
        Mockito.verify(demo).add(2, 3);
        // 报错，因为预期的结果是add()方法执行1次，并且入参是(1,3)，但实际的入参是(2,3)
//        Mockito.verify(demo).add(1, 3);
    }

    @Test
    void add2() {
        SecureRandom secureRandom = Mockito.mock(SecureRandom.class);
        Mockito.when(secureRandom.nextInt()).thenReturn(17);
        System.out.println(secureRandom.nextInt());
    }

    @Test
    void add3() {
        SecureRandom secureRandom = Mockito.mock(SecureRandom.class);
        Mockito.when(secureRandom.nextInt()).thenReturn(17);
        Assertions.assertEquals(17, secureRandom.nextInt());
        // 报错，因为预期的结果是20，但实际的结果是17
//        Assertions.assertEquals(20, secureRandom.nextInt());
    }

}