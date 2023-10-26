package com.vladislavskiy.Anime.repositories.impl;

import com.vladislavskiy.Anime.dto.TyanCredentialsDto;
import com.vladislavskiy.Anime.repositories.TyanRepository;
import com.vladislavskiy.Anime.services.TyanService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
@Repository
public class TyanRepositoryImpl implements TyanRepository {
    final private TyanService tyanService;

    public TyanRepositoryImpl(TyanService tyanService) {
        this.tyanService = tyanService;
    }

    public ResponseEntity getAllTyans() throws SQLException {
        return tyanService.getAllTyans();
    }

    @Override
    public ResponseEntity getTyanById(Integer id) {
        return tyanService.getTyanById(id);
    }

    @Override
    public ResponseEntity addTyanByCredentials(TyanCredentialsDto tyanCredentialsDto) {
        return tyanService.addTyanByCredentials(tyanCredentialsDto);
    }
}
