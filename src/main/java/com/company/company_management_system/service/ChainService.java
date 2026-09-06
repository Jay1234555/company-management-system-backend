package com.company.company_management_system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.company_management_system.entity.Chain;
import com.company.company_management_system.entity.Group;
import com.company.company_management_system.repository.ChainRepository;
import com.company.company_management_system.repository.GroupRepository;

@Service
public class ChainService {

    private final ChainRepository chainRepository;
    private final GroupRepository groupRepository;

    public ChainService(ChainRepository chainRepository,
                        GroupRepository groupRepository) {

        this.chainRepository = chainRepository;
        this.groupRepository = groupRepository;
    }

    // Get All Active Chains
    public List<Chain> getAllChains() {

        return chainRepository.findByIsActiveTrue();
    }

    // Get Chain By ID
    public Chain getChainById(Long id) {

        return chainRepository.findByChainIdAndIsActiveTrue(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Chain not found with ID: " + id
                        )
                );
    }

    // Create Chain
    public Chain addChain(Chain chain) {

        // Validate Company Name
        if (chain.getCompanyName() == null ||
                chain.getCompanyName().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Company name is required"
            );
        }

        // Validate GSTN
        if (chain.getGstnNo() == null ||
                chain.getGstnNo().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "GSTN number is required"
            );
        }

        // Validate GSTN length
        if (chain.getGstnNo().trim().length() != 15) {

            throw new IllegalArgumentException(
                    "GSTN number must be 15 characters"
            );
        }

        // Validate Group
        if (chain.getGroup() == null ||
                chain.getGroup().getId() == null) {

            throw new IllegalArgumentException(
                    "Group is required"
            );
        }

        // Check duplicate GSTN
        if (chainRepository.existsByGstnNo(
                chain.getGstnNo().trim())) {

            throw new IllegalArgumentException(
                    "GSTN number already exists"
            );
        }

        // Get existing active Group
        Long groupId = chain.getGroup().getId();

        Group group = groupRepository
                .findByIdAndIsDeletedFalse(groupId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Active group not found"
                        )
                );

        // Set actual Group
        chain.setGroup(group);

        // Set active
        chain.setActive(true);

        return chainRepository.save(chain);
    }

    // Update Chain
    public Chain updateChain(Long id, Chain updatedChain) {

        // Find existing active Chain
        Chain existingChain = getChainById(id);

        // Validate Company Name
        if (updatedChain.getCompanyName() == null ||
                updatedChain.getCompanyName().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Company name is required"
            );
        }

        // Validate GSTN
        if (updatedChain.getGstnNo() == null ||
                updatedChain.getGstnNo().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "GSTN number is required"
            );
        }

        // Validate GSTN length
        if (updatedChain.getGstnNo().trim().length() != 15) {

            throw new IllegalArgumentException(
                    "GSTN number must be 15 characters"
            );
        }

        // Validate Group
        if (updatedChain.getGroup() == null ||
                updatedChain.getGroup().getId() == null) {

            throw new IllegalArgumentException(
                    "Group is required"
            );
        }

        String newGstn =
                updatedChain.getGstnNo().trim();

        // Check duplicate GSTN only when GSTN changed
        if (!existingChain.getGstnNo()
                .equalsIgnoreCase(newGstn)
                && chainRepository.existsByGstnNo(newGstn)) {

            throw new IllegalArgumentException(
                    "GSTN number already exists"
            );
        }

        // Get active Group
        Long groupId =
                updatedChain.getGroup().getId();

        Group group = groupRepository
                .findByIdAndIsDeletedFalse(groupId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Active group not found"
                        )
                );

        // Update Chain
        existingChain.setCompanyName(
                updatedChain.getCompanyName().trim()
        );

        existingChain.setGstnNo(newGstn);

        existingChain.setGroup(group);

        return chainRepository.save(existingChain);
    }

    // Soft Delete Chain
    public String deleteChain(Long id) {

        Chain chain = getChainById(id);

        /*
         * Brand relationship check will be added
         * when Brand Management module is created.
         */

        chain.setActive(false);

        chainRepository.save(chain);

        return "Chain deleted successfully";
    }

    // Filter Chains By Group
    public List<Chain> getChainsByGroup(Long groupId) {

        // Only active Group can be selected
        Group group = groupRepository
                .findByIdAndIsDeletedFalse(groupId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Active group not found"
                        )
                );

        return chainRepository
                .findByGroupAndIsActiveTrue(group);
    }
}