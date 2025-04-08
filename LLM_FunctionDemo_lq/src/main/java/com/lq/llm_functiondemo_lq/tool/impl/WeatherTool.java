//package com.lq.llm_functiondemo_lq.tool.impl;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.xhy.function_calling.service.tool.WeatherToolService;
//import org.xhy.function_calling.tool.ToolDefinition;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * 天气查询工具
// */
//@Component
//public class WeatherTool implements ToolDefinition {
//
//    private static final Logger logger = LoggerFactory.getLogger(WeatherTool.class);
//
//    private final WeatherToolService weatherService;
//
//    public WeatherTool(WeatherToolService weatherService) {
//        this.weatherService = weatherService;
//    }
//
//    @Override
//    public String getName() {
//        return "get_weather";
//    }
//
//    @Override
//    public String getDescription() {
//        return "获取指定城市和日期的天气信息";
//    }
//
//    @Override
//    public Map<String, Object> getParametersDefinition() {
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("type", "object");
//        parameters.put("required", List.of("city"));
//
//        Map<String, Object> properties = new HashMap<>();
//
//        Map<String, Object> cityProperty = new HashMap<>();
//        cityProperty.put("type", "string");
//        cityProperty.put("description", "城市名称，如北京、上海等");
//
//        Map<String, Object> dateProperty = new HashMap<>();
//        dateProperty.put("type", "string");
//        dateProperty.put("description", "查询日期，格式为YYYY-MM-DD，默认为今天");
//
//        properties.put("city", cityProperty);
//        properties.put("date", dateProperty);
//
//        parameters.put("properties", properties);
//
//        return parameters;
//    }
//
//    @Override
//    public Object execute(String arguments) {
//        logger.info("执行天气查询，参数: {}", arguments);
//        return weatherService.getWeatherData(arguments);
//    }
//}