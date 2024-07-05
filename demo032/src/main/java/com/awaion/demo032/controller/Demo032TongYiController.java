package com.awaion.demo032.controller;

import com.awaion.demo032.models.ActorsFilms;
import com.awaion.demo032.models.Completion;
import com.awaion.demo032.service.TongYiService;
import org.jetbrains.annotations.Nullable;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.image.ImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ai")
@CrossOrigin
public class Demo032TongYiController {
    @Autowired
    @Qualifier("tongYiSimpleServiceImpl")
    private TongYiService tongYiSimpleService;
    @Autowired
    @Qualifier("tongYiOutputParseServiceImpl")
    private TongYiService tongYiOutputService;
    @Autowired
    @Qualifier("tongYiPromptTemplateServiceImpl")
    private TongYiService tongYiPromptTemplateService;
    @Autowired
    @Qualifier("tongYiRolesServiceImpl")
    private TongYiService tongYiRolesService;
    @Autowired
    @Qualifier("tongYiStuffServiceImpl")
    private TongYiService tongYiStuffService;
    @Autowired
    @Qualifier("tongYiAudioSimpleServiceImpl")
    private TongYiService tongYiAudioService;
    @Autowired
    @Qualifier("tongYiImagesServiceImpl")
    private TongYiService tongYiImgService;

    private static HashMap<String, Integer> countMap = new HashMap<>();

    private static @Nullable String getLimit() {
        if (countMap.get(LocalDate.now().toString()) == null) {
            countMap.put(LocalDate.now().toString(), 1);
        } else {
            if (countMap.get(LocalDate.now().toString()) > 100) {
                return "访问次数过多,请明天再试";
            } else {
                countMap.put(LocalDate.now().toString(), countMap.get(LocalDate.now().toString()) + 1);
            }
        }
        return null;
    }

    @GetMapping(value = "/example")
    public String completion(@RequestParam(value = "message", defaultValue = "讲个笑话") String message) {
        // http://localhost:8080/ai/example?message=讲个笑话
        String x = getLimit();
        if (x != null) return x;
//        return String.valueOf(countMap.get(LocalDate.now().toString()));
        return tongYiSimpleService.completion(message);
    }

    @GetMapping("/streamLimit")
    public Map<String, String> streamCompletion(@RequestParam(value = "message", defaultValue = "如何将大象装进冰箱") String message) {
        // http://localhost:8080/ai/stream?message=如何将大象装进冰箱
        return tongYiSimpleService.streamCompletion(message);
    }

    @GetMapping("/outputLimit")
    public ActorsFilms generate(@RequestParam(value = "actor", defaultValue = "李小龙") String actor) {
        // http://localhost:8080/ai/output?actor=李小龙
        // https://json-schema.org/draft/2020-12/schema
        return tongYiOutputService.genOutputParse(actor);
    }

    @GetMapping("/prompt-tmplLimit")
    public AssistantMessage completion(@RequestParam(value = "adjective", defaultValue = "西红柿炒蛋") String adjective,
                                       @RequestParam(value = "topic", defaultValue = "做饭主题") String topic) {
        // http://localhost:8080/ai/prompt-tmpl?adjective=西红柿炒蛋&topic=做饭主题
        return tongYiPromptTemplateService.genPromptTemplates(adjective, topic);
    }

    @GetMapping("/rolesLimit")
    public AssistantMessage generate(@RequestParam(value = "message", defaultValue = "关于五星上将的著名言论") String message,
                                     @RequestParam(value = "name", defaultValue = "道格拉斯-麦克阿瑟") String name,
                                     @RequestParam(value = "voice", defaultValue = "五星上将") String voice) {
        // http://localhost:8080/ai/roles?message=关于五星上将的著名言论&name=道格拉斯-麦克阿瑟&voice=五星上将
        return tongYiRolesService.genRole(message, name, voice);
    }

    @GetMapping("/stuffLimit")
    public Completion completion(@RequestParam(value = "message", defaultValue = "我国第一次载人航天是在什么时候") String message,
                                 @RequestParam(value = "stuffit", defaultValue = "false") boolean stuffit) {
        // http://localhost:8080/ai/stuff?message=我国第一次载人航天是在什么时候&stuffit=false
        return tongYiStuffService.stuffCompletion(message, stuffit);
    }

    @GetMapping("/imgLimit")
    public ImageResponse genImg(@RequestParam(value = "prompt", defaultValue = "生成一张世界第一高峰珠峰图片") String imgPrompt) {
        // http://localhost:8080/ai/img?prompt=生成一张世界第一高峰珠峰图片
        return tongYiImgService.genImg(imgPrompt);
    }

    @GetMapping("/audioLimit")
    public String genAudio(@RequestParam(value = "prompt", defaultValue = "这是一段描述周星驰的音频") String prompt) {
        // http://localhost:8080/ai/audio?prompt=生产这一段周星驰的音频
        return tongYiAudioService.genAudio(prompt);
    }

}
