package com.example.javabe.repository;

import com.example.javabe.domain.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyRepoJpa extends JpaRepository<Vacancy, Long> {
}
