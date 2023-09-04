## application

## logging

### log4j2

s-boot3-simple采用了log4j2, 时间点是2023年, 原因主要是:
* 异步性能(搭配 lmax Disruptor)
* 更清晰精简的配置
* lazy logging

#### 1. 去除其他 logging 的绑定

```xml
...

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <!-- 去除 starter-web 项目的logging 依赖, 防止classpath内有多个logging 运行时导致绑定异常 -->
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>

...
```

#### 2. 增加 Log4j2 依赖

```xml
...

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-log4j2</artifactId>
    <!-- 版本号跟着主 springboot 版本走 -->
</dependency>

...
```

#### 3. 配置文件

`src/main/resrouces` 下新建 `log4j2.xml/log4j2.json/log4j2.yaml` 均可, 此处以 xml 为例:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration xmlns:xi="http://www.w3.org/2001/XInclude"
               status="warn" name="XInclude">
    <Properties>
        <Property name="PATTERN" value="%d{yyyy-MM-dd HH:mm:ss.SSS} %5p [%t] %-40.40c{1.} : %m%n"/>
    </Properties>
    <Appenders>
        <!-- 输出到控制台，仅在本地开发环境使用 -->
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="${PATTERN}"/>
        </Console>
        <!--输出到日志文件，滚动分割日志文件，自动打包gz-->
        <RollingFile name="File" fileName="logs/app.log" filePattern="logs/archives/app-%d{yyyy-MM-dd}-%i.log.gz">
            <PatternLayout pattern="${PATTERN}"/>
            <Policies>
                <!--默认一天一个文件-->
                <TimeBasedTriggeringPolicy/>
                <!--一天内大于size就单独分隔-->
                <SizeBasedTriggeringPolicy size="1 GB"/>
            </Policies>
        </RollingFile>
    </Appenders>
    <Loggers>
        <!-- 添加你的自定义logger，一般用于区分包名的日志，不同包名不同的级别/appender -->
        <!-- additivity 意思是，调用完当前appender，是否继续调用parent logger appender，默认true-->
        <Logger name="your logger/package name" level="debug" additivity="false"/>
        <!--默认的Root Logger 级别-->
        <Root level="INFO">
            <!--这里需要区分下环境（配合maven profile之类的）-->
            <!-- 开发环境使用Console Appender，生产环境使用File Appender -->
            <AppenderRef ref="Console"/>
            <!--
            <AppenderRef ref="File"/>
            -->
        </Root>
    </Loggers>
</Configuration>
```

#### 日志代码

```java

// 普通占位
logger.debug("Logging in user {} with birthday {}", user.getName(), user.getBirthdayCalendar());


// String.format 格式化
public static Logger logger = LogManager.getFormatterLogger("Foo");

logger.debug("Logging in user %s with birthday %s", user.getName(), user.getBirthdayCalendar());
logger.debug("Logging in user %1$s with birthday %2$tm %2$te,%2$tY", user.getName(), user.getBirthdayCalendar());
logger.debug("Integer.MAX_VALUE = %,d", Integer.MAX_VALUE);
logger.debug("Long.MAX_VALUE = %,d", Long.MAX_VALUE);


// logger.printf 格式化
logger.printf(Level.INFO, "Logging in user %1$s with birthday %2$tm %2$te,%2$tY", user.getName(), user.getBirthdayCalendar());


// “惰性” 打日志
logger.debug("入参报文：{}",() -> JSON.toJSONString(someDTO));

// 相当于如下代码
if(logger.isDebugEnabled()){
    logger.debug("入参报文：{}",JSON.toJSONString(someDTO));
}

```

#### 异步日志打印

log4j2 支持异步日志, 异步打印的性能比同步提升明显, 需要使用 `Disruptor` 库来启用:

```xml
...
<!-- Needed for Async Logging with Log4j 2 -->
<dependency>
    <groupId>com.lmax</groupId>
    <artifactId>disruptor</artifactId>
    <version>3.3.6</version>
</dependency>
...

```
添加依赖后你可以选择让所有Logger都异步运行, 或者创建混合模式(部分同步部分异步)

#### 全异步模式

设置系统变量 `Log4jContextSelector` 为 `org.apache.logging.log4j.core.async.AsyncLoggerContextSelector` 即可开启全局异步.

你可以这样添加系统变量:
```bash
mvn spring-boot:run -DLog4jContextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector
```

或者这样启动jar:

```bash
mvn clean package
java -jar target/log4j2-demo-0.0.1-SNAPSHOT.jar -DLog4jContextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector
```

#### 混合模式

在配置文件中可以通过使用 `<AsyncLogger>` 来控制是否启用异步.

以下配置文件将指定的log变成异步的, 而其他的日志输出不受影响:

```xml
...

<Loggers>
    <AsyncLogger name="com.example.log4j2demo" level="debug" additivity="false">
        <AppenderRef ref="ConsoleAppender" />
        <AppenderRef ref="FileAppender" />
    </AsyncLogger>

    <Root level="info">
        <AppenderRef ref="ConsoleAppender" />
        <AppenderRef ref="FileAppender" />
    </Root>
</Loggers>

...

```

混合模式下不需要再设置系统变量, 仅仅修改配置文件即可.

#### 参考

* https://www.callicoder.com/spring-boot-log4j-2-example/
* https://segmentfault.com/a/1190000039751787