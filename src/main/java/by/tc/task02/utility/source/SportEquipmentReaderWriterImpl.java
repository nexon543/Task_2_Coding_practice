package by.tc.task02.utility.source;

import java.io.*;

public class SportEquipmentReaderWriterImpl implements SportEquipmentReaderWriter {

    private String filePath;
    private BufferedReader fileReader;
    //private BufferedWriter fileWriter;

    public SportEquipmentReaderWriterImpl() throws IOException {
        setSourceNameFromProperties();
        openConnection();
    }

    private void openConnection() throws IOException {
        FileReader fileReader = new FileReader(filePath);
        //FileWriter fileWriter = new FileWriter(filePath);
        this.fileReader = new BufferedReader(fileReader);
        //this.fileWriter = new BufferedWriter(fileWriter);
    }

    public String read() throws IOException {
        return fileReader.readLine();
    }

    /*@Override
    public void write(String line) throws IOException {
        fileWriter.write(line);
    }*/

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setSourceNameFromProperties() {
        SourceNameReader sourceNameReader = new SourceNameReader();
        filePath = sourceNameReader.readSportEquipmentFilePath();
    }

    public void close() throws IOException {
        fileReader.close();
        //fileWriter.close();
    }

    public String getFilePath() {
        return filePath;
    }
}