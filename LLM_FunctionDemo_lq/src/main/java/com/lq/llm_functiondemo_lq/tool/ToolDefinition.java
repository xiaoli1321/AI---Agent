package com.lq.llm_functiondemo_lq.tool;

import java.util.Map;

/**
 * 工具定义接口
 * 所有工具都需要实现这个接口，以便自动注册到工具注册表中
 */
public interface ToolDefinition {

    /**
     * 获取工具名称
     * 
     * @return 工具名称
     */
    String getName();

    /**
     * 获取工具描述
     * 
     * @return 工具描述
     */
    String getDescription();

    /**
     * 获取工具参数定义
     * 
     * @return 参数定义Map
     */
    Map<String, Object> getParametersDefinition();

    /**
     * 执行工具
     * 
     * @param arguments JSON格式的参数字符串
     * @return 执行结果
     */
    Object execute(String arguments);
}