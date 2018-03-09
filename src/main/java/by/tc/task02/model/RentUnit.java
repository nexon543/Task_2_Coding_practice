package by.tc.task02.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RentUnit {
    private List<SportEquipment> units;


    public RentUnit() {
        units = new ArrayList<>();
    }

    public List<SportEquipment> getUnits() {
        return units;
    }

    public void setUnits(List<SportEquipment> units) {
        this.units = units;
    }

    public boolean addUnit(SportEquipment sportEquipment) {
        units.add(sportEquipment);
        return false;
    }


    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentUnit rentUnit = (RentUnit) o;
        return Objects.equals(units, rentUnit.units);
    }

    @Override
    public int hashCode() {

        return Objects.hash(units);
    }

}
