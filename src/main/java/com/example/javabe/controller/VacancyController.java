package com.example.javabe.controller;

import com.example.javabe.converter.StatisticConverter;
import com.example.javabe.domain.Vacancy;
import com.example.javabe.dto.StatisticDto;
import com.example.javabe.services.StatisticService;
import com.example.javabe.services.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VacancyController {

    private final VacancyService vacancyService;
    private final StatisticService statisticService;

    private final StatisticConverter statisticConverter;

    @Autowired
    public VacancyController(VacancyService vacancyService, StatisticService statisticService,
                             StatisticConverter statisticConverter) {
        this.vacancyService = vacancyService;
        this.statisticService = statisticService;
        this.statisticConverter = statisticConverter;
    }

    @GetMapping("/vacancies")
    public ResponseEntity<Page<Vacancy>> getAllVacancies(@PageableDefault Pageable pageRequest) {
        return new ResponseEntity<>(vacancyService.getAll(pageRequest), HttpStatus.OK);
    }

    @GetMapping("/vacancies/statistics")
    public ResponseEntity<StatisticDto> getStatistic() {
        StatisticDto response = statisticConverter.convert(statisticService.getStatistic());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}