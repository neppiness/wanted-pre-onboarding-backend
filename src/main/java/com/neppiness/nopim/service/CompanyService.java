package com.neppiness.nopim.service;

import com.neppiness.nopim.domain.Company;
import com.neppiness.nopim.dto.CompanyDetailResponse;
import com.neppiness.nopim.dto.CompanyRequest;
import com.neppiness.nopim.dto.CompanyResponse;
import com.neppiness.nopim.exception.ResourceNotFoundException;
import com.neppiness.nopim.repository.CompanyRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Transactional
    public CompanyResponse create(CompanyRequest companyRequest) {
        Company createdCompany = Company.builder()
                .name(companyRequest.getName())
                .country(companyRequest.getCountry())
                .region(companyRequest.getRegion())
                .build();
        return companyRepository
                .save(createdCompany)
                .convertToResponse();
    }

    public List<CompanyResponse> search(CompanyRequest companyRequest) {
        String name = companyRequest.getName();
        String region = companyRequest.getRegion();
        String country = companyRequest.getCountry();
        return companyRepository.findByParameters(name, region, country);
    }

    public CompanyDetailResponse getDetail(Long companyId) {
        Optional<Company> mayBeFoundCompany = companyRepository.findById(companyId);
        if (mayBeFoundCompany.isEmpty()) {
            throw new ResourceNotFoundException(ResourceNotFoundException.COMPANY_NOT_FOUND);
        }
        return mayBeFoundCompany.get()
                .covertToDetailResponse();
    }

}
