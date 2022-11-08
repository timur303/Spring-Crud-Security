package com.peaksoft.dao;

import com.peaksoft.entity.Company;

import java.util.List;

public interface CompanyDao {
    List<Company>getAllCompanies();
    void addCompany(Company company);
    Company getCompanyById(Long id);
    void updateCompany(Company company);
    void deleteCompany(Company company);

}