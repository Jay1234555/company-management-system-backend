package com.company.company_management_system.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.company.company_management_system.entity.Chain;
import com.company.company_management_system.service.ChainService;

@RestController
@RequestMapping("/api/chains")
@CrossOrigin(origins = "*")
public class ChainController {

    private final ChainService chainService;

    public ChainController(ChainService chainService) {
        this.chainService = chainService;
    }

    // Get all active chains
    @GetMapping
    public ResponseEntity<List<Chain>> getAllChains() {

        return ResponseEntity.ok(
                chainService.getAllChains()
        );
    }

    // Get Chain by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getChainById(
            @PathVariable Long id) {

        try {

            return ResponseEntity.ok(
                    chainService.getChainById(id)
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // Add Chain
    @PostMapping
    public ResponseEntity<?> addChain(
            @RequestBody Chain chain) {

        try {

            Chain savedChain =
                    chainService.addChain(chain);

            return ResponseEntity.ok(savedChain);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // Update Chain
    @PutMapping("/{id}")
    public ResponseEntity<?> updateChain(
            @PathVariable Long id,
            @RequestBody Chain chain) {

        try {

            Chain updatedChain =
                    chainService.updateChain(id, chain);

            return ResponseEntity.ok(updatedChain);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // Soft Delete Chain
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteChain(
            @PathVariable Long id) {

        try {

            String message =
                    chainService.deleteChain(id);

            return ResponseEntity.ok(message);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // Filter Chain by Group
    @GetMapping("/group/{groupId}")
    public ResponseEntity<?> getChainsByGroup(
            @PathVariable Long groupId) {

        try {

            return ResponseEntity.ok(
                    chainService.getChainsByGroup(groupId)
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}