package com.company.company_management_system.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.company_management_system.entity.CompanyService;
import com.company.company_management_system.service.ServiceService;

@RestController
@RequestMapping("/api/services")
@CrossOrigin(origins = {
	    "http://localhost:3000",
"sparkling-communication-production-0a7a.up.railway.app"
	    })

public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    // GET all services
    @GetMapping
    public List<CompanyService> getAllServices() {
        return serviceService.getAllServices();
    }

    // GET service by ID
    @GetMapping("/{id}")
    public CompanyService getService(@PathVariable Long id) {
        return serviceService.getServiceById(id);
    }

    // CREATE service
    @PostMapping
    public CompanyService createService(@RequestBody CompanyService service) {
        return serviceService.createService(service);
    }

    // UPDATE service
    @PutMapping("/{id}")
    public CompanyService updateService(
            @PathVariable Long id,
            @RequestBody CompanyService service) {

        return serviceService.updateService(id, service);
    }

    // DELETE service
    @DeleteMapping("/{id}")
    public String deleteService(@PathVariable Long id) {

        serviceService.deleteService(id);

        return "Service deleted successfully";
    }
}