package com.yerke.notes.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.yerke.notes.model.Note;
import com.yerke.notes.repository.NoteRepository;

public class NoteControllerTest {

    private NoteRepository noteRepository;
    private NoteController noteController;

    @BeforeEach
    public void setUp() {
        noteRepository = mock(NoteRepository.class);
        noteController = new NoteController(noteRepository);
    }

    @Test
    public void testGetAllNotes() {
        // Создаем тестовые заметки
        Note note1 = new Note();
        note1.setId(1L);
        note1.setTitle("Note 1");
        note1.setContent("Content 1");

        Note note2 = new Note();
        note2.setId(2L);
        note2.setTitle("Note 2");
        note2.setContent("Content 2");

        List<Note> notes = new ArrayList<>();
        notes.add(note1);
        notes.add(note2);

        // Настроим поведение noteRepository.findAll() для возвращения списка тестовых
        // заметок
        when(noteRepository.findAll()).thenReturn(notes);

        // Вызываем метод контроллера для получения всех заметок
        ResponseEntity<List<Note>> responseEntity = noteController.getAllNotes();

        // Проверяем, что ответ не null
        assertThat(responseEntity).isNotNull();

        // Проверяем, что ответ имеет статус OK
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Проверяем, что список заметок в ответе соответствует ожидаемому
        assertThat(responseEntity.getBody()).isEqualTo(notes);
    }

    // Тесты для других методов контроллера могут быть написаны аналогичным образом
}
