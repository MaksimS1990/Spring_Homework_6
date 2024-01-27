package com.example.homework_6.Note.repositories;

import com.example.homework_6.Note.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    /**
     * Доп метод поиска по идентификатору
     * @param id
     * @return
     */
    Optional<Note> findById(Long id);
}
