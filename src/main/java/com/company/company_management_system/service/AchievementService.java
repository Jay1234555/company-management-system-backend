
package com.company.company_management_system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.company_management_system.entity.Achievement;
import com.company.company_management_system.repository.AchievementRepository;

@Service
public class AchievementService {

    private final AchievementRepository achievementRepository;

    public AchievementService(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }

    // Get all achievements
    public List<Achievement> getAllAchievements() {
        return achievementRepository.findAll();
    }

    // Get achievement by ID
    public Achievement getAchievementById(Long id) {
        return achievementRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Achievement not found with id: " + id
                    ));
    }

    // Create achievement
    public Achievement createAchievement(Achievement achievement) {
        return achievementRepository.save(achievement);
    }

    // Update achievement
    public Achievement updateAchievement(
            Long id,
            Achievement achievementDetails) {

        Achievement achievement =
                achievementRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Achievement not found with id: " + id
                    ));

        achievement.setTitle(achievementDetails.getTitle());
        achievement.setDescription(achievementDetails.getDescription());

        return achievementRepository.save(achievement);
    }

    // Delete achievement
    public void deleteAchievement(Long id) {

        Achievement achievement =
                achievementRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Achievement not found with id: " + id
                    ));

        achievementRepository.delete(achievement);
    }
}