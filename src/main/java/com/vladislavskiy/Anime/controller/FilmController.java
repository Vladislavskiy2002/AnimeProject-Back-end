package com.vladislavskiy.Anime.controller;


import com.vladislavskiy.Anime.dto.FilmCredentialsDto;
import com.vladislavskiy.Anime.repositories.FilmRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin
@Slf4j
public class FilmController {
    final private FilmRepository repository;
    public FilmController(FilmRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/film")
    public ResponseEntity getAllFilms() throws SQLException {
        return repository.getAllFilms();
    }
    @GetMapping("/film/{id}")
    public ResponseEntity getFilmById(@PathVariable Integer id) throws SQLException {
        return repository.getFilmById(id);
    }
    @PostMapping("/film")
    public ResponseEntity addNewFilm(@RequestBody FilmCredentialsDto filmCredentialsDto) throws SQLException {
        log.info(filmCredentialsDto.toString());
        return repository.addFilmByCredentials(filmCredentialsDto);
    }
    @GetMapping("/count")
    public int getVideoCount() throws Exception {
        // Отримати кількість вже існуючих відеофайлів
        List<Path> existingVideos = getAllVideoPaths();
        return existingVideos.size();
    }

    @PostMapping("/video")
    public String uploadVideo(@RequestParam("file") MultipartFile file) {
        try {
            // Отримати шлях до каталогу для збереження відео
            Path uploadPath = Paths.get("D:/frontend/anime-frontend-app/src/assets");

            // Забезпечте існування каталогу
            Files.createDirectories(uploadPath);

            // Отримати кількість вже існуючих відеофайлів
            List<Path> existingVideos = getAllVideoPaths();

            // Генерувати нове ім'я файлу на основі кількості вже існуючих відео
            String fileName = generateFileName(existingVideos.size() + 1);

            // Збереження відеофайлу на файловій системі з новим іменем
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath);

            return fileName;
        } catch (Exception e) {
            return "Error uploading video: " + e.getMessage();
        }
    }

    // Метод для генерації імені файлу на основі кількості вже існуючих відеофайлів
    private String generateFileName(int fileCount) {
        return fileCount + ".mp4";
    }

    // Метод для отримання всіх відеофайлів у каталозі
    private List<Path> getAllVideoPaths() throws Exception {
        // Отримайте шлях до каталогу для збереження відео
        Path uploadPath = Paths.get("D:/frontend/anime-frontend-app/src/assets");

        // Отримати список всіх відеофайлів
        return Files.list(uploadPath).filter(Files::isRegularFile).toList();
    }
    @PutMapping("/film")
    public ResponseEntity updateFilm(@RequestBody FilmCredentialsDto filmCredentialsDto) throws SQLException {
        return repository.updateFilmByCredentials(filmCredentialsDto);
    }
    @DeleteMapping("/film/{id}")
    public ResponseEntity deleteFilm(@PathVariable Integer id) throws SQLException {
        return repository.deleteFilmById(id);
    }
}
