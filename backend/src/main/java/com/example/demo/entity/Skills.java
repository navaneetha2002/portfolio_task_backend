package com.example.demo.entity;
import jakarta.persistence.*;
import java.util.UUID;
@Entity
public class Skills {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

    private UUID id;

    @Column(nullable = false)  // Ensure 'name' is not null
    private String name; // New column 'name'

    @Lob  // Use @Lob for binary data
    private byte[] image;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

