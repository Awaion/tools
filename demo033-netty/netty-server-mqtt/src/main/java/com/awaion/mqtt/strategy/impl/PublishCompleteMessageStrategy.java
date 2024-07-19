package com.awaion.mqtt.strategy.impl;

import com.awaion.mqtt.strategy.MessageStrategy;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PublishCompleteMessageStrategy implements MessageStrategy {
    @Override
    public void sendResponseMessage(ChannelHandlerContext channelHandlerContext, MqttMessage mqttMessage) {
        MqttMessageIdVariableHeader messageIdVariableHeader = (MqttMessageIdVariableHeader) mqttMessage.variableHeader();
        //	构建返回报文
        MqttFixedHeader mqttFixedHeaderBack = new MqttFixedHeader(MqttMessageType.PUBCOMP, false, MqttQoS.AT_MOST_ONCE, false, 0x02);
        MqttMessageIdVariableHeader mqttMessageIdVariableHeaderBack = MqttMessageIdVariableHeader.from(messageIdVariableHeader.messageId());
        MqttMessage publishCompleteMsg = new MqttMessage(mqttFixedHeaderBack, mqttMessageIdVariableHeaderBack);
        log.info("====PublishCompleteMessageStrategy response message:" + publishCompleteMsg.toString());

        channelHandlerContext.channel().writeAndFlush(publishCompleteMsg);
    }
}
