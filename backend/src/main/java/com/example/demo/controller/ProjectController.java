package com.example.demo.controller;

import com.example.demo.entity.Project;
import com.example.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/projects")

public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // Get all projects
    @GetMapping
    public ResponseEntity<List<Project>> getProjects() {
        List<Project> projects = projectService.getAllProjects();
        if (projects.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    // Get project by ID
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("id") String id) {
        try {
            UUID uuid = UUID.fromString(id); // Validate and convert to UUID
            Project project = projectService.getProjectById(uuid);
            if (project == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(project, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Return 400 if the UUID is invalid
        }
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addProject(
            @RequestPart(value = "image", required=false) MultipartFile image,
            @RequestParam("name") String name,
            @RequestParam("description") String description
    ) {
        try{
            projectService.addProject(name, image, description);
            return ResponseEntity.ok("Project added successfully");
        } catch(IOException e){
            return ResponseEntity.internalServerError().body("Error saving project: " + e.getMessage());
        }


    }



    // Update an existing project
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateProject(
            @PathVariable UUID id,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        try {
            Project updatedProject = projectService.updateProject(id, name, image, description);
            if (updatedProject != null) {
                return ResponseEntity.ok("Project updated successfully");
            }else{
                return ResponseEntity.notFound().build();
            }

        }catch(IOException e){
            return ResponseEntity.internalServerError().body("Error updating project: " + e.getMessage());
        }


    }

    @GetMapping("{id}/image")
    public ResponseEntity<byte[]> getProjectImage(@PathVariable UUID id){
        Project project = projectService.getProjectById(id);
        if(project != null && project.getImage()!=null){
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(project.getImage());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable UUID id){
        boolean isDeleted = projectService.deleteProject(id);
        if(isDeleted){
            return ResponseEntity.ok("Project deleted successfully" );
        }
        return ResponseEntity.notFound().build();
    }

}