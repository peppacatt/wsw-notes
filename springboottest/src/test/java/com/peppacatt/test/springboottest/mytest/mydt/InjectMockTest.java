package com.peppacatt.test.springboottest.mytest.mydt;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InjectMockTest {

    // 如果这里用spy,那么调用injectMockService.myName()会报空指针,因为该方法调了injectMockDao的方法,但是injectMockDao未注入
//    @Spy
    // 这里用InjectMocks,那么调用injectMockService.myName()会正常执行,因为用@Mock（或@Spy）注解创建的mock将被注入到用该实例中。
    @InjectMocks
    InjectMockService injectMockService;

    @Spy
    InjectMockDao injectMockDao;

    @Test
    public void test() {
        Assertions.assertEquals("dao", injectMockDao.myName());
//        Mockito.when(injectMockDao.myName()).thenReturn("service");
        Assertions.assertEquals("dao", injectMockService.myName());
    }
}
