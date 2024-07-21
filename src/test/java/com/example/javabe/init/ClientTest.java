package com.example.javabe.init;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientTest {

    private static final String APP_URI = "https://www.arbeitnow.com/api/job-board-api";

    @Spy
    Client client;
    @Mock
    HttpClient httpClient;

    @Test
    public void dataFetcher() throws IOException, InterruptedException {

        //given
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("%s?page=%d".formatted(APP_URI, 1)))
                .GET()
                .timeout(Duration.of(5000, ChronoUnit.MILLIS))
                .build();
        HttpResponse<Object> response = Mockito.mock(HttpResponse.class);
        when(httpClient.send(Mockito.any(), Mockito.any())).thenReturn(response);

        //when
        HttpResponse<String> stringHttpResponse = client.fetchData(1);

        //then
        assertSame(response, stringHttpResponse);

        verify(httpClient).send(request, HttpResponse.BodyHandlers.ofString());
        verifyNoMoreInteractions(httpClient);
    }
}