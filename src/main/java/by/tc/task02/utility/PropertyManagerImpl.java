package main.java.by.tc.task02.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManagerImpl implements PropertyManager, AutoCloseable {
    InputStream input;
    private String propertiesFilePath = "config.properties";
    private Properties appProperties = new Properties();

    private void readPropertyFile() throws IOException {
        if (input == null) {
            input = new FileInputStream(propertiesFilePath);
        }
        appProperties.load(input);
    }

    public String getProperty(String key) {
        return appProperties.getProperty(key);
    }

    public void setProperty(String key, String value) {
        appProperties.setProperty(key, value);
    }

    @Override
    public void setPropertiesSourceFile(String propertiesFilePath) throws IOException {
        if (input != null) {
            input.close();
        }
        this.propertiesFilePath = propertiesFilePath;
        readPropertyFile();

    }

    @Override
    public void close() throws Exception {
        input.close();

    }


}
