package com.chang.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private Properties properties;
    private static ConfigManager configManager;

    public ConfigManager() {
        String filepath = "database.properties";
        InputStream is = ConfigManager.class.getClassLoader().getResourceAsStream(filepath);
        properties = new Properties();

        try {
            properties.load(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized ConfigManager getInstance(){
        return configManager == null ? new ConfigManager() : configManager;
    }

    public String getString(String key){
        return properties.getProperty(key);
    }
}
