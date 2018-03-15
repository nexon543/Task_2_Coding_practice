package by.tc.task02.utility.source;

import java.io.IOException;

public interface SportEquipmentReader extends AutoCloseable {

    String read() throws IOException;

}
