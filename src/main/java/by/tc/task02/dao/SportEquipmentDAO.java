package by.tc.task02.dao;

import by.tc.task02.dao.exception.DAOException;
import by.tc.task02.model.SportEquipment;
import by.tc.task02.model.criteria.Criteria;

import java.util.List;

public interface SportEquipmentDAO {
    List<SportEquipment> getAvailable() throws DAOException;
    List<SportEquipment> find(Criteria criteria) throws DAOException;
    void persist(List<SportEquipment> sportEquipments);
}
