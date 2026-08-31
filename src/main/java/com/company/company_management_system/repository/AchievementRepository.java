package com.company.company_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.company_management_system.entity.Achievement;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
	
}