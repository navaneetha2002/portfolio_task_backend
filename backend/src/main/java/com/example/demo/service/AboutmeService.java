package com.example.demo.service;

import com.example.demo.entity.Aboutme;
import com.example.demo.repository.AboutmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AboutmeService {

    @Autowired
    private AboutmeRepository aboutMeRepository;

    public List<Aboutme> getAllAboutMe() {
        return aboutMeRepository.findAll();
    }

    public Aboutme getAboutMeById(UUID id) {
        return aboutMeRepository.findById(id).orElse(null);
    }

    public Aboutme addAboutMe(Aboutme aboutMe) {
        return aboutMeRepository.save(aboutMe);
    }

    public Aboutme updateAboutMe(UUID id, String name, String description) {
        Aboutme existingAboutMe = getAboutMeById(id);
        if (existingAboutMe != null) {
            existingAboutMe.setName(name);
            existingAboutMe.setDescription(description);
            return aboutMeRepository.save(existingAboutMe);
        }
        return null;
    }

    public boolean deleteAboutMe(UUID id) {
        if (aboutMeRepository.existsById(id)) {
            aboutMeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}