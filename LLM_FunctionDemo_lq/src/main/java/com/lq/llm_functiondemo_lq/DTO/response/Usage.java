package com.lq.llm_functiondemo_lq.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 使用统计
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usage {
    private Integer prompt_tokens;
    private Integer completion_tokens;
    private Integer total_tokens;
}