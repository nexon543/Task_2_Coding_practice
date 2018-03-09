package by.tc.task02.utility.reader.parser;

import java.util.HashMap;
import java.util.Map;

public class SportEquipmentRecordParserImpl implements SportEquipmentRecordParser {
    @Override
    public Map<String, String> parse(String record) {
        Map<String, String> sportEquipmentProperties = new HashMap();

        String[] properties = record.split(";");
        for (String property : properties) {
            String splitProp[] = property.trim().split(":");
            sportEquipmentProperties.put(splitProp[0], splitProp[1]);
        }
        return sportEquipmentProperties;
    }
}
