package by.tc.task02.service.factory;

import by.tc.task02.model.Category;
import by.tc.task02.model.Goggles;
import by.tc.task02.model.SportEquipment;
import by.tc.task02.model.criteria.SearchCriteria;

import java.util.Map;

public class GogglesFactory extends AbstractSportEquipmentFactory {

    private Goggles goggles;
    @Override
    public SportEquipment getSportEquipment(Map<String, String> properties) {
        goggles=new Goggles();
        sportEquipment=goggles;
        initFieldsByProperties(properties);
        return sportEquipment;
    }

    @Override
    protected void setsSportEquipmentField(String propertyName, String propertyValue) {
        SearchCriteria fieldName=SearchCriteria.valueOf(propertyName.toUpperCase());
        switch (fieldName){
            case CATEGORY:
                goggles.setCategory(Category.valueOf(propertyValue.toUpperCase()));
                break;
            case PRICE:
                goggles.setPrice(getInt(propertyValue));
                break;
            case TITLE:
                goggles.setTitle(propertyValue);
                break;
            case RENTER:
                goggles.setRenter(propertyValue);
                break;
            case MATERIAL:
                goggles.setMaterial(propertyValue);
                break;
        }

    }
}
