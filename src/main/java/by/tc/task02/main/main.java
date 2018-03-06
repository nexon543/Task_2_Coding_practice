package by.tc.task02.main;

import by.tc.task02.utility.source_reader.SourceNameReader;

public class main {
    public static void main(String[] args) {
        SourceNameReader nameReader = new SourceNameReader();
        System.out.println("Path to database file: " + nameReader.readSportEquipmentFilePath());
    }
}