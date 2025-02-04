package com.example.demo.controller;

import com.example.demo.entity.Aboutme;
import com.example.demo.service.AboutmeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/aboutme")
public class AboutmeController {

    private final AboutmeService aboutMeService;

    public AboutmeController(AboutmeService aboutMeService) {
        this.aboutMeService = aboutMeService;
    }

    @GetMapping
    public ResponseEntity<List<Aboutme>> getAllAboutMe() {
        List<Aboutme> aboutMeList = aboutMeService.getAllAboutMe();
        if (aboutMeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(aboutMeList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aboutme> getAboutMeById(@PathVariable UUID id) {
        Aboutme aboutMe = aboutMeService.getAboutMeById(id);
        if (aboutMe == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(aboutMe, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Aboutme> addAboutMe(@RequestBody Aboutme aboutMe) {
        Aboutme savedAboutMe = aboutMeService.addAboutMe(aboutMe);
        return new ResponseEntity<>(savedAboutMe, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAboutMe(@PathVariable UUID id, @RequestBody Aboutme aboutme) {
        Aboutme updatedAboutMe = aboutMeService.updateAboutMe(id, aboutme.getName(), aboutme.getDescription());
        if (updatedAboutMe != null) {
            return ResponseEntity.ok("AboutMe updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAboutMe(@PathVariable UUID id) {
        boolean isDeleted = aboutMeService.deleteAboutMe(id);
        if (isDeleted) {
            return ResponseEntity.ok("AboutMe deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
