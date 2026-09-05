package com.company.company_management_system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.company_management_system.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findByIsDeletedFalse();

    Optional<Group> findByIdAndIsDeletedFalse(Long id);

    boolean existsByNameIgnoreCaseAndIsDeletedFalse(String name);

    boolean existsByNameIgnoreCaseAndIsDeletedFalseAndIdNot(
            String name,
            Long id
    );
}