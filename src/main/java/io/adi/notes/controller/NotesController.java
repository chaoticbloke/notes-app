package io.adi.notes.controller;

import io.adi.notes.domain.Note;
import io.adi.notes.dto.HttpResponse;
import io.adi.notes.service.NoteService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequestMapping("/note")
@RestController
public class NotesController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/create")
    public ResponseEntity<HttpResponse<Note>> createNote(@RequestBody Note note) throws Exception {

     String location = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
     return ResponseEntity.created(URI.create(location)).body(noteService.createNote(note));

    }

    @GetMapping("/all")
    public ResponseEntity<HttpResponse<Note>> getNotes(){
        return ResponseEntity.ok().body(noteService.getNotes());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HttpResponse<Note>> updateNote(
            @PathVariable Long id,
            @RequestBody Note note,
            HttpServletRequest request) {

        HttpResponse<Note> response = noteService.updateNote(id, note);

        if (response.getHttpStatus() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(response); // 200 OK
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpResponse<?>> deleteNote(@PathVariable Long id, HttpServletRequest request){
        HttpResponse<?> httpResponse = noteService.deleteNote(id);

        if (httpResponse.getHttpStatus() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(httpResponse);
        }

        return ResponseEntity.ok(httpResponse); // 200 OK
    }
}
