//package com.lq.llm_functiondemo_lq.tool.impl;
//
//import com.lq.llm_functiondemo_lq.tool.ToolDefinition;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//public class SearchownTool implements ToolDefinition
//{
//    @Override
//    public String getName() {
//        return "搜索功能";
//    }
//
//    @Override
//    public String getDescription() {
//        return "这是来查询天气状态的";
//    }
//
//    @Override
//    public Map<String, Object> getParametersDefinition() {
//        Map<String,Object> map = new HashMap<>();
//        map.put("type","object");
//
//        Map<String,Object> body = new HashMap<>();
//        body.put("type", "string");
//        body.put("description","搜索引擎");
//
//        Map<String,Object> propries = new HashMap<>();
//        propries.put("query",map);
//        propries.put("propries",body);
//
//        return propries;
//    }
//
//    @Override
//    public Object execute(String arguments) {
//        return null;
//    }
//}
