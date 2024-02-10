package com.yerke.notes.repository;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.yerke.notes.model.Note;

@DataJpaTest
public class NoteRepositoryTest {

    @Autowired
    private NoteRepository noteRepository;

    @Test
    public void testFindById() {
        // Создаем тестовую запись
        Note note = new Note();
        note.setTitle("Test Title");
        note.setContent("Test Content");

        // Сохраняем запись в репозиторий
        Note savedNote = noteRepository.save(note);

        // Проверяем, что id сохраненной записи не null
        assertNotNull(savedNote.getId());

        // Проверяем, что найденная запись по id не null и ее содержимое совпадает с
        // ожидаемым
        Note foundNote = noteRepository.findById(savedNote.getId()).orElse(null);
        assertNotNull(foundNote);
        assertEquals("Test Title", foundNote.getTitle());
        assertEquals("Test Content", foundNote.getContent());
    }
}