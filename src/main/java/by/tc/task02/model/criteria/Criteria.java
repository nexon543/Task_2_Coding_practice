package by.tc.task02.model.criteria;

import java.util.HashMap;
import java.util.Map;

public class Criteria<E> {
    public static final int MORE = -1;
    public static final int LESS = 1;
    public static final int EQUALS = 0;
    //Optional
    private String sportEquipmentTitle;
    private Map<E, Object> criteria = new HashMap<E, Object>();
    private Map<E, Integer> compareCondition = new HashMap<E, Integer>();

    public void add(E searchCriteria, Object value) {
        criteria.put(searchCriteria, value);
    }

    public void add(E searchCriteria, Object value, int compareCondition) {
        criteria.put(searchCriteria, value);
        addCompareCondition(searchCriteria, compareCondition);
    }

    public String getSportEquipmentTitle() {
        return sportEquipmentTitle;
    }

    public void setApplianceType(String sportEquipmentTitle) {
        this.sportEquipmentTitle = sportEquipmentTitle;
    }

    public void addCompareCondition(E criteria, int compareConditionValue) {
        compareCondition.put(criteria, compareConditionValue);
    }

    public int getCompareCondition(E criteria) {
        Integer result = compareCondition.get(criteria);
        if (result == null)
            return 0;
        return result;
    }

    public Map<E, Object> getCriteria() {
        return criteria;
    }
}
