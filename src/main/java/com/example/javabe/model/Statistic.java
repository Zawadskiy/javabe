package com.example.javabe.model;

import java.util.Map;
import java.util.TreeMap;

public class Statistic {

    private Map<String, Long> data = new TreeMap<>();

    public Map<String, Long> getData() {
        return data;
    }

    public void setData(Map<String, Long> data) {
        this.data = data;
    }
}
