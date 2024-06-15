package com.awaion.demo022;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class MyMeterComponent {
    Counter counter = null;

    public MyMeterComponent(MeterRegistry meterRegistry) {
        // metrics 节点 新增监控信息
        counter = meterRegistry.counter("my.meter");
    }

    public void myMeterCounter() {
        // 监控信息业务处理
        counter.increment();
    }
}

