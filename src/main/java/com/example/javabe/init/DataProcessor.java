package com.example.javabe.init;

import com.example.javabe.converter.DataConverter;
import com.example.javabe.domain.Vacancy;
import com.example.javabe.dto.DataDto;
import com.example.javabe.dto.VacancyDto;
import com.example.javabe.services.VacancyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class DataProcessor {

    private final Client client;
    private final VacancyService vacancyService;
    private final DataConverter dataConverter;
    private final ObjectMapper objectMapper;
    private LocalDateTime latestUpdate = LocalDateTime.now().minusMonths(1);

    private static final int INIT_PAGES_COUNT = 5;

    @Autowired
    public DataProcessor(Client client, VacancyService vacancyService, DataConverter dataConverter, ObjectMapper objectMapper) {
        this.client = client;
        this.vacancyService = vacancyService;
        this.dataConverter = dataConverter;
        this.objectMapper = objectMapper;
    }

    @Scheduled(fixedRate = 30, timeUnit = TimeUnit.SECONDS)
    public void fetchNewVacancies() {

        List<Vacancy> vacancies = new ArrayList<>();
        DataDto dataDto = read(1);

        dataDto.getData().stream()
                .filter(data -> latestUpdate.isBefore(data.getCreatedAt()))
                .peek(data -> vacancies.add(dataConverter.convert(data)))
                .map(VacancyDto::getCreatedAt)
                .max(LocalDateTime::compareTo)
                .ifPresent(max -> {
                    if (max.isAfter(latestUpdate)) {
                        latestUpdate = max;
                    }
                });

        if (!CollectionUtils.isEmpty(vacancies)) {
            vacancyService.saveAll(vacancies);
        }
    }

    @Scheduled(fixedDelay = Long.MAX_VALUE, timeUnit = TimeUnit.NANOSECONDS) // Dead man's solution
    private void init() {

        List<Vacancy> vacancies = new ArrayList<>();

        List<DataDto> init = new ArrayList<>();

        for (int i = INIT_PAGES_COUNT; i > 1; i--) {
            init.add(read(i));
        }

        init.stream()
                .flatMap(dataDto -> dataDto.getData().stream())
                .forEach(data -> {
                    if (data.getCreatedAt().isAfter(latestUpdate)) {
                        latestUpdate = data.getCreatedAt();
                    }
                    vacancies.add(dataConverter.convert(data));
                });

        if (!CollectionUtils.isEmpty(vacancies)) {
            vacancyService.saveAll(vacancies);
        }
    }

    private DataDto read(int page) {
        try {
            return objectMapper.readValue(client.fetchData(page).body(), DataDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
