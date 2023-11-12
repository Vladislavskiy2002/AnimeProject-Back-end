package com.vladislavskiy.Anime.services.Impl;

import com.vladislavskiy.Anime.dto.TyanCredentialsDto;
import com.vladislavskiy.Anime.models.Tyan;
import com.vladislavskiy.Anime.services.TyanService;
import com.vladislavskiy.Anime.utils.TyanUtill;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
@Service
@Transactional
public class TyanServiceImpl implements TyanService {
    final private TyanUtill tyanUtill;
    public TyanServiceImpl(TyanUtill tyanUtill) {
        this.tyanUtill = tyanUtill;
    }
    @Override
    public ResponseEntity getAllTyans() throws SQLException {
        return tyanUtill.getAllTyans();
    }
    @Override
    public ResponseEntity getTyanById(Integer id) {
        return tyanUtill.getTyanById(id);
    }

    @Override
    public ResponseEntity addTyanByCredentials(TyanCredentialsDto tyanCredentialsDto) {
        return tyanUtill.saveTyan( new Tyan(tyanCredentialsDto.id(),tyanCredentialsDto.name(),tyanCredentialsDto.surname(),
                tyanUtill.countTyanIQ(tyanCredentialsDto)));
    }
    @Override
    public ResponseEntity updateTyanByCredentials(TyanCredentialsDto tyanCredentialsDto) {
        return tyanUtill.updateTyanByCredentials(tyanCredentialsDto);
    }
}
