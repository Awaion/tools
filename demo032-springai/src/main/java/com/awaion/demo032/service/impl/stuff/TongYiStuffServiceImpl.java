package com.awaion.demo032.service.impl.stuff;

import com.awaion.demo032.models.Completion;
import com.awaion.demo032.service.AbstractTongYiServiceImpl;
import com.awaion.demo032.service.TongYiService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class TongYiStuffServiceImpl extends AbstractTongYiServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(TongYiService.class);
    private final ChatClient chatClient;

    public TongYiStuffServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Value("classpath:/docs/wikipedia-curling.md")
    private Resource docsToStuffResource;

    @Value("classpath:/prompts/qa-prompt.st")
    private Resource qaPromptResource;

    // TongYi model: Range of input length should be [1, 6000]
    @Override
    public Completion stuffCompletion(String message, boolean stuffit) {

        PromptTemplate promptTemplate = new PromptTemplate(qaPromptResource);
        Map<String, Object> map = new HashMap<>();
        map.put("question", message);

        if (stuffit) {
            map.put("context", docsToStuffResource);
        } else {
            map.put("context", "");
        }

        Prompt prompt = promptTemplate.create(map);
        Generation generation = chatClient.call(prompt).getResult();
        return new Completion(generation.getOutput().getContent());
    }
}
