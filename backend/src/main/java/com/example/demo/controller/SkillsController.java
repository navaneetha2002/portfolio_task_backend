package com.example.demo.controller;

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
    public ResponseEntity<Skills> createSkill(@RequestParam("name") String name, @RequestParam("image") MultipartFile file) throws IOException {
        Skills skill = new Skills();
        skill.setName(name);  // Set the name from the request
        skill.setImage(file.getBytes());  // Set the image file as bytes
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

    @PutMapping("/{id}")
    public ResponseEntity<Skills> updateSkill(
            @PathVariable UUID id,
            @RequestParam("name") String name,
            @RequestParam("image") MultipartFile file
    ) throws IOException {
        Optional<Skills> existingSkillOpt = skillsService.getSkillById(id);
        if (existingSkillOpt.isPresent()) {
            Skills updatedSkill = existingSkillOpt.get();
            updatedSkill.setName(name);  // Update the name
            updatedSkill.setImage(file.getBytes());  // Update the image

            Skills savedSkill = skillsService.saveSkill(updatedSkill);  // Save the updated skill

            return ResponseEntity.ok(savedSkill);
        }
        return ResponseEntity.notFound().build();
    }


    // Delete a skill
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSkill(@PathVariable UUID id) {
        boolean isDeleted = skillsService.deleteSkill(id);
        if (isDeleted) {
            return ResponseEntity.ok("Skill deleted successfully");
        }
        return ResponseEntity.noContent().build();
    }

    // Retrieve skill image by ID
    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getSkillsImage(@PathVariable UUID id) {
        Optional<Skills> skill = skillsService.getSkillById(id);
        if (skill.isPresent() && skill.get().getImage() != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(skill.get().getImage());
        }
        return ResponseEntity.notFound().build();
    }
}
