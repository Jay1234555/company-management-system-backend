package com.company.company_management_system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.company_management_system.entity.CompanyService;
import com.company.company_management_system.repository.ServiceRepository;

@Service
public class ServiceService {

    private final ServiceRepository repository;

    public ServiceService(ServiceRepository repository) {
        this.repository = repository;
    }

    public List<CompanyService> getAllServices() {
        return repository.findAll();
    }

    public CompanyService getServiceById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));
    }

    public CompanyService createService(CompanyService service) {
        return repository.save(service);
    }

    public CompanyService updateService(Long id, CompanyService service) {

        CompanyService existing = getServiceById(id);

        existing.setTitle(service.getTitle());
        existing.setDescription(service.getDescription());
        existing.setImageUrl(service.getImageUrl());

        return repository.save(existing);
    }

    public void deleteService(Long id) {
        repository.deleteById(id);
    }
}