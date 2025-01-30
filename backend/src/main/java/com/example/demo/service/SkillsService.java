package com.example.demo.service;


import com.example.demo.entity.Project;
import com.example.demo.entity.Skills;
import com.example.demo.repository.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SkillsService {

    @Autowired
    private SkillsRepository skillsRepository;

    // Create a new skill
    public Skills saveSkill(Skills skill) {
        return skillsRepository.save(skill);
    }

    // Get all skills
    public List<Skills> getAllSkills() {
        return skillsRepository.findAll();
    }

    // Get skill by ID
    public Optional<Skills> getSkillById(UUID id) {
        return skillsRepository.findById(id);
    }

    // Update a skill
    public Skills updateSkill(UUID id, Skills updatedSkill) {
        return skillsRepository.findById(id).map(skill -> {
            skill.setImage(updatedSkill.getImage());
            return skillsRepository.save(skill);
        }).orElseThrow(() -> new RuntimeException("Skill not found with ID: " + id));
    }

    // Delete a skill
    public boolean deleteSkill(UUID id) {
        if(skillsRepository.existsById(id)) {
            skillsRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
