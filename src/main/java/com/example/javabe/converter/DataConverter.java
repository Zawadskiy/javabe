package com.example.javabe.converter;

import com.example.javabe.domain.Vacancy;
import com.example.javabe.dto.VacancyDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataConverter {

    public Vacancy convert(VacancyDto vacancyDto) {

        Vacancy vacancy = new Vacancy();

        vacancy.setJobTypes(vacancyDto.getJobTypes());
        vacancy.setCompanyName(vacancyDto.getCompanyName());

        vacancy.setCreatedAt(vacancyDto.getCreatedAt());
        vacancy.setDescription(vacancyDto.getDescription());

        vacancy.setLocation(vacancyDto.getLocation());
        vacancy.setRemote(vacancyDto.isRemote());

        vacancy.setTags(vacancyDto.getTags());
        vacancy.setUrl(vacancyDto.getUrl());

        vacancy.setSlug(vacancyDto.getSlug());
        vacancy.setTitle(vacancyDto.getTitle());

        return vacancy;
    }

    public VacancyDto convert(Vacancy vacancy) {

        VacancyDto vacancyDto = new VacancyDto();

        vacancyDto.setJobTypes(vacancy.getJobTypes());
        vacancyDto.setCompanyName(vacancy.getCompanyName());

        vacancyDto.setCreatedAt(vacancy.getCreatedAt());
        vacancyDto.setDescription(vacancy.getDescription());

        vacancyDto.setLocation(vacancy.getLocation());
        vacancyDto.setRemote(vacancy.isRemote());

        vacancyDto.setTags(vacancy.getTags());
        vacancyDto.setUrl(vacancy.getUrl());

        vacancyDto.setSlug(vacancy.getSlug());
        vacancyDto.setTitle(vacancy.getTitle());

        return vacancyDto;
    }


}
