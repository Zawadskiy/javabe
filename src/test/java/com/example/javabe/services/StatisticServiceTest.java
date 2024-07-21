package com.example.javabe.services;

import com.example.javabe.domain.Vacancy;
import com.example.javabe.model.Statistic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StatisticServiceTest {

    @InjectMocks
    StatisticService statisticService;

    @Mock
    VacancyService vacancyService;

    @Test
    public void getStatistic() {

        //given
        List<Vacancy> all = new ArrayList<>();
        Vacancy v1 = new Vacancy();
        Vacancy v2 = new Vacancy();
        Vacancy v3 = new Vacancy();
        Vacancy v4 = new Vacancy();
        v1.setLocation("location1");
        v2.setLocation("location2");
        v3.setLocation("location3");
        v4.setLocation("location1");
        all.add(v1);
        all.add(v2);
        all.add(v3);
        all.add(v4);

        when(vacancyService.getAll()).thenReturn(all);

        //when
        Statistic statistic = statisticService.getStatistic();

        //then
        assertNotNull(statistic);
        assertEquals(3, statistic.getData().size());
        assertEquals(2, statistic.getData().get("location1"));
        assertEquals(1, statistic.getData().get("location2"));

        verify(vacancyService).getAll();
        verifyNoMoreInteractions(vacancyService);
    }
}