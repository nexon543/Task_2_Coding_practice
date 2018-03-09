package by.tc.task02.service.factory;

import by.tc.task02.model.SportEquipment;

import java.util.Iterator;
import java.util.Map;

public abstract class AbstractSportEquipmentFactory {

    SportEquipment sportEquipment;

    protected void initFieldsByProperties(Map<String, String> sportEquipmentProperties) {

        Iterator<Map.Entry<String, String>> propertyEntries = sportEquipmentProperties.entrySet().iterator();

        while (propertyEntries.hasNext()) {
            Map.Entry<String, String> property = propertyEntries.next();
            setsSportEquipmentField(property.getKey(), property.getValue());
        }
    }

    protected float getFloat(String value) {
        return Float.parseFloat(value);
    }

    protected int getInt(String value) {
        return Integer.parseInt(value);
    }

    public abstract SportEquipment getSportEquipment(Map<String, String> properties);

    protected abstract void setsSportEquipmentField(String propertyName, String propertyValue);
}
