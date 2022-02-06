package org.example.dao;

import org.example.model.WordList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Класс WordListDao работает с базой данных
 * */

@Component
public class WordListDao {

    private static final Logger logger = LoggerFactory.getLogger(WordListDao.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WordListDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public int[] saveWordList(final WordList wordList) {

        return this.jdbcTemplate.batchUpdate(
                "insert into words (url, word) values(?,?)",
                new BatchPreparedStatementSetter() {

                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, wordList.getUrl());
                        ps.setString(2, wordList.getList().get(i));
                    }

                    public int getBatchSize() {

                        logger.info("SAVE WORD LIST");
                        return wordList.getList().size();
                    }

                });
    }

}
