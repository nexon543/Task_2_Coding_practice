package by.tc.task02.utility.source;

import java.io.IOException;

public interface SportEquipmentWriter extends AutoCloseable {
    void write(String line) throws IOException;
}
