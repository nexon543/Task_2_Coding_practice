package by.tc.task02.model;

import java.util.Objects;

public class SkipRope extends SportEquipment {
    int length;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SkipRope skipRope = (SkipRope) o;
        return length == skipRope.length;
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), length);
    }

    @Override
    public String toString() {
        return "SkipRope{" +
                "length=" + length +
                "} " + super.toString();
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
