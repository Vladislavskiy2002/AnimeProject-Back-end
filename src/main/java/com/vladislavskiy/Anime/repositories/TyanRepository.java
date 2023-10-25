package com.vladislavskiy.Anime.repositories;

import org.springframework.http.ResponseEntity;

import java.sql.SQLException;

public interface TyanRepository {
    ResponseEntity getAllTyans() throws SQLException;
}
