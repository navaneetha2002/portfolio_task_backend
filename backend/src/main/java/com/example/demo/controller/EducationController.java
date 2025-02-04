package com.example.demo.controller;

import com.example.demo.entity.Education;
import com.example.demo.service.EducationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/education")
public class EducationController {

    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping
    public ResponseEntity<List<Education>> getProjects() {
        List<Education> education = educationService.getAllEducation();
        if (education.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(education, HttpStatus.OK);
    }

    @PostMapping
    public Education addEducation(@RequestBody Education education) {
        return educationService.addEducation(education);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEducation(@PathVariable("id") UUID id, @RequestBody Education education) {
        // Extract the name and description from the education entity
        String name = education.getName();  // Assuming name is a property of Education
        String description = education.getDescription();  // Assuming description is a property of Education

        // Now call the service method with id, name, and description
        educationService.updateEducation(id, name, description);

        return ResponseEntity.ok("Education updated successfully");
    }


    @GetMapping("/{id}")
    public ResponseEntity<Education> getEducationById(@PathVariable UUID id) {
        Education education = educationService.getEducationById(id);
        if (education == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(education, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEducation(@PathVariable UUID id) {
        boolean isDeleted = educationService.deleteEducation(id);
        if (isDeleted) {
            return ResponseEntity.ok("Project deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
