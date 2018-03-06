package by.tc.task02.utility.source_reader;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface SportEquipmentReader extends AutoCloseable {
    void openConnection() throws FileNotFoundException;

    String read() throws IOException;
}
