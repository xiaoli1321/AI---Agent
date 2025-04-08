package com.lq.llm_functiondemo_lq.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 天气数据
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherData {
    private String city;
    private String date;
    private String condition;
    private String temperature;
    private String humidity;
    private String windSpeed;
}