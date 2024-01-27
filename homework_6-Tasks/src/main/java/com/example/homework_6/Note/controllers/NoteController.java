package com.example.homework_6.Note.controllers;

import com.example.homework_6.Note.model.Note;
import com.example.homework_6.Note.repositories.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/note")
public class NoteController {
    private final NoteRepository repository;

    @PostMapping
    /**
     * добавить заметку
     */
    public ResponseEntity<Note> addNote(@RequestBody Note note) {
        note.setCreationTime(LocalDateTime.now());
        return new ResponseEntity<>(repository.save(note), HttpStatus.CREATED);
    }

    /**
     * показать все заметки
     * @return возврается List (список) всех заметок
     */
    @GetMapping
    public ResponseEntity<List<Note>> findAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    /**
     * найти заметку по идентификатору
     * @param id идентикатор
     * @return возврается найденная заметка либо null
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Note note = repository.findById(id).orElse(null);
        if (note != null) {
            return new ResponseEntity<>(note, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * обновить заметку по идентификатору
     * @param id идентификатор
     * @param note наша заметка
     * @return возвращается обновленная заметка
     */
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateById(@PathVariable Long id, @RequestBody Note note) {
        Note n = repository.findById(id).orElse(null);
        if (n != null) {
            note.setCreationTime(n.getCreationTime());
            note.setId(n.getId());
            return new ResponseEntity<>(repository.save(note), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * удаление заметки по идентификатору
     * @param id идентификатор
     * @return "Void" - то есть, пустой ответ
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        Note n = repository.findById(id).orElse(null);
        if (n != null) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
