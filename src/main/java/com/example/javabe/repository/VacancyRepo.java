package com.example.javabe.repository;

import com.example.javabe.domain.Vacancy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class VacancyRepo {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public VacancyRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void saveAll(List<Vacancy> vacancies) {
        jdbcTemplate.batchUpdate("INSERT INTO JOB (slug, company_name, title, description," +
                        " remote, url, tags, job_types ,location, created_at)" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                vacancies,
                50,
                (PreparedStatement ps, Vacancy vacancy) -> {
                    ps.setString(1, vacancy.getSlug());
                    ps.setString(2, vacancy.getCompanyName());
                    ps.setString(3, vacancy.getTitle());
                    ps.setString(4, vacancy.getDescription());
                    ps.setBoolean(5, vacancy.isRemote());
                    ps.setString(6, vacancy.getUrl());
                    ps.setObject(7, vacancy.getTags());
                    ps.setObject(8, vacancy.getJobTypes());
                    ps.setString(9, vacancy.getLocation());
                    ps.setTimestamp(10, Timestamp.valueOf(vacancy.getCreatedAt()));
                });
    }
}
