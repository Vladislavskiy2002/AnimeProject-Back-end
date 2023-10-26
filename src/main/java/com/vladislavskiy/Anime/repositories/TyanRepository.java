package com.vladislavskiy.Anime.repositories;

import com.vladislavskiy.Anime.dto.TyanCredentialsDto;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;

public interface TyanRepository {
    ResponseEntity getAllTyans() throws SQLException;

    ResponseEntity getTyanById(Integer id);

    ResponseEntity addTyanByCredentials(TyanCredentialsDto tyanCredentialsDto);
}
