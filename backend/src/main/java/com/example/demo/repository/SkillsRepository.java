package com.example.demo.repository;


import com.example.demo.entity.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SkillsRepository extends JpaRepository<Skills, UUID> {
}
