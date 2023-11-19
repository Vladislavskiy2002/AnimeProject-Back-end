package com.vladislavskiy.Anime.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String message;
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film filmId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserInfo userId;

    public UserInfo getUserId() {
        return userId;
    }

    public void setUserId(UserInfo userId) {
        this.userId = userId;
    }

    public Film getFilmId() {
        return filmId;
    }

    public void setFilmId(Film filmId) {
        this.filmId = filmId;
    }
}
