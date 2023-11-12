package com.vladislavskiy.Anime.services;

import com.vladislavskiy.Anime.dto.FilmCredentialsDto;
import com.vladislavskiy.Anime.dto.TyanCredentialsDto;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;

public interface FilmService {
    ResponseEntity getAllFilms() throws SQLException;

    ResponseEntity getFilmById(Integer id);

    ResponseEntity addFilmByCredentials(FilmCredentialsDto filmCredentialsDto);

    ResponseEntity updateFilmByCredentials(FilmCredentialsDto filmCredentialsDto);

    ResponseEntity deleteFilmById(Integer id);
}
