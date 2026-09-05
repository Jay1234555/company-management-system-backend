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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.company.company_management_system.entity.CompanyService;
import com.company.company_management_system.service.ServiceService;


@RestController
@RequestMapping("/api/services")
@CrossOrigin(
    origins = {
        "http://localhost:3000",
        "https://sparkling-communication-production-0a7a.up.railway.app"
    },
    allowedHeaders = "*",
    methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.DELETE,
        RequestMethod.OPTIONS
    }
)
public class ServiceController {

    private final ServiceService service;

    public ServiceController(ServiceService service) {
        this.service = service;
    }

    @GetMapping
    public List<CompanyService> getAllServices() {
        return service.getAllServices();
    }

    @GetMapping("/{id}")
    public CompanyService getServiceById(@PathVariable Long id) {
        return service.getServiceById(id);
    }

    @PostMapping
    public CompanyService createService(@RequestBody CompanyService companyService) {
        return service.createService(companyService);
    }

    @PutMapping("/{id}")
    public CompanyService updateService(
            @PathVariable Long id,
            @RequestBody CompanyService companyService) {

        return service.updateService(id, companyService);
    }

    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable Long id) {
        service.deleteService(id);
    }
}