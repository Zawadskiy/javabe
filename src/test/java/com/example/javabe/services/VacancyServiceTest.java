package com.example.javabe.services;

import com.example.javabe.domain.Vacancy;
import com.example.javabe.repository.VacancyRepoJpa;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VacancyServiceTest {

    @InjectMocks
    VacancyService vacancyService;
    @Mock
    VacancyRepoJpa vacancyRepo;

    @Test
    public void saveAll() {
        //given
        List<Vacancy> vacancies = new ArrayList<>();
        when(vacancyRepo.saveAll(Mockito.anyList())).thenReturn(vacancies);

        //when
        vacancyService.saveAll(vacancies);

        //then
        verify(vacancyRepo).saveAll(vacancies);
        verifyNoMoreInteractions(vacancyRepo);
    }

    @Test
    public void getAllPagable() {
        //given
        Page<Vacancy> vacancies = Mockito.mock(Page.class);
        Pageable pageRequest = Mockito.mock(Pageable.class);
        when(vacancyRepo.findAll(any(Pageable.class))).thenReturn(vacancies);

        //when
        vacancyService.getAll(pageRequest);

        //then
        verify(vacancyRepo).findAll(pageRequest);
        verifyNoMoreInteractions(vacancyRepo);
    }

    @Test
    public void testGetAll() {
        //given
        List<Vacancy> vacancies = new ArrayList<>();
        when(vacancyRepo.findAll()).thenReturn(vacancies);

        //when
        vacancyService.getAll();

        //then
        verify(vacancyRepo).findAll();
        verifyNoMoreInteractions(vacancyRepo);
    }
}