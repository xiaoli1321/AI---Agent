package com.lq.llm_functiondemo_lq.DTO.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 聊天完成响应
 */
@Setter
@Getter
public class ChatCompletionResponse {
    private String id;
    private List<Choice> choices;
    private List<ToolCall> tool_calls;
    private Usage usage;
    private Long created;
    private String model;
    private String object;

}