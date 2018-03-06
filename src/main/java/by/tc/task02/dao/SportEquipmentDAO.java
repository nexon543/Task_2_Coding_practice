package by.tc.task02.dao;

import main.java.by.tc.task02.model.SportEquipment;

import java.util.List;

public interface SportEquipmentDAO {
    List<SportEquipment> findAll();

    List<SportEquipment> find();

    void save(List<SportEquipment> sportEquipments);
}
