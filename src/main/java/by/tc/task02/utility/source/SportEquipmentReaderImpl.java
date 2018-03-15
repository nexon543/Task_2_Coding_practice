package by.tc.task02.utility.source;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SportEquipmentReaderImpl implements SportEquipmentReader {

    private SourceNameReader sourceNameReader;
    private String filePath;
    private BufferedReader fileReader;

    public SportEquipmentReaderImpl() throws IOException {
        sourceNameReader = new SourceNameReader();
        filePath = sourceNameReader.readSportEquipmentFilePath();
        fileReader = new BufferedReader(new FileReader(filePath));
    }

    public String read() throws IOException {
        return fileReader.readLine();
    }

    public void close() throws IOException {
        fileReader.close();

    }

}