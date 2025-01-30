package com.example.demo.repository;
import com.example.demo.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface ProjectRepository extends JpaRepository<Project, UUID> {
    }
