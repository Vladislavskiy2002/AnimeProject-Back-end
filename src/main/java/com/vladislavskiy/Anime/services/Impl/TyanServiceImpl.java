package com.vladislavskiy.Anime.services.Impl;

import com.vladislavskiy.Anime.services.TyanService;
import com.vladislavskiy.Anime.utils.TyanUtill;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
@Service
public class TyanServiceImpl implements TyanService {
    private TyanUtill tyanUtill;

    public TyanServiceImpl(TyanUtill tyanUtill) {
        this.tyanUtill = tyanUtill;
    }

    @Override
    public ResponseEntity getAllTyans() throws SQLException {
        return tyanUtill.getAllTyans();
    }
}
