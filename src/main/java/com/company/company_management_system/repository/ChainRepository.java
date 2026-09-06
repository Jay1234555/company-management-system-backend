package com.company.company_management_system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.company_management_system.entity.Chain;
import com.company.company_management_system.entity.Group;

public interface ChainRepository extends JpaRepository<Chain, Long> {

    boolean existsByGstnNo(String gstnNo);

    Optional<Chain> findByGstnNo(String gstnNo);

    List<Chain> findByIsActiveTrue();

    List<Chain> findByGroupAndIsActiveTrue(Group group);

    Optional<Chain> findByChainIdAndIsActiveTrue(Long chainId);
}