package com.lq.llm_functiondemo_lq.Server.LLMServiceimpl;


import com.lq.llm_functiondemo_lq.DTO.request.ChatCompletionRequest;
import com.lq.llm_functiondemo_lq.DTO.request.Message;
import com.lq.llm_functiondemo_lq.DTO.request.ResponseFormat;
import com.lq.llm_functiondemo_lq.DTO.response.ChatCompletionResponse;
import com.lq.llm_functiondemo_lq.Server.LLMService;
import com.lq.llm_functiondemo_lq.tool.ToolRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class llmserverimplt implements LLMService {

    @Value("${llm.api.url}")
    private String apiUrl;

    @Value("${llm.api.token}")
    private String apiToken;

    @Value("${llm.api.model:Qwen/Qwen2.5-72B-Instruct-128K}")
    private String model;

    // 发送协议
     final RestTemplate restTemplate;

     final ToolRegistry toolRegistry;


     llmserverimplt(RestTemplate restTemplate, ToolRegistry toolRegistry){
         this.restTemplate=restTemplate;
         this.toolRegistry=toolRegistry;

     }

    // 包装   llm 的请求体  进行请求

    @Override
    public ChatCompletionResponse getChatCompletion(String userQuery) {

        // 创建请求头
        org.springframework.http.HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiToken);

        Message useMessage = new Message();
        useMessage.setContent(userQuery);
        useMessage.setRole("user");


        ChatCompletionRequest requestBody = ChatCompletionRequest.builder()
                .model(model)
                .messages(Collections.singletonList(useMessage))
                .stream(false)
                .max_tokens(512)
                .stop(null)
                .temperature(0.7)
                .top_p(0.7)
                .top_k(50)
                .frequency_penalty(0.5)
                .n(1)
                .response_format(new ResponseFormat("text"))
                .tools(toolRegistry.getToolsForLLM()) // 使用工具注册表获取所有工具
                .build();

        HttpEntity<ChatCompletionRequest> entity = new HttpEntity<>(requestBody,headers);
        ChatCompletionResponse respond = restTemplate.postForObject(apiUrl,entity,ChatCompletionResponse.class);


        return respond;
    }

    @Override
    public String getProviderName() {
        return "yanwenuyixin";
    }
}
