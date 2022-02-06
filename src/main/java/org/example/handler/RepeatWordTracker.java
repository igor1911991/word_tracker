package org.example.handler;

import org.example.dao.WordListDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс RepeatWordTracker выполняет задачу подсчёта статистики уникальных слов в HTML документе
 * */

@Component
public class RepeatWordTracker {

    private static final Logger logger = LoggerFactory.getLogger(WordListDao.class);

    public static List<String> readFromWeb(String webURL)  throws IOException{

        URL url = new URL(webURL);
        StringBuilder webPage = new StringBuilder();
        try(InputStream is = url.openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                webPage.append(line);
            }
        }
        catch (MalformedURLException ex) {
            logger.error("", ex);
            ex.printStackTrace();
            throw new MalformedURLException("URL is malformed!!");
        }
        catch (IOException ex) {
            logger.error("", ex);
            ex.printStackTrace();
            throw new IOException();
        }
        String webPageLine = webPage.toString();

        List<String> result = found(webPageLine);

        return result;
    }

    public static List<String> found(String webPaigeLine) {

        List<String> resultList;
        Map<String, Integer> map;

        StringBuilder text = new StringBuilder();

            Pattern p = Pattern.compile("<(p|h(1|2|3|4|5|6)|title|div|a|li)((\\s.*?>)+)(.|\\s)*?<"); //паттерн находит тэги содержащие текст
            Matcher m = p.matcher(webPaigeLine);
            while(m.find()){
                text.append(m.group());
            }



        String textUpperCase = text.toString().toUpperCase();
        String textWithSpace = textUpperCase.replaceAll("(<.+?>|[^A-Za-zА-Яа-я\\x{0401}\\x{0451}])", " ").trim(); // замена небуквенных символов на пробелы
        String[] words = textWithSpace.split("\\s+");
        map = new HashMap<>();
        resultList = new ArrayList<>();
        for(String str: words) {
            if(!map.containsKey(str)){
                map.put(str, 1);
            } else {
                Integer v = (map.get(str) + 1);
                map.put(str, v);
            }
        }
        for(Map.Entry<String, Integer> mapResult: map.entrySet()){
            resultList.add(mapResult.getKey() + " - " + mapResult.getValue());
        }

        return resultList;
    }


}
