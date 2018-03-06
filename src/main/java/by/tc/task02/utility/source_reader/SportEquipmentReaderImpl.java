package by.tc.task02.utility.source_reader;

import by.tc.task02.utility.source_reader.SourceNameReader;
import by.tc.task02.utility.source_reader.SportEquipmentReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SportEquipmentReaderImpl implements SportEquipmentReader {

    private String filePath;
    private BufferedReader fileReader;


    @Override
    public void openConnection() throws FileNotFoundException {
        FileReader fileReader = new FileReader(filePath);
        this.fileReader = new BufferedReader(fileReader);
    }

    public String read() throws IOException {
        return fileReader.readLine();
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setSourceNameFromProperties() {
        SourceNameReader sourceNameReader = new SourceNameReader();
        filePath = sourceNameReader.readSportEquipmentFilePath();
    }

    public void close() throws IOException {
        fileReader.close();
    }

    public String getFilePath() {
        return filePath;
    }
}