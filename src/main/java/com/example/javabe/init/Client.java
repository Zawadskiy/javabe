package com.example.javabe.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Component
public class Client {

    private static final String APP_URI = "https://www.arbeitnow.com/api/job-board-api";
    private final HttpClient httpClient;


    @Autowired
    public Client(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public HttpResponse<String> fetchData(int page) {

        HttpResponse<String> response;
        HttpRequest request = getHttpRequest(page);

        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception.getMessage());
        }

        return response;
    }

    private HttpRequest getHttpRequest(int page) {
        return HttpRequest.newBuilder()
                .uri(URI.create("%s?page=%d".formatted(Client.APP_URI, page)))
                .GET()
                .timeout(Duration.of(5000, ChronoUnit.MILLIS))
                .build();
    }
}
