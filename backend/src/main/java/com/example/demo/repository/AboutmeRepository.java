package com.example.demo.repository;


import com.example.demo.entity.Aboutme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AboutmeRepository extends JpaRepository<Aboutme, UUID> {
}