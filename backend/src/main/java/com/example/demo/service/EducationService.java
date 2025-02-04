package com.example.demo.service;

import com.example.demo.entity.Education;
import com.example.demo.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EducationService {

    @Autowired
    private final EducationRepository educationRepository;

    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    public List<Education> getAllEducation() {
        return educationRepository.findAll();
    }

    public Education getEducationById(UUID id) {
        return educationRepository.findById(id).orElse(null);
    }

    public Education addEducation(Education education) {
        return educationRepository.save(education);
    }

    public Education updateEducation(UUID id, String name, String description) {
        Education existingEducation = getEducationById(id);
        if (existingEducation != null) {
            existingEducation.setName(name);
            existingEducation.setDescription(description);
            return educationRepository.save(existingEducation);
        }
        return null;
    }

    public boolean deleteEducation(UUID id) {
        if (educationRepository.existsById(id)) {
            educationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
