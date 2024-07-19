package com.awaion.demo032.service.impl.audio;

import com.alibaba.cloud.ai.tongyi.audio.api.SpeechClient;
import com.alibaba.dashscope.audio.tts.SpeechSynthesisAudioFormat;
import com.awaion.demo032.service.AbstractTongYiServiceImpl;
import com.awaion.demo032.service.TongYiService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
public class TongYiAudioSimpleServiceImpl extends AbstractTongYiServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(TongYiService.class);
    private final SpeechClient speechClient;

    @Autowired
    public TongYiAudioSimpleServiceImpl(SpeechClient client) {
        this.speechClient = client;
    }

    @Override
    public String genAudio(String text) {
        logger.info("gen audio prompt is: {}", text);
        var resWAV = speechClient.call(text);
        return save(resWAV, SpeechSynthesisAudioFormat.WAV.getValue());
    }

    private String save(ByteBuffer audio, String type) {
        String currentPath = System.getProperty("user.dir");
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-HH-mm-ss");
        String fileName = currentPath + File.separator + now.format(formatter) + "." + type;
        File file = new File(fileName);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(audio.array());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return fileName;
    }

}
