package com.neppiness.nopim.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neppiness.nopim.dto.CompanyDetailResponse;
import com.neppiness.nopim.dto.CompanyResponse;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Company {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    private String region;

    private String country;

    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Job> jobs;

    @Builder
    public Company(final Long id, final String name, final String country, final String region, final List<Job> jobs) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.region = region;
        this.jobs = initializeJobs(jobs);
    }

    private List<Job> initializeJobs(List<Job> jobs) {
        if (jobs == null) {
            return new ArrayList<>();
        }
        return jobs;
    }

    public CompanyResponse convertToResponse() {
        return CompanyResponse.builder()
                .id(this.id)
                .name(this.name)
                .region(this.region)
                .country(this.country)
                .build();
    }

    public CompanyDetailResponse covertToDetailResponse() {
        List<Long> jobIds = this.jobs.stream()
                .map(Job::getId)
                .toList();
        return CompanyDetailResponse.builder()
                .id(this.id)
                .name(this.name)
                .region(this.region)
                .country(this.country)
                .jobs(jobIds)
                .build();
    }

}
