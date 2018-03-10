package by.tc.task02.utility.source;

import java.io.IOException;

public interface SportEquipmentReaderWriter extends AutoCloseable {

    void setFilePath(String filePath);
    String getFilePath();
    void setSourceNameFromProperties();
    String read() throws IOException;
    //void write (String line)throws IOException;
}
