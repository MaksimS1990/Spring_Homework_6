package com.example.homework_6.Note.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
// @AllArgsConstructor
@NoArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String status;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private LocalDateTime CreationTime;

    /**
     * Конструктор с двумя полями(остальные поля гененрируются автоматом)
     * @param description описание
     * @param status ствтус
     */
    public Note(String description, String status) {
        this.description = description;
        this.status = status;
    }
}
