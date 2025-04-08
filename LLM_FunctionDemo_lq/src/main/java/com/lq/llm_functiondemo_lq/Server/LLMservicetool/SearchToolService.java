package com.lq.llm_functiondemo_lq.Server.LLMservicetool;

import com.lq.llm_functiondemo_lq.DTO.response.SearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 搜索服务
 * 提供模拟的搜索功能
 */
@Service
public class SearchToolService {

    private static final Logger logger = LoggerFactory.getLogger(SearchToolService.class);

    /**
     * 执行搜索并返回模拟结果
     *
     * @param arguments JSON格式的参数字符串，包含查询关键词
     * @return 搜索结果
     */
    public SearchResult search(String arguments) {
        logger.info("执行搜索，参数: {}", arguments);

        // 解析参数中的查询关键词
        String query = extractQueryFromArguments(arguments);

        // 创建模拟的搜索结果
        return createMockSearchResults(query);
    }

    /**
     * 从JSON参数字符串中提取查询关键词
     */
    private String extractQueryFromArguments(String arguments) {
        // 简单解析，实际应用中应使用JSON解析库
        String query = "未知查询";

        if (arguments != null && arguments.contains("\"query\"")) {
            int startIndex = arguments.indexOf("\"query\"") + 8;
            int endIndex = arguments.indexOf("\"", startIndex + 2);
            if (startIndex > 0 && endIndex > startIndex) {
                query = arguments.substring(startIndex + 2, endIndex);
            }
        }

        return query;
    }

    /**
     * 创建模拟的搜索结果
     */
    private SearchResult createMockSearchResults(String query) {
        List<Map<String, String>> searchResults = new ArrayList<>();

        // 添加一些模拟的搜索结果
        Map<String, String> result1 = new HashMap<>();
        result1.put("title", query + " - 百科");
        result1.put("url", "https://example.com/wiki/" + query.replace(" ", "_"));
        result1.put("snippet", "这是关于" + query + "的百科信息，包含了详细的介绍和历史背景...");
        searchResults.add(result1);

        Map<String, String> result2 = new HashMap<>();
        result2.put("title", query + " 最新资讯");
        result2.put("url", "https://example.com/news/" + query.replace(" ", "-"));
        result2.put("snippet", "最新的" + query + "相关新闻和动态，包括最近的发展和趋势...");
        searchResults.add(result2);

        Map<String, String> result3 = new HashMap<>();
        result3.put("title", "如何学习" + query);
        result3.put("url", "https://example.com/learn/" + query.replace(" ", "+"));
        result3.put("snippet", "专业的" + query + "学习指南，从入门到精通的完整教程...");
        searchResults.add(result3);

        // 创建并返回SearchResult对象
        return new SearchResult(query, searchResults, searchResults.size(), "0.35秒");
    }
}