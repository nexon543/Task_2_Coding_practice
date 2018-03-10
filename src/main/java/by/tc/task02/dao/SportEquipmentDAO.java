package by.tc.task02.dao;

import by.tc.task02.dao.exception.DAOException;
import by.tc.task02.model.SportEquipment;
import by.tc.task02.model.SportEquipmentType;
import by.tc.task02.model.criteria.Criteria;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface SportEquipmentDAO {
    Map<SportEquipmentType, Integer> getAllAvailableCount() throws DAOException;
    List<SportEquipment> find(Criteria criteria) throws DAOException;
    boolean updateFirst (Criteria criteriaOfOldRecord, SportEquipment updatedRecord) throws DAOException;
}
