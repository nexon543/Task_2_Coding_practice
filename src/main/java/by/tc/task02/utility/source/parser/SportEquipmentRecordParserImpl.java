package by.tc.task02.utility.source.parser;

import by.tc.task02.model.Goggles;
import by.tc.task02.model.SportEquipment;
import by.tc.task02.model.SportEquipmentType;

import java.util.HashMap;
import java.util.Map;

public class SportEquipmentRecordParserImpl implements SportEquipmentRecordParser {
    @Override
    public Map<String, String> parse(String record) {
        Map<String, String> sportEquipmentProperties = new HashMap<>();

        String[] properties = record.split(";");
        for (String property : properties) {
            String splitProp[] = property.trim().split(":");
            sportEquipmentProperties.put(splitProp[0], splitProp[1]);
        }
        return sportEquipmentProperties;
    }

    @Override
    public String createRecord(SportEquipment sportEquipment) {
        SportEquipmentType sportEquipmentType = SportEquipmentType.valueOf(sportEquipment.getClass().getSimpleName());
        StringBuilder record = new StringBuilder("title:").append(sportEquipment.getTitle())
                .append("; price:").append(sportEquipment.getPrice())
                .append("; category:").append(sportEquipment.getCategory()).append(";renter:")
                .append(sportEquipment.getRenter());
        switch (sportEquipmentType) {
            case GOGGLES:
                record.append(";material:" + ((Goggles) sportEquipment).getMaterial());
                break;
        }
        return record.toString();
    }
}
