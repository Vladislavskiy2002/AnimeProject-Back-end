package com.vladislavskiy.Anime.services.Impl;

import com.vladislavskiy.Anime.dto.FilmCredentialsDto;
import com.vladislavskiy.Anime.models.Film;
import com.vladislavskiy.Anime.services.FilmService;
import com.vladislavskiy.Anime.utils.FilmUtill;
import com.vladislavskiy.Anime.utils.TyanUtill;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
@Transactional
public class FilmServiceImpl implements FilmService {
    final private FilmUtill filmUtill;
    public FilmServiceImpl(FilmUtill filmUtill) {
        this.filmUtill = filmUtill;
    }
    @Override
    public ResponseEntity getAllFilms() throws SQLException {
        return filmUtill.getAllFilms();
    }
    @Override
    public ResponseEntity getFilmById(Integer id) {
        return filmUtill.getFilmById(id);
    }
    @Override
    public ResponseEntity addFilmByCredentials(FilmCredentialsDto filmCredentialsDto) {
        return filmUtill.saveFilm(new Film(filmCredentialsDto.id(),filmCredentialsDto.name(),filmCredentialsDto.rating(),filmCredentialsDto.description(),filmCredentialsDto.filename()));
    }
    @Override
    public ResponseEntity updateFilmByCredentials(FilmCredentialsDto filmCredentialsDto) {
        return filmUtill.updateFilmByCredentials(filmCredentialsDto);
    }

    @Override
    public ResponseEntity deleteFilmById(Integer id) {
        return filmUtill.deleteFilmById(id);
    }
}
