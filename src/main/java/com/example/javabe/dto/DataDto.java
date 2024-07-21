package com.example.javabe.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class DataDto {

    @NotBlank
    private List<VacancyDto> data;

    @NotBlank
    private LinksDto links;

    @NotBlank
    private MetaDto meta;

    public @NotBlank List<VacancyDto> getData() {
        return data;
    }

    public void setData(@NotBlank List<VacancyDto> data) {
        this.data = data;
    }

    public @NotBlank LinksDto getLinks() {
        return links;
    }

    public void setLinks(@NotBlank LinksDto links) {
        this.links = links;
    }

    public @NotBlank MetaDto getMeta() {
        return meta;
    }

    public void setMeta(@NotBlank MetaDto meta) {
        this.meta = meta;
    }
}
