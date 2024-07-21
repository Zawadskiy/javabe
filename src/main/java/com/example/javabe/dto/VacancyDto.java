package com.example.javabe.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

public class VacancyDto {

    @NotBlank
    private String slug;
    @NotBlank
    @JsonProperty("company_name")
    private String companyName;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    private boolean remote;
    @NotBlank
    private String url;
    @NotBlank
    private List<String> tags;
    @NotBlank
    @JsonProperty("job_types")
    private List<String> jobTypes;
    @NotBlank
    private String location;
    @NotBlank
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    public @NotBlank String getSlug() {
        return slug;
    }

    public @NotBlank String getCompanyName() {
        return companyName;
    }

    public @NotBlank String getTitle() {
        return title;
    }

    public @NotBlank String getDescription() {
        return description;
    }

    public boolean isRemote() {
        return remote;
    }

    public @NotBlank String getUrl() {
        return url;
    }

    public @NotBlank List<String> getTags() {
        return tags;
    }

    public @NotBlank List<String> getJobTypes() {
        return jobTypes;
    }

    public @NotBlank String getLocation() {
        return location;
    }

    public @NotBlank LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setSlug(@NotBlank String slug) {
        this.slug = slug;
    }

    public void setCompanyName(@NotBlank String companyName) {
        this.companyName = companyName;
    }

    public void setTitle(@NotBlank String title) {
        this.title = title;
    }

    public void setDescription(@NotBlank String description) {
        this.description = description;
    }

    public void setRemote(boolean remote) {
        this.remote = remote;
    }

    public void setUrl(@NotBlank String url) {
        this.url = url;
    }

    public void setTags(@NotBlank List<String> tags) {
        this.tags = tags;
    }

    public void setJobTypes(@NotBlank List<String> jobTypes) {
        this.jobTypes = jobTypes;
    }

    public void setLocation(@NotBlank String location) {
        this.location = location;
    }

    public void setCreatedAt(@NotBlank LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
