package com.martyworm.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {

    private static PropertiesManager instance = null;

    public static PropertiesManager getInstance(){
        if(instance == null){
            instance = new PropertiesManager();
        }

        return instance;
    }

    Properties properties = new Properties();

    private PropertiesManager(){
        loadProperties();
    }

    private void loadProperties(){
        InputStream input = null;
        try {

            input = new FileInputStream(ClassLoader.getSystemClassLoader()
                    .getResource("properties/config.properties").getFile());
            properties.load(input);
        } catch (IOException ex) {
            System.out.println("Unble to load config properties file: " + ex.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean getProperty(String propertyName){
        Boolean value = Boolean.parseBoolean(properties.getProperty(propertyName));
        if(value == null){
            return false;
        }

        return value;
    }

}


