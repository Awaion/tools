package com.awaion.demo032.service;

import com.awaion.demo032.models.ActorsFilms;
import com.awaion.demo032.models.Completion;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.image.ImageResponse;

import java.util.Map;

public abstract class AbstractTongYiServiceImpl implements TongYiService {
    private static final String INFO_PREFIX = "please implement ";
    private static final String INFO_SUFFIX = "() method.";

    @Override
    public String completion(String message) {
        throw new RuntimeException(INFO_PREFIX + Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    @Override
    public Map<String, String> streamCompletion(String message) {
        throw new RuntimeException(INFO_PREFIX + Thread.currentThread().getStackTrace()[2].getMethodName() + INFO_SUFFIX);
    }

    @Override
    public ActorsFilms genOutputParse(String actor) {
        throw new RuntimeException(INFO_PREFIX + Thread.currentThread().getStackTrace()[2].getMethodName() + INFO_SUFFIX);
    }

    @Override
    public AssistantMessage genPromptTemplates(String adjective, String topic) {
        throw new RuntimeException(INFO_PREFIX + Thread.currentThread().getStackTrace()[2].getMethodName() + INFO_SUFFIX);
    }

    @Override
    public AssistantMessage genRole(String message, String name, String voice) {
        throw new RuntimeException(INFO_PREFIX + Thread.currentThread().getStackTrace()[2].getMethodName() + INFO_SUFFIX);
    }

    @Override
    public Completion stuffCompletion(String message, boolean stuffit) {
        throw new RuntimeException(INFO_PREFIX + Thread.currentThread().getStackTrace()[2].getMethodName() + INFO_SUFFIX);
    }

    @Override
    public ImageResponse genImg(String imgPrompt) {
        throw new RuntimeException(INFO_PREFIX + Thread.currentThread().getStackTrace()[2].getMethodName() + INFO_SUFFIX);
    }

    @Override
    public String genAudio(String text) {
        throw new RuntimeException(INFO_PREFIX + Thread.currentThread().getStackTrace()[2].getMethodName() + INFO_SUFFIX);
    }

}
