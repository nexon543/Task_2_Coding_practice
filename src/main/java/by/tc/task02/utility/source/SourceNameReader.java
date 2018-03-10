package by.tc.task02.utility.source;

import java.util.ResourceBundle;

public class SourceNameReader {

    private ResourceBundle properties;

    public SourceNameReader() {
    }

    public String readSportEquipmentFilePath() {
        properties = ResourceBundle.getBundle(DBParameter.CONFIG_FILE_PATH);
        String sportEquipmentFilePath = properties.getString(DBParameter.SPORT_EQUIPMENT_PATH_KEY);
        return sportEquipmentFilePath;
    }
}