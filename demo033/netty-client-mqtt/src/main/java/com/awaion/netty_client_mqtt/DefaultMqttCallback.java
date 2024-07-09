package com.awaion.netty_client_mqtt;

import io.github.netty.mqtt.client.MqttConnectParameter;
import io.github.netty.mqtt.client.callback.*;

public class DefaultMqttCallback implements MqttCallback {
    @Override
    public void subscribeCallback(MqttSubscribeCallbackResult mqttSubscribeCallbackResult) {
        System.out.println("======subscribeCallback");
    }

    @Override
    public void unsubscribeCallback(MqttUnSubscribeCallbackResult mqttUnSubscribeCallbackResult) {
        System.out.println("======unsubscribeCallback");
    }

    @Override
    public void messageSendCallback(MqttSendCallbackResult mqttSendCallbackResult) {
        System.out.println("======messageSendCallback");
    }

    @Override
    public void messageReceiveCallback(MqttReceiveCallbackResult receiveCallbackResult) {
        System.out.println("======messageReceiveCallback");
    }

    @Override
    public void channelConnectCallback(MqttConnectCallbackResult mqttConnectCallbackResult) {
        System.out.println("======channelConnectCallback");
    }

    @Override
    public void connectCompleteCallback(MqttConnectCallbackResult mqttConnectCallbackResult) {
        System.out.println("======connectCompleteCallback");
    }

    @Override
    public void connectLostCallback(MqttConnectLostCallbackResult mqttConnectLostCallbackResult) {
        System.out.println("======connectLostCallback");
    }

    @Override
    public void heartbeatCallback(MqttHeartbeatCallbackResult mqttHeartbeatCallbackResult) {
        System.out.println("======heartbeatCallback");
    }

    @Override
    public void channelExceptionCaught(MqttConnectParameter mqttConnectParameter, MqttChannelExceptionCallbackResult mqttChannelExceptionCallbackResult) {
        System.out.println("======channelExceptionCaught");
    }
}
