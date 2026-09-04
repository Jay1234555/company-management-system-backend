
package com.company.company_management_system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.company_management_system.entity.Project;
import com.company.company_management_system.repository.ProjectRepository;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    // Get all projects
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // Get project by ID
    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Project not found with id: " + id));
    }

    // Create project
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    // Update project
    public Project updateProject(Long id, Project projectDetails) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Project not found with id: " + id));

        project.setProjectName(projectDetails.getProjectName());
        project.setDescription(projectDetails.getDescription());
        project.setTechnology(projectDetails.getTechnology());
        project.setImageUrl(projectDetails.getImageUrl());

        return projectRepository.save(project);
    }

    // Delete project
    public void deleteProject(Long id) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Project not found with id: " + id));

        projectRepository.delete(project);
    }
}

