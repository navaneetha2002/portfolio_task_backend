package com.example.demo.controller;


import com.example.demo.entity.Project;
import com.example.demo.entity.Skills;
import com.example.demo.service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/skills")
public class SkillsController {

    @Autowired
    private SkillsService skillsService;

    // Create a new skill
    @PostMapping
    public ResponseEntity<Skills> createSkill(@RequestParam("image") MultipartFile file) throws IOException {
        Skills skill = new Skills();
        skill.setImage(file.getBytes());
        Skills savedSkill = skillsService.saveSkill(skill);
        return ResponseEntity.ok(savedSkill);
    }

    // Get all skills
    @GetMapping
    public ResponseEntity<List<Skills>> getAllSkills() {
        List<Skills> skills = skillsService.getAllSkills();
        return ResponseEntity.ok(skills);
    }

    // Get skill by ID
    @GetMapping("/{id}")
    public ResponseEntity<Skills> getSkillById(@PathVariable UUID id) {
        Optional<Skills> skill = skillsService.getSkillById(id);
        return skill.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a skill
    @PutMapping("/{id}")
    public ResponseEntity<Skills> updateSkill(@PathVariable UUID id, @RequestParam("image") MultipartFile file) throws IOException {
        Skills updatedSkill = new Skills();
        updatedSkill.setImage(file.getBytes());
        Skills skill = skillsService.updateSkill(id, updatedSkill);
        return ResponseEntity.ok(skill);
    }

    // Delete a skill
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSkill(@PathVariable UUID id) {
        boolean isDeleted = skillsService.deleteSkill(id);
        if(isDeleted){
            return ResponseEntity.ok("Skill deleted successfully" );
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getSkillsImage(@PathVariable UUID id) {
        Optional<Skills> skill = skillsService.getSkillById(id); // Use getSkillById
        if (skill.isPresent() && skill.get().getImage() != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(skill.get().getImage());
        }
        return ResponseEntity.notFound().build();
    }

}
