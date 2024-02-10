package com.yerke.notes.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.beans.factory.annotation.Autowired;

@DataJpaTest
public class NoteTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testMapping() {
        // Создаем объект Note
        Note note = new Note();
        note.setTitle("Test Title");
        note.setContent("Test Content");

        // Сохраняем его в базу данных через EntityManager
        entityManager.persistAndFlush(note);

        // Получаем сохраненный объект из базы данных
        Note savedNote = entityManager.find(Note.class, note.getId());

        // Проверяем, что объект из базы данных совпадает с сохраненным объектом
        assertEquals(note, savedNote);
        assertEquals("Test Title", savedNote.getTitle());
        assertEquals("Test Content", savedNote.getContent());
    }
}