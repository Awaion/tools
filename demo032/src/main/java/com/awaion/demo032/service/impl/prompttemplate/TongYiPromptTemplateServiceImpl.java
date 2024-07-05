package com.awaion.demo032.service.impl.prompttemplate;

import com.awaion.demo032.service.AbstractTongYiServiceImpl;
import com.awaion.demo032.service.TongYiService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class TongYiPromptTemplateServiceImpl extends AbstractTongYiServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(TongYiService.class);
    private final ChatClient chatClient;
    @Value("classpath:/prompts/joke-prompt.st")
    private Resource jokeResource;

    public TongYiPromptTemplateServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public AssistantMessage genPromptTemplates(String adjective, String topic) {
        PromptTemplate promptTemplate = new PromptTemplate(jokeResource);
        Prompt prompt = promptTemplate.create(Map.of("adjective", adjective, "topic", topic));
        return chatClient.call(prompt).getResult().getOutput();
    }
}
