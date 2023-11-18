package com.vladislavskiy.Anime.repositories;

import com.vladislavskiy.Anime.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {
    @Query("select f from films f")
    List<Film> findAll();

    @Modifying
    @Query("update films f set f.name = :name, f.rating = :rating, f.description = :description where f.id = :id")
    Film update(@Param("id") Long id, @Param("name") String name, @Param("rating") Double rating, @Param("description") String description);
}
