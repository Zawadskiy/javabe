package com.example.javabe.init;

import com.example.javabe.converter.DataConverter;
import com.example.javabe.dto.DataDto;
import com.example.javabe.dto.VacancyDto;
import com.example.javabe.services.VacancyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DataProcessorTest {

    @InjectMocks
    DataProcessor dataProcessor;

    @Mock
    Client client;
    @Mock
    VacancyService vacancyService;
    @Mock
    DataConverter dataConverter;
    @Mock
    ObjectMapper objectMapper;

    @Test
    public void fetchNewVacanciesOneNew() throws IOException {
        //given
        List<VacancyDto> vacancyDtoList = new ArrayList<>();
        VacancyDto vacancyDto = new VacancyDto();
        vacancyDto.setCreatedAt(LocalDateTime.now().minusMonths(2));
        VacancyDto vacancyDto1 = new VacancyDto();
        vacancyDto1.setCreatedAt(LocalDateTime.now().plusSeconds(5));

        vacancyDtoList.add(vacancyDto);
        vacancyDtoList.add(vacancyDto1);

        HttpResponse<String> response = Mockito.mock(HttpResponse.class);
        DataDto dataDto = Mockito.mock(DataDto.class);

        when(client.fetchData(anyInt())).thenReturn(response);
        when(response.body()).thenReturn("Mock response");
        when(objectMapper.readValue(anyString(), eq(DataDto.class))).thenReturn(dataDto);
        when(dataDto.getData()).thenReturn(vacancyDtoList);
        //when

        dataProcessor.fetchNewVacancies();

        //then
        verify(client).fetchData(1);
        verify(objectMapper).readValue(response.body(), DataDto.class);
        verify(dataConverter, Mockito.times(1)).convert(vacancyDto1);
        verify(vacancyService, Mockito.times(1)).saveAll(anyList());

    }


    @Test
    public void fetchNewVacanciesNoNew() throws IOException {
        //given
        List<VacancyDto> vacancyDtoList = new ArrayList<>();
        VacancyDto vacancyDto = new VacancyDto();
        vacancyDto.setCreatedAt(LocalDateTime.now().minusMonths(2));
        VacancyDto vacancyDto1 = new VacancyDto();
        vacancyDto1.setCreatedAt(LocalDateTime.now().minusMonths(1).minusSeconds(1));

        vacancyDtoList.add(vacancyDto);
        vacancyDtoList.add(vacancyDto1);

        HttpResponse<String> response = Mockito.mock(HttpResponse.class);
        DataDto dataDto = Mockito.mock(DataDto.class);

        when(client.fetchData(anyInt())).thenReturn(response);
        when(response.body()).thenReturn("Mock response");
        when(objectMapper.readValue(anyString(), eq(DataDto.class))).thenReturn(dataDto);
        when(dataDto.getData()).thenReturn(vacancyDtoList);
        //when

        dataProcessor.fetchNewVacancies();

        //then
        verify(client).fetchData(1);
        verify(objectMapper).readValue(response.body(), DataDto.class);
        verify(dataConverter, Mockito.times(0)).convert(vacancyDto1);
        verify(vacancyService, Mockito.times(0)).saveAll(anyList());
    }
}