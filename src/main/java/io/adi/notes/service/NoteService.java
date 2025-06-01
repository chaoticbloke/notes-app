package io.adi.notes.service;
import io.adi.notes.domain.Note;
import io.adi.notes.dto.HttpResponse;
import io.adi.notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public HttpResponse<Note> createNote(Note note) throws Exception{
        String location = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
        note.setCreatedAt(LocalDateTime.now());
        Note saved = noteRepository.save(note);

        HttpResponse<Note> httpResponse = HttpResponse.<Note>builder()
                .data(Collections.singleton(saved))
                .httpStatus(HttpStatus.CREATED)
                .path(location)
                .statusCode(201)
                .message("Note created with id = "+ saved.getId())
                .build();

        return httpResponse;
    }

   public HttpResponse<Note> getNotes(){
       String location = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
       List<Note> notes = noteRepository.findAll();
        HttpResponse<Note> httpResponse = HttpResponse.<Note>builder()
                .data(notes)
                .httpStatus(HttpStatus.OK)
                .path(location)
                .statusCode(200)
                .message("Total notes fetched are = "+ notes.size())
                .build();

        return httpResponse;
    }

    public HttpResponse<Note> updateNote(Long noteId, Note note) {
        String location = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
        return noteRepository.findById(noteId)
                .map(existingNote -> {
                    existingNote.setUpdatedAt(LocalDateTime.now());
                    existingNote.setTitle(note.getTitle());
                    existingNote.setDescription(note.getDescription());
                    existingNote.setLevel(note.getLevel());
                    Note savedNote = noteRepository.save(existingNote);

                    return HttpResponse.<Note>builder()
                            .data(Collections.singleton(savedNote))
                            .statusCode(HttpStatus.OK.value())
                            .httpStatus(HttpStatus.OK)
                            .message("Note with ID " + noteId + " updated successfully")
                            .path(location)
                            .build();
                })
                .orElseGet(() -> HttpResponse.<Note>builder()
                        .data(null)
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .path(location)
                        .message("Note with ID " + noteId + " not found")
                        .build());
    }

    public HttpResponse<?> deleteNote(Long noteId) {
      String location =  ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
        Optional<Note> noteOptional = noteRepository.findById(noteId);

        if (noteOptional.isEmpty()) {
            return HttpResponse.builder()
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message("Note with ID " + noteId + " not found")
                    .path(location)
                    .build();
        }

        noteRepository.deleteById(noteId);

        return HttpResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("Note deleted successfully")
                .path(location)
                .build();
    }

}
