package com.awaion.demo026;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.atomic.AtomicReference;

//@EnableWebFlux // 完全自定义,WebFluxAutoConfiguration自动配置失效
@SpringBootApplication
public class Demo026Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo026Application.class, args);
    }

    public static void main1(String[] args) throws IOException {
        //编写一个能处理请求的服务器

        // 创建http请求reactive处理器
        HttpHandler handler = (ServerHttpRequest request, ServerHttpResponse response) -> {
//            response.getHeaders(); // 获取响应头
//            response.getCookies(); // 获取Cookie
//            response.getStatusCode(); // 获取响应状态码
//            response.bufferFactory(); // buffer 工厂
//            response.writeWith() // 把xxx写出去
//            response.setComplete(); // 设置响应完成

            URI uri = request.getURI();
            System.out.println(Thread.currentThread() + "请求url:" + uri);

            // 获取响应 DataBufferFactory
            DataBufferFactory factory = response.bufferFactory();

            // 包装响应 DataBuffer
            DataBuffer buffer = factory.wrap((uri + " ==> Hello Reactive").getBytes());

            // 响应写出 Mono<DataBuffer>,Flux<DataBuffer>
            return response.writeWith(Mono.just(buffer));
        };

        // 创建http请求reactive适配器
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(handler);

        // 创建处理http请求的netty服务器
        HttpServer.create()
                .host("localhost")
                .port(8080)
                .handle(adapter)
                .bindNow();

        System.out.println("服务器启动 => 绑定主机ip:localhost => 监听端口:8080");
        startKeepAliveThread();
        System.out.println("服务器启动完成 => keep-alive线程已启动");

    }

    private static AtomicReference<Thread> atomicReference = new AtomicReference();

    private static void startKeepAliveThread() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(Long.MAX_VALUE);
                } catch (InterruptedException var1) {
                    return;
                }
            }
        });
        if (atomicReference.compareAndSet((Thread) null, thread)) {
            thread.setDaemon(false);
            thread.setName("keep-alive");
            thread.start();
        }
    }

}
