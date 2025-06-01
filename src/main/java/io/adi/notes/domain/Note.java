package io.adi.notes.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ✅ Primary key generation
    private Long id;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING) //stores as HIGH,LOW,MEDIUM
    private Level level;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
