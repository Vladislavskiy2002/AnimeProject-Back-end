package com.vladislavskiy.Anime.services;

import org.springframework.http.ResponseEntity;

import java.sql.SQLException;

public interface TyanService {
    ResponseEntity getAllTyans() throws SQLException;
}
