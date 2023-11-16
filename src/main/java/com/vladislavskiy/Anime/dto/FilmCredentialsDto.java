package com.vladislavskiy.Anime.dto;

import org.springframework.web.multipart.MultipartFile;

public record FilmCredentialsDto(Integer id, String name, Double rating, String description, String filename
) {
}
