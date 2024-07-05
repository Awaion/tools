package com.awaion.demo032.service.impl.output;

import com.awaion.demo032.models.ActorsFilms;
import com.awaion.demo032.service.AbstractTongYiServiceImpl;
import com.awaion.demo032.service.TongYiService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class TongYiOutputParseServiceImpl extends AbstractTongYiServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(TongYiService.class);
    private final ChatClient chatClient;

    public TongYiOutputParseServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public ActorsFilms genOutputParse(String actor) {
        var outputParser = new BeanOutputParser<>(ActorsFilms.class);

        String format = outputParser.getFormat();
        logger.info("format: " + format);
        String userMessage = """
                Generate the filmography for the actor {actor}.
                {format}
                """;
        PromptTemplate promptTemplate = new PromptTemplate(userMessage, Map.of("actor", actor, "format", format));
        Prompt prompt = promptTemplate.create();
        Generation generation = chatClient.call(prompt).getResult();

        // {@link BeanOutputParser#getFormat}
        // simple solve.
        String content = generation.getOutput().getContent()
                .replace("```json", "")
                .replace("```", "");

        return outputParser.parse(content);
    }
}
