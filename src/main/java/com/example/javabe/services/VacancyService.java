package com.example.javabe.services;

import com.example.javabe.domain.Vacancy;
import com.example.javabe.repository.VacancyRepoJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacancyService {

    private final VacancyRepoJpa vacancyRepo;

    @Autowired
    public VacancyService(VacancyRepoJpa vacancyRepo) {
        this.vacancyRepo = vacancyRepo;
    }

    public void saveAll(List<Vacancy> vacancies) {
        vacancyRepo.saveAll(vacancies);
    }

    public Page<Vacancy> getAll(Pageable pageRequest) {
        return vacancyRepo.findAll(pageRequest);
    }

    public List<Vacancy> getAll() {
        return vacancyRepo.findAll();
    }
}
