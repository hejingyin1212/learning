# SpringBoot启动流程

# Spring注解

## 手动装配

开发者按照需要，要一个一个的配置

### 模式注解

* @Component
* @Controller
* @Service
* @Respository
* @Configuration

### 模块装配

#### 注解方式实现Enable

@Import

#### 接口驱动实现Enable

ImportSelector

### 条件装配

在Bean装配前做一些前置判断

* @Porfile		配置话条件装配
* @Condition	程序条件装配

## 自动装配

开发者不需要一个一个装配。

实现方式：模式注解 + 条件装配 + Enable模块 + 工厂加载机制

流程：

* 自动装配						@EnableAutoConfiguration
* 实现自动装配                 xxxAutoConfiguration
* 配置自动装配的实现      META-INF/spring.factories

# SpringApplication

## SpringApplication构造（准备）阶段

### 1. 配置SpringBootBean	-源码

​	xml或java配置文件集合，SpringBoot会读取BeanDefinitionLoader，并且将配置加载成为SpringBean

### 2. Web应用类型推断

```java
public enum WebApplicationType {
    NONE,
    SERVLET,
    REACTIVE;
}
```

* Web应用       启动Web服务器
* 非Web应用    执行完直接结束

可以通过.web()设置，springboot本身会自动推断

根据当前应用的classpath来去查找是否有相应的实现类  deduceFromClasspath()

```java
private static final String[] SERVLET_INDICATOR_CLASSES = new String[]{"javax.servlet.Servlet", "org.springframework.web.context.ConfigurableWebApplicationContext"};
private static final String WEBMVC_INDICATOR_CLASS = "org.springframework.web.servlet.DispatcherServlet";
private static final String WEBFLUX_INDICATOR_CLASS = "org.springframework.web.reactive.DispatcherHandler";
private static final String JERSEY_INDICATOR_CLASS = "org.glassfish.jersey.servlet.ServletContainer";
private static final String SERVLET_APPLICATION_CONTEXT_CLASS = "org.springframework.web.context.WebApplicationContext";
private static final String REACTIVE_APPLICATION_CONTEXT_CLASS = "org.springframework.boot.web.reactive.context.ReactiveWebApplicationContext";
```

### 3. 加载应用上下文初始化器

利用工厂加载机制，实例化ApplicationContextInitializer接口的实现，通过SpringFactoryLoader获取META-INF/spring.factories获取资源

```
# Initializers
org.springframework.context.ApplicationContextInitializer=\
org.springframework.boot.autoconfigure.SharedMetadataReaderFactoryContextInitializer,\
org.springframework.boot.autoconfigure.logging.ConditionEvaluationReportLoggingListener
```

可以通过AnnotationOrderComparator

### 4. 加载事件监听器

### 5. 推断引导类

含有main方法的类称为引导类，mainApplicationClass，通过堆栈实现

```java
private Class<?> deduceMainApplicationClass() {
    try {
        StackTraceElement[] stackTrace = (new RuntimeException()).getStackTrace();
        StackTraceElement[] var2 = stackTrace;
        int var3 = stackTrace.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            StackTraceElement stackTraceElement = var2[var4];
            if ("main".equals(stackTraceElement.getMethodName())) {
                return Class.forName(stackTraceElement.getClassName());
            }
        }
    } catch (ClassNotFoundException var6) {
    }

    return null;
}
```

## SpringApplication运行阶段

1. 加载，运行SpringApplication运行监听器
2. 监听SpringBoot事件
3. 创建Spring上下文
4. 创建Environment







