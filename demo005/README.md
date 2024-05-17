# SpringBoot + Vue3.4

## 代码地址

- Gitee: https://gitee.com/Awaion/tools/tree/master/demo005
- Github: https://github.com/Awaion/tools/tree/master/demo005

## 简介

Vue开发的前端代码在打包后本质上还是html,css,js等浏览器可解析的文件,将打包文件放入静态文件夹即可

前端使用mock服务器模拟api,这里在控制器把请求重新发给mock,然后响应给前端

前端代码地址(感谢分享): https://github.com/HalseySpicy/Geeker-Admin

Vue.js官方文档: https://cn.vuejs.org/

## 演示
本地服务启动访问地址：http://localhost

![首页](src/main/resources/document/20240412104732.png)

## 集成步骤

1. 将 Vue 打包好的前端文件放在 resources/static 目录下

2. pom.xml 新增web依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

3. web调用mock服务器
```
@PostMapping(value = "/api/**")
@Operation(summary = "内部调用")
public ResponseEntity<?> api3(@RequestBody Map body, HttpServletRequest request) throws ServletException, IOException {
    String requestUrl = request.getRequestURI().toString();
    requestUrl = requestUrl.substring(4);
    String url = "https://mock.mengxuegu.com/mock/629d727e6163854a32e8307e" + requestUrl;

    // 转换原始请求信息为HttpEntity
    HttpHeaders headers = new HttpHeaders();
    Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
        String headerName = headerNames.nextElement();
        headers.add(headerName, request.getHeader(headerName));
    }

    HttpEntity<?> entity = new HttpEntity<>(body, headers);

    ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.resolve(request.getMethod()), entity, Object.class);
    return response;
}

@GetMapping(value = "/api/**")
@Operation(summary = "内部调用")
public ResponseEntity<?> api4(Map body, HttpServletRequest request) throws ServletException, IOException {
    String requestUrl = request.getRequestURI().toString();
    requestUrl = requestUrl.substring(4);
    String url = "https://mock.mengxuegu.com/mock/629d727e6163854a32e8307e" + requestUrl;

    // 转换原始请求信息为HttpEntity
    HttpHeaders headers = new HttpHeaders();
    Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
        String headerName = headerNames.nextElement();
        headers.add(headerName, request.getHeader(headerName));
    }

    HttpEntity<?> entity = new HttpEntity<>(body, headers);

    ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.resolve(request.getMethod()), entity, Object.class);
    return response;
}
```

## 技术说明

#### 后端

| 技术                 | 说明                | 官网                                           |
| -------------------- | ------------------- | ---------------------------------------------- |
| SpringBoot           | Web应用开发框架      | https://spring.io/projects/spring-boot         |

#### 前端

| 技术         | 说明                   | 官网                                               |
| ----------   | ---------------------  | --------------------------------------            |
| Vue.js       | 前端框架               | https://cn.vuejs.org/                             |
| Geeker-Admin | 前端模版               | https://github.com/HalseySpicy/Geeker-Admin       |

### 开发工具

| 工具          | 说明                | 官网                                            |
| ------------- | ------------------- | ----------------------------------------------- |
| IDEA          | 开发IDE             | https://www.jetbrains.com/idea/download         |
| VSCode        | 开发IDE             | https://code.visualstudio.com/                  |

### 开发环境

| 工具          | 版本号  | 下载                                                                                 |
| ------------- | ------ | ------------------------------------------------------------                         |
| JDK           | 1.8    | https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html |
| Vue.js        | v3     | https://cn.vuejs.org/                                                                |
| Node.js       | v20    | https://nodejs.org/en                                                                |


### 启动方式

main方法启动

## 许可证

[MIT License](https://opensource.org/license/mit)

Copyright (c) 2024-2024 Awaion

