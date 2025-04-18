package config;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    public static final Properties properties = new Properties();

    static {
        try (InputStream input = PropertiesLoader.class.getClassLoader()
                .getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new IllegalStateException("Config file not found!");
            }
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load configs", e);
        }
    }

    public static String getProperties(String key) {
        return properties.getProperty(key);
    }

}
