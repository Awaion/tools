package com.awaion.demo015.publisher;

import com.awaion.demo015.Service.Demo1Service;
import com.awaion.demo015.Service.Demo2Service;
import com.awaion.demo015.Service.Demo3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MyEventListener {
    @Autowired
    private Demo1Service demo1Service;
    @Autowired
    private Demo2Service demo2Service;
    @Autowired
    private Demo3Service demo3Service;

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
}
