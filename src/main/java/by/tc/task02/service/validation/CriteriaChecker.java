package by.tc.task02.service.validation;


import by.tc.task02.model.criteria.Criteria;
import by.tc.task02.model.criteria.SearchCriteria;

import java.util.Map;
import java.util.Set;

public class CriteriaChecker {

    //if value String and condition not equals - invalid condition
    private static boolean checkCompareCondition() {
        return true;
    }

    private static boolean checkInt(Integer criteriaValue, int compareCondition, int applianceValue) {
        return criteriaValue.compareTo(applianceValue) == compareCondition;
    }

    private static boolean checkFloat(Float criteriaValue, int compareCondition, float applianceValue) {
        return criteriaValue.compareTo(applianceValue) == compareCondition;
    }

    private static boolean checkString(String criteriaValue, String applianceValue) {
        //TODO: use RegExp
        return criteriaValue.equals(applianceValue);
    }

    private static boolean checkDouble(Double criteriaValue, int compareCondition, double applianceValue) {
        return criteriaValue.compareTo(applianceValue) == compareCondition;
    }

    public <E> boolean check(Map<String, String> sportEquipmentProperties, Criteria criteria) {
        Set<Map.Entry<SearchCriteria, Object>> criteriaSet = criteria.getCriteria().entrySet();
        //iteration through all criterias
        for (Map.Entry<SearchCriteria, Object> criteriaEntry : criteriaSet) {
            SearchCriteria criteriaKey = criteriaEntry.getKey();
            String criteriaName = criteriaKey.toString();
            int compareCondition = criteria.getCompareCondition(criteriaKey);
            Object criteriaValue = criteriaEntry.getValue();
            //checking value of appliance property associated with current criteria by criteriaName
            if (!checkSingleCriteria(criteriaValue, compareCondition, sportEquipmentProperties.get(criteriaName.toLowerCase())))
                return false;
        }
        return true;
    }

    private boolean checkSingleCriteria(Object criteriaValue, int compareCondition, String applianceFieldValue) {
        SportEquipmentFieldDataType valueType = SportEquipmentFieldDataType.valueOf(criteriaValue.getClass().getSimpleName().toUpperCase());
        if (valueType != null) {
            switch (valueType) {
                case INTEGER:
                    int applianceIntFieldValue = Integer.parseInt(applianceFieldValue);
                    return checkInt((Integer) criteriaValue, compareCondition, applianceIntFieldValue);
                case FLOAT:
                    float applianceFloatFieldValue = Float.parseFloat(applianceFieldValue + "f");
                    return checkFloat((Float) criteriaValue, compareCondition, applianceFloatFieldValue);
                case DOUBLE:

                case STRING:
                    return checkString((String) criteriaValue, applianceFieldValue);
                default://TODO: add Logger
            }
        }
        return true;
    }
}
