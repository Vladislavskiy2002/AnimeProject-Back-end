package com.vladislavskiy.Anime.controller;


import com.vladislavskiy.Anime.repositories.TyanRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.*;

@RestController
public class AnimeController {
    TyanRepository repository;

    public AnimeController(TyanRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/all")
    public ResponseEntity getAllTyan() throws SQLException {
        return repository.getAllTyans();
    }
}
