package com.example.javabe.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MetaDto {

    @JsonProperty("current_page")
    private int currentPage;
    private int from;
    private String path;
    @JsonProperty("per_page")
    private String perPage;
    private int to;
    private String terms;
    private String info;

    public void setTo(int to) {
        this.to = to;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPerPage() {
        return perPage;
    }

    public void setPerPage(String perPage) {
        this.perPage = perPage;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
