package com.example.javabe.services;

import com.example.javabe.domain.Vacancy;
import com.example.javabe.model.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class StatisticService {

    private final VacancyService vacancyService;

    @Autowired
    public StatisticService(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    public Statistic getStatistic() {

        Statistic statistic = new Statistic();

        List<Vacancy> all = vacancyService.getAll();

        TreeMap<String, Long> collect = all.stream()
                .collect(Collectors.groupingBy(Vacancy::getLocation, TreeMap::new, Collectors.counting()));

        statistic.setData(collect);

        return statistic;
    }
}
