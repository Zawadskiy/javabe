package com.example.javabe.dto;

import java.util.Map;
import java.util.TreeMap;

public class StatisticDto {

    private Map<String, Long> data = new TreeMap<>();

    public Map<String, Long> getData() {
        return data;
    }

    public void setData(Map<String, Long> data) {
        this.data = data;
    }
}
