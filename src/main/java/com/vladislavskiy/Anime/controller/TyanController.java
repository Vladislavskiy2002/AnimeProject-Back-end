package com.vladislavskiy.Anime.controller;


import com.vladislavskiy.Anime.dto.TyanCredentialsDto;
import com.vladislavskiy.Anime.repositories.TyanRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class TyanController {
    final private TyanRepository repository;

    public TyanController(TyanRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/go")
    public String getAllgoTyan() throws SQLException {
        return "repository.getAllTyans()";
    }
    @GetMapping("/go/{username}")
    public String getAllgoTyan(@PathVariable String username) throws SQLException {
        return "repository.getAllTyans()" + username;
    }
    @PostMapping("/go")
    public String addAllgoTyan() throws SQLException {
        return "repository.addAllTyans()";
    }
    @GetMapping("/tyan")
    @PreAuthorize("hasRole('USER')")
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
