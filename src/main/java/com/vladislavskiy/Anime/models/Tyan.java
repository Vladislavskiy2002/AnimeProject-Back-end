package com.vladislavskiy.Anime.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "tyans")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tyan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;
}
