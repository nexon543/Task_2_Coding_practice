package by.tc.task02.utility.source;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SportEquipmentWriterImpl implements SportEquipmentWriter {

    private BufferedWriter fileWriter;

    public SportEquipmentWriterImpl (String filePath) throws IOException {
        File tmpFile = new File(filePath);
        if (!tmpFile.exists()) {
                tmpFile.createNewFile();
        }
        fileWriter=new BufferedWriter(new FileWriter(tmpFile));
    }

    @Override
    public void write(String line) throws IOException {
        fileWriter.write(line);
    }

    @Override
    public void close() throws Exception {
        fileWriter.close();
    }
}
