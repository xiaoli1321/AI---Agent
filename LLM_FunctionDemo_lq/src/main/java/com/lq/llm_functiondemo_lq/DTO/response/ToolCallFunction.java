package com.lq.llm_functiondemo_lq.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 工具调用函数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToolCallFunction {
    private String name;
    private String arguments;
}