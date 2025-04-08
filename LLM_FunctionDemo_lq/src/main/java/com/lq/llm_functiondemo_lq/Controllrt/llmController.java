package com.lq.llm_functiondemo_lq.Controllrt;


import com.lq.llm_functiondemo_lq.DTO.response.ChatCompletionResponse;
import com.lq.llm_functiondemo_lq.Server.LLMService;
import com.lq.llm_functiondemo_lq.tool.ToolCallProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class llmController {

    private static final Logger logger = LoggerFactory.getLogger(llmController.class);

    @Autowired
    private LLMService llmserver;

    @Autowired
    private  ToolCallProcessor toolCallProcessor;


    @PostMapping("/query")
    public ResponseEntity<Map<String, Object>> chat(@RequestParam String query){

        if (query == null || query.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "查询内容不能为空"));
        }

        logger.info("收到聊天请求: {}", query);

        // 调用LLM服务
        ChatCompletionResponse response = llmserver.getChatCompletion(query);

        // 处理响应和工具调用
        Map<String, Object> result = toolCallProcessor.processResponse(response, query);

        // 添加LLM提供商信息
        result.put("provider", llmserver.getProviderName());

        return ResponseEntity.ok(result);
    }


}
