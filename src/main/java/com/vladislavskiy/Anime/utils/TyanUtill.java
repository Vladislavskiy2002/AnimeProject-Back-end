package com.vladislavskiy.Anime.utils;

import com.vladislavskiy.Anime.dto.TyanCredentialsDto;
import com.vladislavskiy.Anime.models.Tyan;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class TyanUtill {
    final private DataSource dataSource;
    final private Connection con;

    public TyanUtill(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        this.con = dataSource.getConnection();
    }

    public ResponseEntity getAllTyans() throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT * from tyans")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return ResponseEntity.of(Optional.of(getTyans(resultSet)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity getTyanById(Integer id) {
        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT * from tyans where id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return ResponseEntity.of(Optional.of(getTyans(resultSet)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Tyan> getTyans(ResultSet resultSet) throws SQLException {
        List<Tyan> tyans = new ArrayList<>();
        while (resultSet.next()) {
            Tyan tyan = new Tyan();
            tyan.setId(resultSet.getInt("id"));
            tyan.setName(resultSet.getString("name"));
            tyan.setSurname(resultSet.getString("surname"));
            tyan.setIQ(resultSet.getInt("IQ"));
            tyans.add(tyan);
        }
        return tyans;
    }

    public Integer countTyanIQ(TyanCredentialsDto tyanCredentialsDto) {
        log.info("COUNT: " + (tyanCredentialsDto.id() + tyanCredentialsDto.name().length() + tyanCredentialsDto.surname().length()));
        return tyanCredentialsDto.id() + tyanCredentialsDto.name().length() + tyanCredentialsDto.surname().length();
    }

    public ResponseEntity saveTyan(Tyan tyan) {
        try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO tyans VALUES (?,?,?,?)")) {
            preparedStatement.setInt(1, tyan.getId());
            preparedStatement.setString(2, tyan.getName());
            preparedStatement.setString(3, tyan.getSurname());
            preparedStatement.setInt(4, tyan.getIQ());
            int i = preparedStatement.executeUpdate();
            if (i > 0)
                return ResponseEntity.ok(tyan);
            else return (ResponseEntity) ResponseEntity.noContent();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity updateTyanByCredentials(TyanCredentialsDto tyanCredentialsDto) {
        try (PreparedStatement preparedStatement = con.prepareStatement("update tyans set name = ?, surname = ? where id = ?")) {
            preparedStatement.setString(1, tyanCredentialsDto.name());
            preparedStatement.setString(2, tyanCredentialsDto.surname());
            preparedStatement.setInt(3, tyanCredentialsDto.id());
            int i = preparedStatement.executeUpdate();
            if (i > 0)
                return ResponseEntity.ok(tyanCredentialsDto);
            else return (ResponseEntity) ResponseEntity.noContent();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}