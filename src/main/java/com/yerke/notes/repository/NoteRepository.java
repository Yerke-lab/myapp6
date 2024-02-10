package com.yerke.notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yerke.notes.model.Note;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {
    @SuppressWarnings("null")
    Optional<Note> findById(Long id);
}
