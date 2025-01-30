package com.example.demo.entity;
import jakarta.persistence.*;
import java.util.UUID;
@Entity
public class Skills {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

    private UUID id;

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

}

