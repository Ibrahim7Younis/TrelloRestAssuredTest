package com.trello.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static ConfigManager instance;
    private final Properties properties = new Properties();
    private static final String DEFAULT_CONFIG = "auth.properties";

    // Private constructor - singleton
    private ConfigManager() {
        loadProperties(DEFAULT_CONFIG);
    }

    // Thread-safe singleton
    public static ConfigManager getInstance() {
        if (instance == null) {
            synchronized (ConfigManager.class) {
                if (instance == null) {
                    instance = new ConfigManager();
                }
            }
        }
        return instance;
    }

    private void loadProperties(String fileName) {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new RuntimeException("Config file not found: " + fileName);
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file: " + fileName, e);
        }
    }

    // Generic getter
    public String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property not found: " + key);
        }
        return value.trim();
    }

    // Convenience getters
    public String getConsumerKey()    { return get("consumerKey"); }
    public String getConsumerSecret() { return get("consumerSecret"); }
    public String getAccessToken()    { return get("accessToken"); }
    public String getTokenSecret()    { return get("tokenSecret"); }
}
