package com.awaion.netty_client_mqtt;

import io.github.netty.mqtt.client.*;
import io.github.netty.mqtt.client.constant.MqttVersion;
import io.github.netty.mqtt.client.msg.MqttMsgInfo;
import io.github.netty.mqtt.client.msg.MqttWillMsg;
import io.github.netty.mqtt.client.store.MemoryMqttMsgStore;
import io.github.netty.mqtt.client.store.MqttMsgStore;
import io.netty.handler.codec.mqtt.MqttQoS;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqttController {
    private MqttClient client;

    public MqttController() {
        //创建MQTT全局配置器（也可以不创建）
        MqttConfiguration mqttConfiguration = new MqttConfiguration(2);
        //创建MQTT客户端工厂
        MqttClientFactory mqttClientFactory = new DefaultMqttClientFactory(mqttConfiguration);
        //使用内存消息存储器（默认）
        MqttMsgStore mqttMsgStore = new MemoryMqttMsgStore();
        mqttClientFactory.setMqttMsgStore(mqttMsgStore);
        //创建连接参数，设置客户端ID
        //创建连接参数，设置客户端ID
        MqttConnectParameter mqttConnectParameter = new MqttConnectParameter("netty-client-mqtt-test");
        //设置客户端版本（默认为3.1.1）
        mqttConnectParameter.setMqttVersion(MqttVersion.MQTT_3_1_1);
        //是否自动重连
        mqttConnectParameter.setAutoReconnect(true);
        //Host
        mqttConnectParameter.setHost("127.0.0.1");
        //端口
        mqttConnectParameter.setPort(1883);
        //是否使用SSL/TLS
        mqttConnectParameter.setSsl(false);
        //遗嘱消息
        MqttWillMsg mqttWillMsg = new MqttWillMsg("test", "hello matt2".getBytes(), MqttQoS.EXACTLY_ONCE);
        mqttConnectParameter.setWillMsg(mqttWillMsg);
        //是否清除会话
        mqttConnectParameter.setCleanSession(true);
        //心跳间隔
        mqttConnectParameter.setKeepAliveTimeSeconds(60);
        //连接超时时间
        mqttConnectParameter.setConnectTimeoutSeconds(30);
        //创建一个客户端
        MqttClient mqttClient = mqttClientFactory.createMqttClient(mqttConnectParameter);
        //添加回调器
        mqttClient.addMqttCallback(new DefaultMqttCallback());
        mqttClient.connect();
        client = mqttClient;
    }

    @GetMapping("/sendMessage")
    public String sendMessage() {
        // http://localhost:8081/sendMessage
        client.publishFuture(new MqttMsgInfo("test", "hello matt".getBytes(), MqttQoS.EXACTLY_ONCE));
        return "success";
    }
}
