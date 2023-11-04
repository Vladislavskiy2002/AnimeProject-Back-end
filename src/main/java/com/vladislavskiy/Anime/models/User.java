package com.vladislavskiy.Anime.models;

import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Table(name = "user")
@Data
public class User {
    @Id
    private Long id;
    private String name;
    private String surname;
    private String password;
}
