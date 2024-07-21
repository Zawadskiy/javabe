package com.example.javabe.converter;

import com.example.javabe.dto.StatisticDto;
import com.example.javabe.model.Statistic;
import org.springframework.stereotype.Component;

@Component
public class StatisticConverter {

    public Statistic convert(StatisticDto statisticDto) {
        Statistic statistic = new Statistic();
        statistic.setData(statisticDto.getData());

        return statistic;
    }

    public StatisticDto convert(Statistic statistic) {
        StatisticDto statisticDto = new StatisticDto();
        statisticDto.setData(statistic.getData());

        return statisticDto;
    }
}
