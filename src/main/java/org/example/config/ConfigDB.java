package org.example.config;

import org.example.dao.WordListDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;


public class ConfigDB {

    private static final Logger logger = LoggerFactory.getLogger(WordListDao.class);

    public static final String DB_URL = "db.url";
    public static final String DB_LOGIN = "db.login";
    public static final String DB_PASSWORD = "db.password";


    private static Properties properties = new Properties();

    public synchronized static String getProperty(String name) {
        if (properties.isEmpty()) {
            try (InputStream is = ConfigDB.class.getClassLoader()
                    .getResourceAsStream("dbConfig.properties")) {

                properties.load(is);

            } catch (Exception ex) {
                logger.error("", ex);
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
        return properties.getProperty(name);
    }
}
