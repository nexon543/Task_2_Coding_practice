package by.tc.task02.service.factory;

import by.tc.task02.model.SportEquipment;
import by.tc.task02.model.SportEquipmentType;
import by.tc.task02.model.criteria.CriteriaConstants;

import java.util.EnumMap;
import java.util.Map;

public class SportEquipmentFactoryClient {

    private Map<SportEquipmentType, AbstractSportEquipmentFactory> factories;

    public SportEquipmentFactoryClient(){
        factories=new EnumMap<>(SportEquipmentType.class);
        factories.put(SportEquipmentType.GOGGLES, new GogglesFactory());
    }

    public SportEquipment getSportEquipment(Map<String, String> properties){
        AbstractSportEquipmentFactory factory=factories.get(SportEquipmentType.
                valueOf(properties.get(CriteriaConstants.SPORT_EQUIPMENT_TYPE).toUpperCase()));
        return factory.getSportEquipment(properties);
    }

}
