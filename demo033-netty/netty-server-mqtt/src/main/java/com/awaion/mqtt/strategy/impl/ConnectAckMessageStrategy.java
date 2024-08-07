package com.awaion.mqtt.strategy.impl;

import com.awaion.mqtt.strategy.MessageStrategy;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConnectAckMessageStrategy implements MessageStrategy {

    @Override
    public void sendResponseMessage(ChannelHandlerContext channelHandlerContext, MqttMessage mqttMessage) {
        MqttConnectMessage mqttConnectMessage = (MqttConnectMessage) mqttMessage;

        /*---------------------------解析接收的消息----------------------------*/
        MqttFixedHeader mqttFixedHeader = mqttConnectMessage.fixedHeader();
        MqttConnectVariableHeader mqttConnectVariableHeader = mqttConnectMessage.variableHeader();

        /*---------------------------构建返回的消息---------------------------*/
        //	构建返回报文， 固定报头
        MqttConnAckVariableHeader mqttConnAckVariableHeader = new MqttConnAckVariableHeader(MqttConnectReturnCode.CONNECTION_ACCEPTED, mqttConnectVariableHeader.isCleanSession());
        MqttFixedHeader mqttFixedHeaderBack = new MqttFixedHeader(MqttMessageType.CONNACK, mqttFixedHeader.isDup(), MqttQoS.AT_MOST_ONCE, mqttFixedHeader.isRetain(), 0x02);

        //	构建CONNACK消息体
        MqttConnAckMessage connAck = new MqttConnAckMessage(mqttFixedHeaderBack, mqttConnAckVariableHeader);
        log.info("===ConnectAckMessageStrategy response message:" + connAck);
        channelHandlerContext.channel().writeAndFlush(connAck);

    }
}
