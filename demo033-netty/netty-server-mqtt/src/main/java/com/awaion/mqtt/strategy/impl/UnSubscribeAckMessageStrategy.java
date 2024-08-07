package com.awaion.mqtt.strategy.impl;

import com.awaion.mqtt.strategy.MessageStrategy;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UnSubscribeAckMessageStrategy implements MessageStrategy {
    @Override
    public void sendResponseMessage(ChannelHandlerContext channelHandlerContext, MqttMessage mqttMessage) {
        /*---------------------------解析接收的消息---------------------------*/
        MqttMessageIdVariableHeader messageIdVariableHeader = (MqttMessageIdVariableHeader) mqttMessage.variableHeader();

        /*---------------------------构建返回的消息---------------------------*/
        MqttMessageIdVariableHeader variableHeaderBack = MqttMessageIdVariableHeader.from(messageIdVariableHeader.messageId());
        MqttFixedHeader mqttFixedHeaderBack = new MqttFixedHeader(MqttMessageType.UNSUBACK, false, MqttQoS.AT_MOST_ONCE, false, 2);
        MqttUnsubAckMessage unSubAckMsg = new MqttUnsubAckMessage(mqttFixedHeaderBack, variableHeaderBack);
        log.info("===UnSubscribeAckMessageStrategy response message:" + unSubAckMsg.toString());

        channelHandlerContext.channel().writeAndFlush(unSubAckMsg);
    }
}
