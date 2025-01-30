package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Project {

        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)

        private UUID id;

        @Column(nullable = false)
        private String name;

        @Column(nullable = false )
        @Lob
        private String description;

    @Lob  // Use @Lob for binary data
    private byte[] image;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
