package com.awaion.mqtt.strategy.impl;

import com.awaion.mqtt.strategy.MessageStrategy;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttFixedHeader;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttMessageType;
import io.netty.handler.codec.mqtt.MqttQoS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PingMessageStrategy implements MessageStrategy {
    @Override
    public void sendResponseMessage(ChannelHandlerContext channelHandlerContext, MqttMessage mqttMessage) {
        MqttFixedHeader fixedHeader = new MqttFixedHeader(MqttMessageType.PINGRESP, false, MqttQoS.AT_MOST_ONCE, false, 0);
        MqttMessage pingBackMsg = new MqttMessage(fixedHeader);
        log.info("===response message:" + pingBackMsg);
        channelHandlerContext.channel().writeAndFlush(pingBackMsg);
    }
}
