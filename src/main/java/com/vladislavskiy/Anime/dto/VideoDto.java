package com.vladislavskiy.Anime.dto;

import org.springframework.web.multipart.MultipartFile;

public record VideoDto (MultipartFile mp4Content) {
}
