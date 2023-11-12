package com.vladislavskiy.Anime.controller;


import com.vladislavskiy.Anime.dto.FilmCredentialsDto;
import com.vladislavskiy.Anime.dto.TyanCredentialsDto;
import com.vladislavskiy.Anime.repositories.FilmRepository;
import com.vladislavskiy.Anime.repositories.TyanRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
//@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@CrossOrigin

public class FilmController {
    final private FilmRepository repository;
    public FilmController(FilmRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/film")
    public ResponseEntity getAllFilms() throws SQLException {
        return repository.getAllFilms();
    }
    @GetMapping("/film/{id}")
    public ResponseEntity getFilmById(@PathVariable Integer id) throws SQLException {
        return repository.getFilmById(id);
    }
    @PostMapping("/film")
    public ResponseEntity addNewFilm(@RequestBody FilmCredentialsDto filmCredentialsDto) throws SQLException {
        return repository.addFilmByCredentials(filmCredentialsDto);
    }
    @PutMapping("/film")
    public ResponseEntity updateFilm(@RequestBody FilmCredentialsDto filmCredentialsDto) throws SQLException {
        return repository.updateFilmByCredentials(filmCredentialsDto);
    }
    @DeleteMapping("/film/{id}")
    public ResponseEntity deleteFilm(@PathVariable Integer id) throws SQLException {
        return repository.deleteFilmById(id);
    }
}
