package com.vladislavskiy.Anime.services;

import com.vladislavskiy.Anime.dto.FilmCredentialsDto;
import com.vladislavskiy.Anime.models.Film;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FilmService {
    List<Film> getAllFilms();

    Film getFilmById(Long id);

    Film addFilmByCredentials(FilmCredentialsDto filmCredentialsDto);

    String uploadVideo(MultipartFile file);

    Film updateFilmByCredentials(FilmCredentialsDto filmCredentialsDto);

    Film deleteFilmById(Long id);
}
