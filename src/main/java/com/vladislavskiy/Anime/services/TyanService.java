package com.vladislavskiy.Anime.services;

import com.vladislavskiy.Anime.dto.TyanCredentialsDto;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;

public interface TyanService {
    ResponseEntity getAllTyans() throws SQLException;

    ResponseEntity getTyanById(Integer id);

    ResponseEntity addTyanByCredentials(TyanCredentialsDto tyanCredentialsDto);
}
