package com.lq.llm_functiondemo_lq.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 选择结果
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Choice {
    private ResponseMessage message;
    private String finish_reason;
}