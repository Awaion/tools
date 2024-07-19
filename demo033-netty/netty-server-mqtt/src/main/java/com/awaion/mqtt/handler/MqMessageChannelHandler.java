package com.awaion.mqtt.handler;

import com.awaion.mqtt.rabbit.MqMessage;
import com.awaion.mqtt.rabbit.ProducerService;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@ChannelHandler.Sharable
@Component
@Slf4j
public class MqMessageChannelHandler extends ChannelInboundHandlerAdapter {
    // 消息队列请求处理器
    @Autowired
    ProducerService producerService;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        log.info("===MqMessageChannelHandler msg:{}", msg);
        if (!(msg instanceof MqMessage)) {
            return;
        }
        MqMessage mqMessage = (MqMessage) msg;
        log.info("===转发到Rabbitmq Server：" + mqMessage.data);
        producerService.sendData(mqMessage.data);
    }

}
