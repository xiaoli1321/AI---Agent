package com.lq.llm_functiondemo_lq.tool;

import com.lq.llm_functiondemo_lq.DTO.response.ChatCompletionResponse;
import com.lq.llm_functiondemo_lq.DTO.response.Choice;
import com.lq.llm_functiondemo_lq.DTO.response.ToolCall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工具调用处理器
 * 负责处理LLM的工具调用响应
 */
@Service
public class ToolCallProcessor {

    private static final Logger logger = LoggerFactory.getLogger(ToolCallProcessor.class);

    private final ToolRegistry toolRegistry;

    public ToolCallProcessor(ToolRegistry toolRegistry) {
        this.toolRegistry = toolRegistry;
    }

    /**
     * 处理API响应，提取工具调用信息并执行相应操作
     *
     * @param response API响应
     * @param query    原始查询
     * @return 处理结果
     */
    public Map<String, Object> processResponse(ChatCompletionResponse response, String query) {
        Map<String, Object> result = new HashMap<>();

        // 添加AI回复
        if (response.getChoices() != null && !response.getChoices().isEmpty()) {
            Choice firstChoice = response.getChoices().get(0);

            Choice choice = response.getChoices().get(0);
            // 判断是否存在工具

            // 添加AI回复内容（如果有）
            if (firstChoice.getMessage() != null && firstChoice.getMessage().getContent() != null
                    && !firstChoice.getMessage().getContent().isEmpty()) {
                result.put("aiResponse", firstChoice.getMessage().getContent());
            }

            // 检查Choice中的finish_reason是否为tool_calls
            if ("tool_calls".equals(firstChoice.getFinish_reason())) {
                logger.info("检测到工具调用请求 (finish_reason=tool_calls)");

                // 从Choice中获取工具调用信息
                if (firstChoice.getMessage() != null && firstChoice.getMessage().getTool_calls() != null
                        && !firstChoice.getMessage().getTool_calls().isEmpty()) {
                    logger.info("从Choice.message中找到工具调用信息");
                    processToolCalls(firstChoice.getMessage().getTool_calls(), result);
                } else {
                    logger.warn("finish_reason为tool_calls，但未找到工具调用信息");
                }
            }
        }

        // 兼容处理：检查response中的标准tool_calls字段（以防API格式变化）
        if (response.getTool_calls() != null && !response.getTool_calls().isEmpty()) {
            logger.info("从response中找到标准工具调用信息");
            processToolCalls(response.getTool_calls(), result);
        }

        return result;
    }

    /**
     * 处理工具调用
     */
    private void processToolCalls(List<ToolCall> toolCalls, Map<String, Object> result) {
        for (ToolCall toolCall : toolCalls) {
            if ("function".equals(toolCall.getType())) {
                String functionName = toolCall.getFunction().getName();
                String arguments = toolCall.getFunction().getArguments();

                logger.info("执行工具: {}, 参数: {}", functionName, arguments);
                try {
                    Object toolResult = toolRegistry.executeTool(functionName, arguments);
                    if (toolResult != null) {
                        // 使用工具名称作为结果的键
                        String resultKey = functionName.replace("get_", "") + "Data";
                        // 对于搜索工具，使用searchData作为键
                        if ("search".equals(functionName)) {
                            resultKey = "searchData";
                        }
                        result.put(resultKey, toolResult);
                        logger.info("工具 {} 执行成功", functionName);
                    } else {
                        logger.warn("工具 {} 执行结果为空", functionName);
                    }
                } catch (Exception e) {
                    logger.error("执行工具 {} 时出错: {}", functionName, e.getMessage(), e);
                    result.put("error", "执行工具 " + functionName + " 时出错: " + e.getMessage());
                }
            }
        }
    }
}