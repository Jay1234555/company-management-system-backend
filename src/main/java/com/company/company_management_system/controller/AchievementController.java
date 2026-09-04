
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

import com.company.company_management_system.entity.Achievement;
import com.company.company_management_system.service.AchievementService;

@RestController
@RequestMapping("/api/achievements")
@CrossOrigin(origins = {
	    "http://localhost:3000",
	    "sparkling-communication-production-0a7a.up.railway.app"
	})
public class AchievementController {
	

    private final AchievementService achievementService;

    public AchievementController(
            AchievementService achievementService) {

        this.achievementService = achievementService;
    }

    // GET all achievements
    @GetMapping
    public List<Achievement> getAllAchievements() {
        return achievementService.getAllAchievements();
    }

    // GET achievement by ID
    @GetMapping("/{id}")
    public Achievement getAchievement(
            @PathVariable Long id) {

        return achievementService.getAchievementById(id);
    }

    // CREATE achievement
    @PostMapping
    public Achievement createAchievement(
            @RequestBody Achievement achievement) {

        return achievementService.createAchievement(achievement);
    }

    // UPDATE achievement
    @PutMapping("/{id}")
    public Achievement updateAchievement(
            @PathVariable Long id,
            @RequestBody Achievement achievement) {

        return achievementService.updateAchievement(
                id,
                achievement
        );
    }

    // DELETE achievement
    @DeleteMapping("/{id}")
    public String deleteAchievement(
            @PathVariable Long id) {

        achievementService.deleteAchievement(id);

        return "Achievement deleted successfully";
    }
}
