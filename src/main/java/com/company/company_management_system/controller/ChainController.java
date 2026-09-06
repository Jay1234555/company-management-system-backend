package com.company.company_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.company_management_system.entity.Chain;
import com.company.company_management_system.service.ChainService;

@RestController
@RequestMapping("/api/chains")
@CrossOrigin(origins = {
        "http://localhost:3000",
        "https://sparkling-communication-production-0a7a.up.railway.app"
})
public class ChainController {

    @Autowired
    private ChainService chainService;

    // Get All Active Chains
    @GetMapping
    public ResponseEntity<List<Chain>> getAllChains() {

        return ResponseEntity.ok(chainService.getAllChains());
    }

    // Filter Chains By Group
    @GetMapping("/group/{groupId}")
    public ResponseEntity<?> getChainsByGroup(
            @PathVariable Long groupId) {

        try {

            return ResponseEntity.ok(
                    chainService.getChainsByGroup(groupId)
            );

        } catch (RuntimeException e) {

            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    // Get Chain By ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getChainById(
            @PathVariable Long id) {

        try {

            return ResponseEntity.ok(
                    chainService.getChainById(id)
            );

        } catch (RuntimeException e) {

            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    // Create Chain
    @PostMapping
    public ResponseEntity<?> addChain(
            @RequestBody Chain chain) {

        try {

            Chain savedChain =
                    chainService.addChain(chain);

            return new ResponseEntity<>(
                    savedChain,
                    HttpStatus.CREATED
            );

        } catch (IllegalArgumentException e) {

            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );

        } catch (RuntimeException e) {

            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
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

        } catch (IllegalArgumentException e) {

            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );

        } catch (RuntimeException e) {

            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    // Soft Delete Chain
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteChain(
            @PathVariable Long id) {

        try {

            chainService.deleteChain(id);

            return ResponseEntity.ok(
                    "Chain deleted successfully"
            );

        } catch (RuntimeException e) {

            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        }
    }
}