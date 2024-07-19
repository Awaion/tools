package com.awaion.demo024;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class FlowDemo {

    public static void main(String[] args) throws InterruptedException {
        // 发布订阅模型 观察者模式

        // 创建发布者
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

        // 创建处理器
        MyProcessor myProcessor1 = new MyProcessor();
        MyProcessor myProcessor2 = new MyProcessor();
        MyProcessor myProcessor3 = new MyProcessor();

        // 创建订阅者
        Flow.Subscriber<String> mySubscriber1 = new MySubscriber();
        Flow.Subscriber<String> mySubscriber2 = new MySubscriber();

        // 发布者和处理器绑定
        publisher.subscribe(myProcessor1);
        // 处理器和处理器绑定
        myProcessor1.subscribe(myProcessor2);
        // 处理器和处理器绑定
        myProcessor2.subscribe(myProcessor3);

        // 处理器和订阅者绑定
        myProcessor3.subscribe(mySubscriber1);
        // 处理器和订阅者绑定
        myProcessor3.subscribe(mySubscriber2);

        for (int i = 0; i < 10; i++) {
            if (i == 5) {
//                publisher.closeExceptionally(new RuntimeException("5555"));
            } else {
                // 发布者发布信息
                publisher.submit("p-" + i);
            }
        }

        // 发布者通道关闭
        publisher.close();

        Thread.sleep(10000);
    }

    /**
     * 自定义处理器
     */
    static class MyProcessor extends SubmissionPublisher<String> implements Flow.Processor<String, String> {

        private Flow.Subscription subscription;

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            // 处理器和订阅绑定
            this.subscription = subscription;
            // 获取订阅1个数据
            subscription.request(1);
            System.out.println("处理器和订阅绑定:" + this.hashCode());
        }

        @Override
        public void onNext(String item) {
            // 对数据进行处理
            item += ":处理";
            // 对处理后数据提交,给下一个处理器处理
            super.submit(item);
            // 获取订阅1个数据
            subscription.request(1);
            System.out.println("处理数据:" + this.hashCode() + ":" + item);
        }

        @Override
        public void onError(Throwable throwable) {
            System.out.println("处理器异常");
        }

        @Override
        public void onComplete() {
            System.out.println("处理器完成");
        }
    }

    /**
     * 自定义订阅者
     */
    static class MySubscriber implements Flow.Subscriber<String> {

        private Flow.Subscription subscription;

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            // 订阅者和订阅绑定
            this.subscription = subscription;
            // 获取一个数据
            subscription.request(1);
            System.out.println("订阅者和订阅绑定:" + this.hashCode());
        }

        @Override //在下一个元素到达时； 执行这个回调；   接受到新数据
        public void onNext(String item) {
            System.out.println("线程:" + Thread.currentThread() + ":" + this.hashCode() + ":订阅者接收数据:" + item);
            if (item.equals("p-7")) {
                // 取消订阅
                subscription.cancel();
            } else {
                // 获取一个数据
                subscription.request(1);
            }
        }

        @Override
        public void onError(Throwable throwable) {
            System.out.println("线程:" + Thread.currentThread() + "订阅者错误:" + throwable);
        }

        @Override
        public void onComplete() {
            System.out.println("线程:" + Thread.currentThread() + "订阅者完成");
        }

    }
}

