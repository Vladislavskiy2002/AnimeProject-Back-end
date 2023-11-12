package com.vladislavskiy.Anime.repositories.impl;

import com.vladislavskiy.Anime.dto.FilmCredentialsDto;
import com.vladislavskiy.Anime.repositories.FilmRepository;
import com.vladislavskiy.Anime.services.FilmService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class FilmRepositoryImpl implements FilmRepository {
    final private FilmService filmService;

    public FilmRepositoryImpl(FilmService filmService) {
        this.filmService = filmService;
    }

    @Override
    public ResponseEntity getAllFilms() throws SQLException {
        return filmService.getAllFilms();
    }
    @Override
    public ResponseEntity getFilmById(Integer id) {
        return filmService.getFilmById(id);
    }

    @Override
    public ResponseEntity addFilmByCredentials(FilmCredentialsDto filmCredentialsDto) {
        return filmService.addFilmByCredentials(filmCredentialsDto);
    }

    @Override
    public ResponseEntity updateFilmByCredentials(FilmCredentialsDto filmCredentialsDto) {
        return filmService.updateFilmByCredentials(filmCredentialsDto);
    }

    @Override
    public ResponseEntity deleteFilmById(Integer id) {
        return filmService.deleteFilmById(id);
    }
}
