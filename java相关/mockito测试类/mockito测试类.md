# 0 JUnit

单元测试的版本差异

Junit4

```java
import org.junit.Test;
import org.junit.runner.RunWith;
```



JUnit5

```java
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
```



## 0.0 注解的区别

| 特征                             | JUNIT 4        | JUNIT 5        |
| :------------------------------- | :------------- | :------------- |
| 声明一种测试方法                 | `@Test`        | `@Test`        |
| 在当前类中的所有测试方法之前执行 | `@BeforeClass` | `@BeforeAll`   |
| 在当前类中的所有测试方法之后执行 | `@AfterClass`  | `@AfterAll`    |
| 在每个测试方法之前执行           | `@Before`      | `@BeforeEach`  |
| 每种测试方法后执行               | `@After`       | `@AfterEach`   |
| 禁用测试方法/类                  | `@Ignore`      | `@Disabled`    |
| 测试工厂进行动态测试             | NA             | `@TestFactory` |
| 嵌套测试                         | NA             | `@Nested`      |
| 标记和过滤                       | `@Category`    | `@Tag`         |
| 注册自定义扩展                   | NA             | `@ExtendWith`  |

## 0.1 断言的区别

在Junit 4中，org.junit.Assert具有所有断言方法来验证预期结果和结果。

在JUnit 5中，org.junit.jupiter.Assertions包含大多数断言方法

# 1 什么是mock

Mock可以理解为模拟出一个虚假的对象在测试环境中用来替换掉真实的对象,以达到:

- 验证该对象的某些方法的调用情况,调用了多少次,参数是多少
- 给这个对象的行为做一个定义,来指定返回结果或者指定特定的动作

# 2 mock一个对象和方法

## 2.1 模拟对象

### 2.1.1 mock

(1) 使用接口

使用Mockito.mock(Xxx.class)来mock一个对象或者接口

例：

```java
    @Test
    void add() {
        SecureRandom secureRandom = Mockito.mock(SecureRandom.class);
        // 注意:这里的输出永远都是0（如果没有对mock出来的对象行为进行模拟，那么执行该mock对象方法得到的返回值为该方法返回值的默认值）
        System.out.println(secureRandom.nextInt());
    }
```

(2) 使用注解

使用@Mock注解

使用该注解时，要使用MockitoAnnotations.openMocks()方法，让注解生效, 比如放在@Before方法中初始化。

```java
    @Mock
    SecureRandom secureRandom;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void add4() {
        Mockito.when(secureRandom.nextInt()).thenReturn(17);
        Assertions.assertEquals(17, secureRandom.nextInt());
    }
```
比较优雅优雅的写法是用MockitoJUnitRunner，它可以自动执行MockitoAnnotations.openMocks方法。

```java
@RunWith(MockitoJUnitRunner.class)
public class Demo1Test {
    @Spy
    Demo demo;

    @Test
    public void test() {
        Assertions.assertEquals(5, demo.add(2, 3));
    }

}
```



### 2.1.2 spy

spy和mock的不同：

- spy的对象会走真实的方法，而mock对象不会
- spy()方法的参数是对象的实例，mock的参数是class

```java
    @Spy
    Demo demo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void add4() {
        // 注解的方式
        Assertions.assertEquals(5, demo.add(2, 3));
        // 接口的方式
        Demo demoSpy = Mockito.spy(new Demo());
        Assertions.assertEquals(5, demoSpy.add(2, 3));

    }
```

### 2.1.3 InjectMocks

**@InjectMocks：创建一个实例，简单的说是这个Mock可以调用真实代码的方法，其余用@Mock（或@Spy）注解创建的mock将被注入到用该实例中。**

示例:

类InjectMockDao

```java
package com.peppacatt.test.springboottest.mytest.mydt;

import org.springframework.stereotype.Component;

@Component
public class InjectMockDao {
    public String myName() {
        return "dao";
    }
}

```
类InjectMockService
```java
package com.peppacatt.test.springboottest.mytest.mydt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InjectMockService {
    @Autowired
    InjectMockDao injectMockDao;

    public String myName() {
        return injectMockDao.myName();
    }
}

```

测试类

