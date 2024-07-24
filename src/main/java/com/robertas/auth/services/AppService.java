package com.robertas.auth.services;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;
import com.robertas.auth.models.Application;

import jakarta.annotation.PostConstruct;

@Service
public class AppService {

    private List<Application> applications;

    @PostConstruct
    public void loadAppInDB() {
        Faker faker = new Faker();
        applications = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Application.builder()
                        .id(i)
                        .name(faker.app().name())
                        .author(faker.app().name())
                        .version(faker.app().version())
                        .build())
                .toList();
    }

    public List<Application> allApplications() {
        return applications;
    }

    public Application getApplicationById(Long id) {
        return applications.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
