package org.example.handler;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Класс RepeatWordTrackerTest проверяет работу метода found() класса RepeatWordTracker
 * отвечающего за подсчёт статистики уникальных слов в HTML документе
 * */

public class RepeatWordTrackerTest {

    @Test
    public void loadHTML() throws URISyntaxException, IOException {
        URL url = RepeatWordTrackerTest.class.getClassLoader()
                .getResource("testHTML.html");

        List<String> list = Files.readAllLines(Paths.get(url.toURI()), StandardCharsets.UTF_8);
        String html = list.toString();
        List<String> wordList = RepeatWordTracker.found(html);
        String result = wordList.toString();
        String sample = "[МОСКВЕ - 1, МАКЕТ - 1, ВДНХ - 1, ПЯТЫЙ - 1, ВОЗДВИГНУТЫ - 1, МУЗЕЙ - 1," +
                " ИСПОЛЬЗОВАН - 1, ГОДУ - 1, СЧЕТУ - 1, В - 2, НА - 1, И - 1, ПРЕДЫДУЩИЕ - 1," +
                " ПО - 1, МЕДИА - 1, Р - 2, МОНУМЕНТЫ - 1, SEARCH - 1, ПОЛНОРАЗМЕРНЫЙ - 1," +
                " САМАРА - 1, НОСИТЕЛЮ - 2, QUOT - 2, КОСМИЧЕСКАЯ - 1, РАКЕТЕ - 2, ЭТО - 1," +
                " ПАМЯТНИК - 1, ПАМЯТНИКИ - 1, БЫЛИ - 1, ШКОЛА - 1]";

        result.equals(sample);
    }







}