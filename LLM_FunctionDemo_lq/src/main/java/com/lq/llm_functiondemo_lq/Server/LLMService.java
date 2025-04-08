package com.lq.llm_functiondemo_lq.Server;

import com.lq.llm_functiondemo_lq.DTO.response.ChatCompletionResponse;
import org.springframework.stereotype.Service;

/**
 * LLM服务接口
 * 定义与大语言模型交互的通用接口
 */
@Service
public interface LLMService {

    /**
     * 发送聊天请求并获取响应
     * 
     * @param userQuery 用户查询
     * @return 聊天完成响应
     */
    ChatCompletionResponse getChatCompletion(String userQuery);

    /**
     * 获取LLM提供商名称
     * 
     * @return 提供商名称
     */
    String getProviderName();
}