package com.vladislavskiy.Anime.utils;

import com.vladislavskiy.Anime.dto.FilmCredentialsDto;
import com.vladislavskiy.Anime.models.Film;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class FilmUtill {
    final private DataSource dataSource;
    final private Connection con;

    public FilmUtill(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        this.con = dataSource.getConnection();
    }

    public ResponseEntity getAllFilms() throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT * from films")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return ResponseEntity.of(Optional.of(getFilm(resultSet)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity getFilmById(Integer id) {
        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT * from films where id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return ResponseEntity.of(Optional.of(getFilm(resultSet)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Film> getFilm(ResultSet resultSet) throws SQLException {
        List<Film> films = new ArrayList<>();
        while (resultSet.next()) {
            Film film = new Film();
            film.setId(resultSet.getInt("id"));
            film.setName(resultSet.getString("name"));
            film.setRating(resultSet.getDouble("rating"));
            film.setDescription(resultSet.getString("description"));
            film.setContentURL(resultSet.getString("video_url"));
            films.add(film);
        }
        return films;
    }

    public ResponseEntity saveFilm(Film film) {
        try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO films (name, rating, description,video_url) VALUES (?,?,?,?)")) {
            preparedStatement.setString(1, film.getName());
            preparedStatement.setDouble(2, film.getRating());
            preparedStatement.setString(3, film.getDescription());
            preparedStatement.setString(4, film.getContentURL());
            int i = preparedStatement.executeUpdate();
            if (i > 0)
                return ResponseEntity.ok(film);
            else return (ResponseEntity) ResponseEntity.noContent();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ResponseEntity updateFilmByCredentials(FilmCredentialsDto filmCredentialsDto) {
        try (PreparedStatement preparedStatement = con.prepareStatement("update films set name = ?, rating = ?, description = ? where id = ?")) {
            preparedStatement.setString(1, filmCredentialsDto.name());
            preparedStatement.setDouble(2, filmCredentialsDto.rating());
            preparedStatement.setString(3, filmCredentialsDto.description());
            preparedStatement.setInt(4, filmCredentialsDto.id());
            int i = preparedStatement.executeUpdate();
            if (i > 0)
                return ResponseEntity.ok(filmCredentialsDto);
            else return (ResponseEntity) ResponseEntity.noContent();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ResponseEntity deleteFilmById(Integer id) {
        try (PreparedStatement preparedStatement = con.prepareStatement("delete from films where id = ?")) {
            preparedStatement.setInt(1, id);
            int i = preparedStatement.executeUpdate();
            if (i > 0)
                return ResponseEntity.ok(id);
            else return (ResponseEntity) ResponseEntity.noContent();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
