package com.awaion.demo025;

import org.reactivestreams.Subscription;
import reactor.core.publisher.*;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class ReactorFluxDemo {

    public static void main(String[] args) throws Exception {
        // 数据扁平化(拆分)
//        flatMap();

        // 转换
//        transform();

        // 空数据设置默认值
//        switchIfEmpty();

        // 合并
//        merge();

        // 结对
//        zipWith();

        // 重试
//        retry();

        // 接收器,管道
//        sinks();

        // 缓存
//        cache();

        // 多线程
//        parallel();

        // 上下文
//        context();

        // 阻塞
//        block();

        // 忽略异常并记录,继续执行
//        onErrorContinue();

        // 最终操作
//        doFinally();

        // 捕获异常,记录特殊的错误日志,重新抛出
//        doOnError2();

        // 包装重新抛出异常;吃掉异常,消费者有感知;抛新异常;流异常完成
//        onErrorMap();

        // 吃掉异常,消费者有感知;调用一个自定义方法;流异常完成
//        onErrorResume2();

        // 吃掉异常,消费者无异常感知;调用一个兜底方法;流正常完成
//        onErrorResume();

        // 吃掉异常,消费者无异常感知;返回一个兜底默认值;流正常完成
//        onErrorReturn();

        // 自定义线程调度
//        scheduler();

        // 自定义流中元素处理规则
//        handle();

        // 限流
//        limitRate();

        // 数据缓冲量
//        buffer();

        // 自定义消费者
//        baseSubscribe();

        // 自定义流的信号感知回调
//        subscribe();

        // flux使用
//        flux();

        // subscribe 事件回调,钩子函数(Hook),doOnXxx
//        fluxSubscribeDoOn();

        // doOn 钩子(Hook)函数 流中某个元素到达以后触发我一个回调,卸载触发流的后面,新流的前面
//        doOnXxxx();

        // 连接合并
//        concat();

        // 执行顺序
//        executionOrder();


        //今天： Flux、Mono、弹珠图、事件感知API、每个操作都是操作的上个流的东西
    }

    private static void flatMap() {
        Flux.just("zhang san", "li si")
                .flatMap(v -> {
                    String[] s = v.split(" ");
                    return Flux.fromArray(s); // 把数据包装成多元素流
                })
                .log()
                .subscribe(); // 两个人的名字按照空格拆分,打印出所有的姓与名
    }

    private static void transform() {
        AtomicInteger atomic = new AtomicInteger(0);
        Flux<String> flux = Flux.just("a", "b", "c")
                .transform(values -> {
                    if (atomic.incrementAndGet() == 1) {
                        return values.map(String::toUpperCase);
                    } else {
                        return values;
                    }
                });

        //transform 无defer,不会共享外部变量的值.无状态转换原理,无论多少个订阅者,transform只执行一次
        //transform 有defer,会共享外部变量的值.有状态转换;原理,无论多少个订阅者,每个订阅者transform都只执行一次
        flux.subscribe(v -> System.out.println("订阅者1：v = " + v));
        flux.subscribe(v -> System.out.println("订阅者2：v = " + v));
    }

    private static void switchIfEmpty() {
        Mono.empty()
                .switchIfEmpty(Mono.just("兜底数据..."))
                .subscribe(v -> System.out.println("v = " + v));
    }

    private static void merge() throws IOException {
        // concat 连接 A流 所有元素和 B流所有元素拼接
        // merge 合并 A流 所有元素和 B流所有元素 按照时间序列合并
        // mergeWith
        // mergeSequential 按照哪个流先发元素排队
//        Flux.mergeSequential();
        Flux.merge(
                        Flux.just(1, 2, 3).delayElements(Duration.ofSeconds(1)),
                        Flux.just("a", "b").delayElements(Duration.ofMillis(1500)),
                        Flux.just("q", "w", "e", "r").delayElements(Duration.ofMillis(500)))
                .log()
                .subscribe();

//        Flux.just(1, 2, 3).mergeWith(Flux.just(4, 5, 6)).log().subscribe();
        System.in.read();
    }

    private static void zipWith() {
        Flux.just(1, 2, 3)
                .zipWith(Flux.just("a", "b", "c", "d"))
                .map(tuple -> {
                    Integer t1 = tuple.getT1(); // 元组中的第一个元素
                    String t2 = tuple.getT2(); // 元组中的第二个元素
                    return t1 + "==>" + t2;
                })
                .log()
                .subscribe(v -> System.out.println("v = " + v));
    }

    private static void retry() throws IOException {
        Flux.just(1)
                .delayElements(Duration.ofSeconds(3))
                .log()
                .timeout(Duration.ofSeconds(2))
                .retry(2) // 把流从头到尾重新请求一次
                .onErrorReturn(2)
                .map(i -> i + ":map")
                .subscribe(v -> System.out.println("v = " + v));
        System.in.read();
    }

    private static void cache() throws IOException {
        Flux<Integer> cache = Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .cache(2);   // 缓存两个元素,默认全部缓存
        cache.subscribe();

        // 自定义订阅者
        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            cache.subscribe(v -> System.out.println("v = " + v));
        }).start();

        System.in.read();
    }

    private static void sinks() {
        //        Flux.create(fluxSink -> fluxSink.next("111"));

        // Sinks 接收器,数据管道,所有数据顺着这个管道往下走的

        // 发送Flux数据
//        Sinks.many();
        // 发送Mono数据
//        Sinks.one();

        // 单播 这个管道只能绑定单个订阅者(消费者)
//        Sinks.many().unicast();
        // 多播 这个管道能绑定多个订阅者
//        Sinks.many().multicast();
        // 重放 这个管道能重放元素,是否给后来的订阅者把之前的元素依然发给它
//        Sinks.many().replay();

        // 从头消费还是从订阅的那一刻消费
//        Sinks.Many<Object> many = Sinks.many()
//                .multicast()
//                .onBackpressureBuffer(); // 背压队列

        // 发布者数据重放,底层利用队列进行缓存之前数据 默认订阅者从订阅的那一刻开始接元素
        Sinks.Many<Object> many = Sinks.many().replay().limit(3);
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                many.tryEmitNext("a-" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        // 订阅
        many.asFlux().subscribe(v -> System.out.println("v1 = " + v));
        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            many.asFlux().subscribe(v -> System.out.println("v2 = " + v));
        }).start();
    }

    private static void parallel() throws IOException {
        Flux.range(1, 1000000)
                .buffer(100)
                .parallel(8)
                .runOn(Schedulers.newParallel("yy"))
                .log()
                .flatMap(list -> Flux.fromIterable(list))
                .collectSortedList(Integer::compareTo)
                .subscribe(v -> System.out.println("v = " + v));
        System.in.read();
    }

    private static void context() {
        Flux.just(1, 2, 3)
                .transformDeferredContextual((flux, context) -> {
                    System.out.println("flux = " + flux);
                    System.out.println("context = " + context);
                    return flux.map(i -> i + "==>" + context.get("prefix"));
                })
                // 上游能拿到下游的最近一次数据,和 ThreadLocal 相反,ThreadLocal在响应式编程中无法使用,因为线程会频繁切换
                .contextWrite(Context.of("prefix", "前缀"))
                .subscribe(v -> System.out.println("v = " + v));
    }

    private static void block() {
        Integer block = Flux.just(1, 2, 3)
                .next()
                .block();
        System.out.println(block);
    }

    private static void onErrorContinue() {
        Flux.just(1, 2, 3, 0, 5)
                .map(i -> 10 / i)
                .onErrorContinue((err, val) -> {
                    System.out.println("err = " + err);
                    System.out.println("val = " + val);
                    System.out.println("发现" + val + "有问题了,继续执行其他的,我会记录这个问题");
                })
                .subscribe(v -> System.out.println("v = " + v),
                        err -> System.out.println("err = " + err));
    }

    private static void doFinally() {
        Flux.just(1, 2, 3, 4)
                .map(i -> "100 / " + i + " = " + (100 / i))
                .doOnError(err -> {
                    System.out.println("err已被记录 = " + err);
                })
                .doFinally(signalType -> {
                    System.out.println("最终操作：" + signalType);
                })
                .subscribe();
    }

    private static void doOnError2() {
        Flux.just(1, 2, 0, 4)
                .map(i -> "100 / " + i + " = " + (100 / i))
                .doOnError(err -> {
                    System.out.println("err已被记录 = " + err);
                }).subscribe(v -> System.out.println("v = " + v),
                        err -> System.out.println("err = " + err),
                        () -> System.out.println("流结束"));
    }

    private static void onErrorMap() {
        Flux.just(1, 2, 0, 4)
                .map(i -> "100 / " + i + " = " + (100 / i))
                .onErrorMap(err -> new RuntimeException(err.getMessage() + ": 又炸了..."))
                .subscribe(v -> System.out.println("v = " + v),
                        err -> System.out.println("err = " + err),
                        () -> System.out.println("流结束"));
    }

    private static void onErrorResume2() {
        Flux.just(1, 2, 0, 4)
                .map(i -> "100 / " + i + " = " + (100 / i))
                .onErrorResume(err -> Flux.error(new RuntimeException(err.getMessage() + ":炸了")))
                .subscribe(v -> System.out.println("v = " + v),
                        err -> System.out.println("err = " + err),
                        () -> System.out.println("流结束"));
    }

    private static void onErrorResume() {
        Flux.just(1, 2, 0, 4)
                .map(i -> "100 / " + i + " = " + (100 / i))
                .onErrorResume(err -> Mono.just("onErrorResume-"))
                .subscribe(v -> System.out.println("v = " + v),
                        err -> System.out.println("err = " + err),
                        () -> System.out.println("流结束"));
    }

    private static void onErrorReturn() {
        Flux.just(1, 2, 0, 4)
                .map(i -> "100 / " + i + " = " + (100 / i))
                .onErrorReturn(NullPointerException.class, "onErrorReturn-NullPointerException")
//                .onErrorReturn(ArithmeticException.class,"onErrorReturn-ArithmeticException")
                .subscribe(v -> System.out.println("v = " + v),
                        err -> System.out.println("err = " + err),
                        () -> System.out.println("流结束"));
    }

    private static void scheduler() {
        Scheduler s1 = Schedulers.newParallel("parallel-scheduler1", 4);
        Scheduler s2 = Schedulers.newParallel("parallel-scheduler2", 4);

        final Flux<String> flux = Flux
                .range(1, 2)
                .map(i -> 10 + i)
                .log()
                .publishOn(s1)
                .map(i -> "value " + i);

        flux.subscribeOn(s2).subscribe();

        // 不指定线程池,默认发布者用的线程就是订阅者的线程；
        new Thread(() -> flux.subscribe(System.out::println)).start();
    }

    private static void handle() {
        Flux.range(1, 10)
                .handle((value, sink) -> {
                    System.out.println("原始值：" + value);
                    sink.next("sink:" + value);
                })
                .log()
                .subscribe();
    }

    private static void limitRate() {
        Flux.range(1, 1000)
                .log()
                .limitRate(100) // 用完3/4就会取3/4.第一次request(100),以后request(75)
                .subscribe();
    }

    private static void buffer() {
        Flux.range(1, 10)
                .buffer(3)
                .log()
                .subscribe();
    }

    private static void baseSubscribe() {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);
        flux.subscribe(new BaseSubscriber<Integer>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                System.out.println("订阅关系绑定的时候触发-" + subscription);
                // 找发布者要数据
                request(1);
                // 要无限数据
//                requestUnbounded();
            }

            @Override
            protected void hookOnNext(Integer value) {
                System.out.println("数据到达,正在处理:" + value);
                request(1); //要1个数据
            }

            @Override
            protected void hookOnComplete() {
                System.out.println("流正常结束...");
            }

            @Override
            protected void hookOnError(Throwable throwable) {
                System.out.println("流异常..." + throwable);
            }

            @Override
            protected void hookOnCancel() {
                System.out.println("流被取消...");
            }

            @Override
            protected void hookFinally(SignalType type) {
                System.out.println("最终回调...一定会被执行");
            }
        });
    }

    private static void subscribe() {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);
        flux.subscribe(
                v -> System.out.println("v = " + v),
                throwable -> System.out.println("异常 = " + throwable),
                () -> System.out.println("流结束了...")
        );
    }

    /**
     * 连接合并
     */
    private static void concat() {
        // concatMap 一个元素可以变很多单个,对于元素类型无限制
        // concat Flux.concat,静态调用
        // concatWith 连接的流和老流中的元素类型要一样
        Flux.concat(Flux.just(1, 2, 3), Flux.just(7, 8, 9))
                .subscribe(System.out::println);
    }

    /**
     * 执行顺序
     */
    private static void executionOrder() {
        Flux.range(1, 7)
                .log()
                .filter(i -> i > 3)
                .log()
                .map(i -> "map-" + i)
                .log()
                .subscribe(System.out::println);
    }

    /**
     * doOn 钩子(Hook)函数 流中某个元素到达以后触发我一个回调,卸载触发流的后面,新流的前面
     * 响应式编程核心：看懂文档弹珠图；
     * 信号： 正常/异常（取消）
     * SignalType：
     * SUBSCRIBE： 被订阅
     * REQUEST：  请求了N个元素
     * CANCEL： 流被取消
     * ON_SUBSCRIBE：在订阅时候
     * ON_NEXT： 在元素到达
     * ON_ERROR： 在流错误
     * ON_COMPLETE：在流正常完成时
     * AFTER_TERMINATE：中断以后
     * CURRENT_CONTEXT：当前上下文
     * ON_CONTEXT：感知上下文
     * <p>
     * doOnXxx API触发时机
     * 1、doOnNext：每个数据（流的数据）到达的时候触发
     * 2、doOnEach：每个元素（流的数据和信号）到达的时候触发
     * 3、doOnRequest： 消费者请求流元素的时候
     * 4、doOnError：流发生错误
     * 5、doOnSubscribe: 流被订阅的时候
     * 6、doOnTerminate： 发送取消/异常信号中断了流
     * 7、doOnCancle： 流被取消
     * 8、doOnDiscard：流中元素被忽略的时候
     */
    public static void doOnXxxx() {
        Flux.just(1, 2, 3, 4, 5, 6, 7, 0, 5, 6)
                .doOnNext(integer -> System.out.println("元素到达1：" + integer))
                .doOnEach(integerSignal -> {
                    System.out.println("doOnEach.." + integerSignal);
                })
                .map(integer -> 10 / integer)
                .doOnError(throwable -> {
                    System.out.println("数据库已经保存了异常：" + throwable.getMessage());
                })
                .map(integer -> 100 / integer)
                .doOnNext(integer -> System.out.println("元素到达2：" + integer))
                .subscribe(System.out::println);
    }

    public static void fluxSubscribeDoOn() throws Exception {
        // subscribe 事件回调,钩子函数(Hook),doOnXxx
        Flux<Integer> flux = Flux.range(1, 7)
                .delayElements(Duration.ofSeconds(1))
                .doOnComplete(() -> System.out.println("流正常结束..."))
                .doOnCancel(() -> System.out.println("流已被取消..."))
                .doOnError(throwable -> System.out.println("流出错..." + throwable))
                .doOnNext(integer -> System.out.println("doOnNext..." + integer));

        flux.subscribe(new BaseSubscriber<>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                System.out.println("订阅者和发布者绑定好了：" + subscription);
                request(1);
            }

            @Override
            protected void hookOnNext(Integer value) {
                System.out.println("元素到达：" + value);
                if (value < 5) {
                    if (value == 3) {
                        int i = 10 / 0;
                    }
                    request(1);
                } else {
                    cancel();
                }
            }

            @Override
            protected void hookOnComplete() {
                System.out.println("数据流结束");
            }

            @Override
            protected void hookOnError(Throwable throwable) {
                System.out.println("数据流异常");
            }

            @Override
            protected void hookOnCancel() {
                System.out.println("数据流被取消");
            }

            @Override
            protected void hookFinally(SignalType type) {
                System.out.println("结束信号：" + type);
            }
        });

        System.in.read();
    }

    public static void flux() throws Exception {
        // Mono:0|1个元素的流, Flux:N个元素的流, Flux 有 empty 方法
        Flux<Integer> just = Flux.just(1, 2, 3, 4, 5); //

        // 流不消费就不执行,一个数据流可以有很多消费者,流是通过广播模式给消费者
        just.subscribe(e -> System.out.println("e1 = " + e));
        just.subscribe(e -> {
            System.out.println("e2 = " + e);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });

        System.out.println("=====每秒产生一个从0开始的递增数字=====");
        Flux<Long> flux = Flux.interval(Duration.ofSeconds(1));
        flux.subscribe(System.out::println);

        System.in.read();
    }
}

