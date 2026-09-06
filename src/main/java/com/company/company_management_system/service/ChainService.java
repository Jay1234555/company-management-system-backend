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

    // Get all active chains
    public List<Chain> getAllChains() {
        return chainRepository.findByIsActiveTrue();
    }

    // Get chain by ID
    public Chain getChainById(Long id) {

        return chainRepository.findByChainIdAndIsActiveTrue(id)
                .orElseThrow(() ->
                        new RuntimeException("Chain not found with ID: " + id));
    }

    // Add Chain
    public Chain addChain(Chain chain) {

        // Company name validation
        if (chain.getCompanyName() == null ||
                chain.getCompanyName().trim().isEmpty()) {

            throw new RuntimeException("Company name is required");
        }

        // GSTN validation
        if (chain.getGstnNo() == null ||
                chain.getGstnNo().trim().isEmpty()) {

            throw new RuntimeException("GSTN number is required");
        }

        // Group validation
        if (chain.getGroup() == null ||
                chain.getGroup().getId() == null) {

            throw new RuntimeException("Group is required");
        }

        // Check GSTN duplicate
        if (chainRepository.existsByGstnNo(chain.getGstnNo())) {

            throw new RuntimeException("GSTN number already exists");
        }

        // Get existing active group
        Long groupId = chain.getGroup().getId();

        Group group = groupRepository.findByIdAndIsDeletedFalse(groupId)
                .orElseThrow(() ->
                        new RuntimeException("Active group not found"));

        // Set actual Group entity
        chain.setGroup(group);

        // New chain is active
        chain.setActive(true);

        return chainRepository.save(chain);
    }

    // Update Chain
    public Chain updateChain(Long id, Chain updatedChain) {

        Chain existingChain = getChainById(id);

        // Company name validation
        if (updatedChain.getCompanyName() == null ||
                updatedChain.getCompanyName().trim().isEmpty()) {

            throw new RuntimeException("Company name is required");
        }

        // GSTN validation
        if (updatedChain.getGstnNo() == null ||
                updatedChain.getGstnNo().trim().isEmpty()) {

            throw new RuntimeException("GSTN number is required");
        }

        // Group validation
        if (updatedChain.getGroup() == null ||
                updatedChain.getGroup().getId() == null) {

            throw new RuntimeException("Group is required");
        }

        // Check duplicate GSTN only if GSTN changed
        if (!existingChain.getGstnNo()
                .equalsIgnoreCase(updatedChain.getGstnNo())
                && chainRepository.existsByGstnNo(updatedChain.getGstnNo())) {

            throw new RuntimeException("GSTN number already exists");
        }

        // Get active group
        Long groupId = updatedChain.getGroup().getId();

        Group group = groupRepository.findByIdAndIsDeletedFalse(groupId)
                .orElseThrow(() ->
                        new RuntimeException("Active group not found"));

        // Update fields
        existingChain.setCompanyName(updatedChain.getCompanyName());
        existingChain.setGstnNo(updatedChain.getGstnNo());
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

    // Filter chains by Group
    public List<Chain> getChainsByGroup(Long groupId) {

        Group group = groupRepository
                .findByIdAndIsDeletedFalse(groupId)
                .orElseThrow(() ->
                        new RuntimeException("Active group not found"));

        return chainRepository.findByGroupAndIsActiveTrue(group);
    }
}