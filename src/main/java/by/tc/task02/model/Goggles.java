package by.tc.task02.model;

import java.util.Objects;

public class Goggles extends SportEquipment {
    String material;

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Goggles goggles = (Goggles) o;
        return Objects.equals(material, goggles.material);
    }

    @Override
    public String toString() {
        return super.toString()+" Goggles{" +
                "material='" + material + '\'' +
                '}';
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), material);
    }
}
