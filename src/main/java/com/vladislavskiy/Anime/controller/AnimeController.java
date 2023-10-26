package com.vladislavskiy.Anime.controller;


import com.vladislavskiy.Anime.dto.TyanCredentialsDto;
import com.vladislavskiy.Anime.repositories.TyanRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.*;

@RestController()
public class AnimeController {
    final private TyanRepository repository;

    public AnimeController(TyanRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/tyan")
    public ResponseEntity getAllTyan() throws SQLException {
        return repository.getAllTyans();
    }
    @GetMapping("/tyan/{id}")
    public ResponseEntity getAllTyan(@PathVariable Integer id) throws SQLException {
        return repository.getTyanById(id);
    }
    @PostMapping("/tyan")
    public ResponseEntity addNewTyan(@RequestBody TyanCredentialsDto tyanCredentialsDto) throws SQLException {
        return repository.addTyanByCredentials(tyanCredentialsDto);
    }
    @PutMapping("/tyan")
    public ResponseEntity updateTyan(@RequestBody TyanCredentialsDto tyanCredentialsDto) throws SQLException {
        return repository.updateTyanByCredentials(tyanCredentialsDto);
    }
}
