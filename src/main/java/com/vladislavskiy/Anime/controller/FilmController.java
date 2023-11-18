package com.vladislavskiy.Anime.controller;


import com.vladislavskiy.Anime.dto.FilmCredentialsDto;
import com.vladislavskiy.Anime.handler.FilmNotFoundException;
import com.vladislavskiy.Anime.models.Film;
import com.vladislavskiy.Anime.services.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin
@Slf4j
public class FilmController {
    final private FilmService service;

    public FilmController(FilmService service) {
        this.service = service;
    }

    @GetMapping("/film")
    public List<Film> getAllFilms() {
        return service.getAllFilms();
    }

    @GetMapping("/film/{id}")
    public ResponseEntity<?> getFilmById(@PathVariable Long id) {
        try {
            return new ResponseEntity<Film>(service.getFilmById(id), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .header("Custom-Header", "значення")
                    .body("message: Film with current id: " + id + " hasn't found");
        }
    }

    @PostMapping("/film")
    public ResponseEntity<Film> addNewFilm(@RequestBody FilmCredentialsDto filmCredentialsDto) {
        return new ResponseEntity<>(service.addFilmByCredentials(filmCredentialsDto), HttpStatus.OK);
    }

    @PostMapping("/video")
    public String uploadVideo(@RequestParam("file") MultipartFile file) {
        try {
            return service.uploadVideo(file);
        } catch (Exception e) {
            return "Error uploading video: " + e.getMessage();
        }
    }

    @PutMapping("/film")
    public ResponseEntity<Film> updateFilm(@RequestBody FilmCredentialsDto filmCredentialsDto) {
        return new ResponseEntity<>(service.updateFilmByCredentials(filmCredentialsDto), HttpStatus.OK);
    }

    @DeleteMapping("/film/{id}")
    public ResponseEntity<?> deleteFilm(@PathVariable Long id) throws SQLException {
        try {
            service.deleteFilmById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (FilmNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
