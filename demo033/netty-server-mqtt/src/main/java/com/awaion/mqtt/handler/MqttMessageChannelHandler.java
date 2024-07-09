package com.awaion.mqtt.handler;

import com.awaion.mqtt.strategy.MessageStrategy;
import com.awaion.mqtt.strategy.MessageStrategyManager;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttMessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@ChannelHandler.Sharable
public class MqttMessageChannelHandler extends ChannelInboundHandlerAdapter {
    // MQTT（Message Queuing Telemetry Transport，消息队列遥测传输）协议处理器
    @Autowired
    MessageStrategyManager messageStrategyManager;

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) {
        log.info("===MqttMessageChannelHandler msg:{}", msg);
        if (!(msg instanceof MqttMessage)) {
            return;
        }

        MqttMessage mqttMessage = (MqttMessage) msg;
        log.info("===from client:" + channelHandlerContext.channel().remoteAddress());
        log.info("===receive message：" + mqttMessage);
        try {
            MqttMessageType type = mqttMessage.fixedHeader().messageType();
            // 返回对于消息处理器对象
            MessageStrategy messageStrategy = messageStrategyManager.getMessageStrategy(type);
            if (messageStrategy != null) {
                messageStrategy.sendResponseMessage(channelHandlerContext, mqttMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("===MqttMessageChannelHandler channelRead end");

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}
