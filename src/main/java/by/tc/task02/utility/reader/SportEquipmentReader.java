package by.tc.task02.utility.reader;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface SportEquipmentReader extends AutoCloseable {
    void openConnection() throws FileNotFoundException;
    void setFilePath(String filePath);
    void setSourceNameFromProperties();
    String read() throws IOException;
}
