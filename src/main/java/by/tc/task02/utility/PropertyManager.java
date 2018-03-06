package main.java.by.tc.task02.utility;

import java.io.IOException;

public interface PropertyManager {

    public void setProperty(String key, String value);

    public String getProperty(String key);

    public void setPropertiesSourceFile(String propertiesFilePath) throws IOException;

}