```java
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

```







## 2.2 模拟对象的行为

模拟一个对象的行为也就是给一个对象打桩，就是给mock出来的对象规定一定的行为，使其按照我们的要求来执行具体的操作

spy和mock的对象都可以进行打桩

### 2.2.1 thenReturn()

```java
    @Test
    void add2() {
        SecureRandom secureRandom = Mockito.mock(SecureRandom.class);
        Mockito.when(secureRandom.nextInt()).thenReturn(17);
        // 结果为:17
        System.out.println(secureRandom.nextInt());
    }
```

### 2.2.2 thenThrow()

```java
    @Test
    void add5() {
        // 正常执行
        System.out.println(demo.myParse("2"));
        Mockito.when(demo.myParse("2")).thenThrow(new NumberFormatException());
        // 抛出异常
        System.out.println(demo.myParse("2"));
    }
```

### 2.2.3 thenCallRealMethod()

在循环的情况下可以用到此方法，在某个循环中调用的方法需要按我们的打桩回复，但是某一次的循环需要走真实的方法的时候调该方法

```java
    @Test
    void add5() {
        Assertions.assertEquals(5, demo.add(2, 3));
        Mockito.when(demo.add(2, 3)).thenReturn(6);
        Assertions.assertEquals(6, demo.add(2, 3));
        Mockito.when(demo.add(2, 3)).thenCallRealMethod();
        Assertions.assertEquals(5, demo.add(2, 3));
    }
```

### 2.2.4 mock静态方法

Mockito从3.4.0开始可以mock静态方法(之前需要借助PowerMock来实现)，但是需要将原来的mockito-core依赖更换为mockito-inline

```xml
        <!-- https://mvnrepository.com/artifact/org.mockito/mockito-inline -->
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-inline</artifactId>
            <version>5.2.0</version>
            <scope>test</scope>
        </dependency>
```

mock静态对象之后需要关闭,使用try-with-resource

```java
    @Test
    public void test() {
        try (MockedStatic<Demo> demo = Mockito.mockStatic(Demo.class)) {
            Mockito.when(Demo.getStr("demo")).thenReturn("o");
            Assertions.assertEquals("o", Demo.getStr("demo"));
        }
    }
```

如果没有引入mockito-inline依赖就直接mock静态方法的话会报如下错误

```
org.mockito.exceptions.base.MockitoException: 
The used MockMaker SubclassByteBuddyMockMaker does not support the creation of static mocks

Mockito's inline mock maker supports static mocks based on the Instrumentation API.
You can simply enable this mock mode, by placing the 'mockito-inline' artifact where you are currently using 'mockito-core'.
Note that Mockito's inline mock maker is not supported on Android.
```



# 3 验证和断言

## 3.1 验证

(1) 验证就是校验对象是否发生过某些行为，使用Mockito.verify(xxx).xxx()

```java
    @Test
    void add1() {
        Demo demo = Mockito.mock(Demo.class);
        System.out.println(demo.add(2, 3));
        // 正常
        Mockito.verify(demo).add(2, 3);
        // 报错，因为预期的结果是add()方法执行1次，并且入参是(1,3)，但实际的入参是(2,3)
        // Mockito.verify(demo).add(1, 3);
    }
```

(2) 验证方法执行的次数

```java
    @Test
    void add() {
        SecureRandom secureRandom = Mockito.mock(SecureRandom.class);
        System.out.println(secureRandom.nextInt());
        // 正确
        Mockito.verify(secureRandom).nextInt();
        // 报错，因为预期结果是执行3次nextInt()方法，但实际上只执行了1次
        // Mockito.verify(secureRandom,Mockito.times(3)).nextInt();
    }
```

## 3.2 断言

使用Assertions中的方法来进行断言

```java
    @Test
    void add3() {
        SecureRandom secureRandom = Mockito.mock(SecureRandom.class);
        Mockito.when(secureRandom.nextInt()).thenReturn(17);
        // 正确
        Assertions.assertEquals(17, secureRandom.nextInt());
        // 报错，因为预期的结果是20，但实际的结果是17
//        Assertions.assertEquals(20, secureRandom.nextInt());
    }
```

