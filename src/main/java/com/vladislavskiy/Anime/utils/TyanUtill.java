package com.vladislavskiy.Anime.utils;

import com.vladislavskiy.Anime.models.Tyan;
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
public class TyanUtill {
    final private DataSource dataSource;
    final private Connection con;

    public TyanUtill(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        this.con = dataSource.getConnection();
    }
    public ResponseEntity getAllTyans() throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement("SELECT * from tyans");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Tyan> tyans = new ArrayList<>();
        while (resultSet.next()){
            Tyan tyan = new Tyan();
            tyan.setId(resultSet.getInt("id"));
            tyan.setName(resultSet.getString("name"));
            tyan.setSurname(resultSet.getString("surname"));
            tyans.add(tyan);
        }
        return ResponseEntity.of(Optional.of(tyans));
    }
}
