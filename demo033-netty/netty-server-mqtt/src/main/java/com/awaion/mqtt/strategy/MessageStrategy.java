package com.awaion.mqtt.strategy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttMessage;

public interface MessageStrategy {
    void sendResponseMessage(ChannelHandlerContext channelHandlerContext, MqttMessage mqttMessage);
}
