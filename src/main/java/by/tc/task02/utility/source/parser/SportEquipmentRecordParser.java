package by.tc.task02.utility.source.parser;

import by.tc.task02.model.SportEquipment;

import java.util.Map;

public interface SportEquipmentRecordParser {
    public Map<String, String> parse(String record);
    public String createRecord(SportEquipment sportEquipment);
}
