package by.tc.task02.model.criteria;

import java.util.HashMap;
import java.util.Map;

public class Criteria {
    public static final int MORE = -1;
    public static final int LESS = 1;
    public static final int EQUALS = 0;
    //Optional
    private String sportEquipmentTitle;
    private Map<SearchCriteria, Object> criteria = new HashMap<SearchCriteria, Object>();
    private Map<SearchCriteria, Integer> compareCondition = new HashMap<SearchCriteria, Integer>();

    public void add(SearchCriteria searchCriteria, Object value) {
        criteria.put(searchCriteria, value);
    }

    public void add(SearchCriteria searchCriteria, Object value, int compareCondition) {
        criteria.put(searchCriteria, value);
        addCompareCondition(searchCriteria, compareCondition);
    }

    public String getSportEquipmentTitle() {
        return sportEquipmentTitle;
    }

    public void setApplianceType(String sportEquipmentTitle) {
        this.sportEquipmentTitle = sportEquipmentTitle;
    }

    public void addCompareCondition(SearchCriteria criteria, int compareConditionValue) {
        compareCondition.put(criteria, compareConditionValue);
    }

    public int getCompareCondition(SearchCriteria criteria) {
        Integer result = compareCondition.get(criteria);
        if (result == null)
            return 0;
        return result;
    }

    public Map<SearchCriteria, Object> getCriteria() {
        return criteria;
    }
}
