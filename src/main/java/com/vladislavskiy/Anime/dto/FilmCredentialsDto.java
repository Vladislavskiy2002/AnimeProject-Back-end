package com.vladislavskiy.Anime.dto;

public record FilmCredentialsDto(Long id, String name, Double rating, String description, String filename,
                                 String picture
) {
}
