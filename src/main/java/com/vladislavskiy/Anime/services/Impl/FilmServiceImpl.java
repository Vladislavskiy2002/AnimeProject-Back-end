package com.vladislavskiy.Anime.services.Impl;

import com.vladislavskiy.Anime.dto.FilmCredentialsDto;
import com.vladislavskiy.Anime.handler.FilmNotFoundException;
import com.vladislavskiy.Anime.models.Film;
import com.vladislavskiy.Anime.repositories.FilmRepository;
import com.vladislavskiy.Anime.services.FilmService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public List<Film> getAllFilms() {
        return Optional.of(filmRepository.findAll()).orElseThrow(() -> new NoSuchElementException("Elements not found"));
    }

    @Override
    public Film getFilmById(Long id) {
        return filmRepository.findById(id).orElseThrow((() -> new NoSuchElementException("Element with id " + id + " is not found")));
    }

    @Override
    public Film addFilmByCredentials(FilmCredentialsDto filmCredentialsDto) {
        return filmRepository.save(new Film(filmCredentialsDto.id(), filmCredentialsDto.name(), filmCredentialsDto.rating(), filmCredentialsDto.description(), filmCredentialsDto.filename(), filmCredentialsDto.picture()));
    }

    @Override
    public String uploadVideo(MultipartFile file) {
        try {
            // Отримати шлях до каталогу для збереження відео
            Path uploadPath = Paths.get("D:/frontend/anime-frontend-app/src/assets");

            // Забезпечте існування каталогу
            Files.createDirectories(uploadPath);

            // Генерувати нове ім'я файлу на основі кількості вже існуючих відео
            String fileName = generateFileName(getVideoCount() + 1);

            // Збереження відеофайлу на файловій системі з новим іменем
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath);
            return fileName;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Integer getVideoCount() throws Exception {
        // Отримати кількість вже існуючих відеофайлів
        List<Path> existingVideos = getAllVideoPaths();
        return existingVideos.size();
    }

    // Метод для генерації імені файлу на основі кількості вже існуючих відеофайлів
    private String generateFileName(int fileCount) {
        return fileCount + ".mp4";
    }

    // Метод для отримання всіх відеофайлів у каталозі
    private List<Path> getAllVideoPaths() throws IOException {
        // Отримайте шлях до каталогу для збереження відео
        Path uploadPath = Paths.get("D:/frontend/anime-frontend-app/src/assets");

        // Отримати список всіх відеофайлів
        return Files.list(uploadPath).filter(Files::isRegularFile).toList();
    }

    @Override
    public Film updateFilmByCredentials(FilmCredentialsDto filmCredentialsDto) {
        return filmRepository.update(filmCredentialsDto.id(), filmCredentialsDto.name(), filmCredentialsDto.rating(), filmCredentialsDto.description());
    }

    @Override
    public Film deleteFilmById(Long id) {
        Film film = filmRepository.findById(id).orElseThrow(() -> new FilmNotFoundException("Film hasn't found"));
        filmRepository.deleteById(id);
        return film;
    }
}
