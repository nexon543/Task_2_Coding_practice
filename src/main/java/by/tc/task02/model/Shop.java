package by.tc.task02.model;

import java.util.*;

public class Shop extends SportEquipment {
    private List<SportEquipment> available;
    private Map<SportEquipmentType, Integer> availableCount;

    public Shop(){
        available=new ArrayList<>();
        availableCount=new EnumMap<SportEquipmentType, Integer>(SportEquipmentType.class);
    }

    public void addAvailable(SportEquipment se){
        available.add(se);
        SportEquipmentType type=SportEquipmentType.valueOf(se.getClass().getSimpleName().toUpperCase());
        Integer count = availableCount.get(type);
        availableCount.put(type, count++);
    }

    public List<SportEquipment> getAvailable() {
        return available;
    }

    public void setAvailable(List<SportEquipment> available) {
        this.available = available;
        for (SportEquipment sportEquipment:available){
            SportEquipmentType type=SportEquipmentType.valueOf(sportEquipment.getClass().getSimpleName().toUpperCase());
            Integer count=availableCount.get(type);
            if (count!=null) {
                availableCount.put(type, count++);
            }
            else {
                availableCount.put(type, 0);
            }
        }
    }

    public Map<SportEquipmentType, Integer> getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(Map<SportEquipmentType, Integer> availableCount) {
        this.availableCount = availableCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Shop shop = (Shop) o;
        return Objects.equals(available, shop.available) &&
                Objects.equals(availableCount, shop.availableCount);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), available, availableCount);
    }

    @Override
    public String toString() {
        return "Shop{" +
                "available=" + available +
                ", availableCount=" + availableCount +
                "} " + super.toString();
    }
}
