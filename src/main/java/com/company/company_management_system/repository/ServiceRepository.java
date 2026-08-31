package com.company.company_management_system.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.company.company_management_system.entity.CompanyService;


public interface ServiceRepository extends JpaRepository<CompanyService, Long> {
	
}