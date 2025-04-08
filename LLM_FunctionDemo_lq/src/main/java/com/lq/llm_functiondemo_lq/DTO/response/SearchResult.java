package com.lq.llm_functiondemo_lq.DTO.response;

import java.util.List;
import java.util.Map;

/**
 * 搜索结果DTO
 */
public class SearchResult {
    private String query;
    private List<Map<String, String>> results;
    private int totalResults;
    private String searchTime;

    public SearchResult() {
    }

    public SearchResult(String query, List<Map<String, String>> results, int totalResults, String searchTime) {
        this.query = query;
        this.results = results;
        this.totalResults = totalResults;
        this.searchTime = searchTime;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Map<String, String>> getResults() {
        return results;
    }

    public void setResults(List<Map<String, String>> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public String getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(String searchTime) {
        this.searchTime = searchTime;
    }
}