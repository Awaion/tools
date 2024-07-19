package com.awaion.demo032.service;

import com.awaion.demo032.models.ActorsFilms;
import com.awaion.demo032.models.Completion;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.image.ImageResponse;

import java.util.Map;

public interface TongYiService {

    /**
     * 对话
     *
     * @param message 提问
     * @return 回答, 字符串返回
     */
    String completion(String message);

    /**
     * 对话
     *
     * @param message 提问
     * @return 回答, 流式返回
     */
    Map<String, String> streamCompletion(String message);

    /**
     * 对话
     *
     * @param actor 提问
     * @return 回答, 对象返回
     */
    ActorsFilms genOutputParse(String actor);

    /**
     * 使用提示模版对话
     *
     * @param adjective 形容词.
     * @param topic     主题.
     * @return 回答
     */
    AssistantMessage genPromptTemplates(String adjective, String topic);

    /**
     * 角色扮演对话
     *
     * @param message 提问
     * @param name    演员名
     * @param voice   演员角色
     * @return 回答
     */
    AssistantMessage genRole(String message, String name, String voice);

    /**
     * 根据内容进行总结
     *
     * @param message 内容
     * @param stuffit
     * @return Completion object.
     */
    Completion stuffCompletion(String message, boolean stuffit);

    /**
     * 图片生成
     *
     * @param imgPrompt 提示信息
     * @return {@link ImageResponse}
     */
    ImageResponse genImg(String imgPrompt);

    /**
     * 音频生成
     *
     * @param text 提示信息
     * @return ByteBuffer object.
     */
    String genAudio(String text);

}
