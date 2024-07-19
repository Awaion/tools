# Spring Boot 3 事件

## 代码地址

- Gitee: https://gitee.com/Awaion/tools/tree/master/demo015
- Github: https://github.com/Awaion/tools/tree/master/demo015

## 简介

如题

## 演示

http://localhost:8080/register?username=%E5%BC%A0%E4%B8%89&password=fwkt

### SpringApplicationRunListener 实现

```
/**
 * 配置到 META-INF/spring.factories 自动加载
 */
@Slf4j
public class MySpringApplicationRunListener implements SpringApplicationRunListener {
    @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
        log.info("===引导阶段===MySpringApplicationRunListener:starting===正在启动===");
    }

    @Override
    public void environmentPrepared(ConfigurableBootstrapContext bootstrapContext, ConfigurableEnvironment environment) {
        log.info("===引导阶段===MySpringApplicationRunListener:environmentPrepared===环境准备完成===");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        log.info("===启动阶段===MySpringApplicationRunListener:contextPrepared===ioc容器准备完成===");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        log.info("===启动阶段===MySpringApplicationRunListener:contextLoaded===ioc容器加载完成===");
    }

    @Override
    public void started(ConfigurableApplicationContext context, Duration timeTaken) {
        log.info("===启动阶段===MySpringApplicationRunListener:started===启动完成===");
    }

    @Override
    public void ready(ConfigurableApplicationContext context, Duration timeTaken) {
        log.info("===启动阶段===MySpringApplicationRunListener:ready===准备就绪===");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        log.info("===启动失败===MySpringApplicationRunListener:failed===应用启动失败===");
    }
}
```

## 应用事件驱动开发

应用启动过程生命周期事件感知有定义9大事件,应用运行中可自定义事件满足业务需求

如果业务逻辑之间没有相互依赖和干扰,可以考虑使用事件驱动开发.适用于单机,应用事件在虚拟机和虚拟机之间是不通信的.

```text
@Service
public class MyEventPublisher implements ApplicationEventPublisherAware {

    ApplicationEventPublisher applicationEventPublisher;

    public void mySendEvent(ApplicationEvent event) {
        // 发布事件
        applicationEventPublisher.publishEvent(event);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}

/**
 * 自定义事件
 */
public class MyEvent extends ApplicationEvent {
    public MyEvent(Object source) {
        super(source);
    }
}

@Order(1)
@EventListener
public void onEvent1(MyEvent myEvent) {
    log.info("===事件监听===MyEventListener:onEvent1==={}", myEvent);
    String source = (String) myEvent.getSource();
    demo1Service.biz01(source);
}

@Order(2)
@EventListener
public void onEvent2(MyEvent myEvent) {
    log.info("===事件监听===MyEventListener:onEvent2==={}", myEvent);
    String source = (String) myEvent.getSource();
    demo2Service.biz02(source);
}

@Order(3)
@EventListener
public void onEvent3(MyEvent myEvent) {
    log.info("===事件监听===MyEventListener:onEvent3==={}", myEvent);
    String source = (String) myEvent.getSource();
    demo3Service.biz03(source);
}

@Autowired
private MyEventPublisher publisher;

/**
 * 请求 http://localhost:8080/register?username=%E5%BC%A0%E4%B8%89&password=fwkt
 */
@GetMapping("/register")
public String register(String username, String password) {
    log.info("===注册===Demo015Controller:register===username:{}===password:{}", username, password);
    // 发布注册成功事件
    publisher.mySendEvent(new MyEvent("username:" + username + ",password:" + password));
    return "注册成功";
}
```

### 技术说明

#### 后端

| 技术           | 说明                | 官网                                           |
|--------------| ------------------- | ---------------------------------------------- |
| SpringBoot 3 | SpringBoot     | https://spring.io/projects/spring-boot         |
| SpringWeb    | Web应用开发框架     | https://spring.io/projects/spring-boot         |

#### 开发工具

| 工具          | 说明                | 官网                                            |
| ------------- | ------------------- | ----------------------------------------------- |
| IDEA          | 开发IDE             | https://www.jetbrains.com/idea/download         |

#### 开发环境

| 工具     | 版本号  | 下载                                                                                 |
|--------| ------ | ------------------------------------------------------------                         |
| JDK  | 17  | https://www.oracle.com/java/technologies/downloads/#java17 |

#### 启动方式

main方法启动

## 许可证

[MIT License](https://opensource.org/license/mit)

Copyright (c) 2024-2024 Awaion

