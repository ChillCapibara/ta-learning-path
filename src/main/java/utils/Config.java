package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Config {
    private static final Properties props = new Properties();

    static {
        try (InputStream is = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (is == null){
                throw new RuntimeException("config.properties not found in classpath!");
            }
            props.load(is);
        } catch (IOException e){
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String get(String key){
        //Setup for CI in the future, System properties override config file
        String sys = System.getProperty(key);
        return sys != null ? sys : props.getProperty(key);
    }

    public static Integer getInt(String key){
        return Integer.parseInt(get(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }
}
