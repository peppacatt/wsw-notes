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

@Mock注解需要搭配MockitoAnnotations.openMocks(testClass)方法一起使用，不然会报错

可以将该方法放到@Before修饰的方法中，在执行每个方法之前都会执行该方法

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

