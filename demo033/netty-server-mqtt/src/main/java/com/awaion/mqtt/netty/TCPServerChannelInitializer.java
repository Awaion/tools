package com.awaion.mqtt.netty;

import com.awaion.mqtt.handler.MqMessageChannelHandler;
import com.awaion.mqtt.handler.MqttMessageChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.codec.mqtt.MqttEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class TCPServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Autowired
    MqttMessageChannelHandler mqttMessageChannelHandler;
    @Autowired
    MqMessageChannelHandler mqMessageChannelHandler;

    public TCPServerChannelInitializer() {
        super();
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        // 频道流水线添加处理器
        socketChannel.pipeline().addLast("===mqttDecoder", new MqttDecoder());
        socketChannel.pipeline().addLast("===mqttEncoder", MqttEncoder.INSTANCE);
        //心跳超时控制
        socketChannel.pipeline().addLast("idle",
                new IdleStateHandler(15, 0, 0, TimeUnit.MINUTES));
        socketChannel.pipeline().addLast("===mqttHandler", mqttMessageChannelHandler);
        socketChannel.pipeline().addLast("===mqHandler", mqMessageChannelHandler);
    }
}
