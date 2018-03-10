package by.tc.task02.service;

import by.tc.task02.model.SportEquipment;
import by.tc.task02.model.SportEquipmentType;
import by.tc.task02.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface ShopService {
    List<SportEquipment> setRenter(String renter) throws ServiceException;

    List<SportEquipment> getRentedSportEquipments() throws ServiceException;

    List<SportEquipment> getAllRented() throws ServiceException;

    List<SportEquipment> getAllAvailable() throws ServiceException;

    Map<SportEquipmentType, Integer> getAllAvailableCount();

    List<SportEquipment> getAvailableOfType(SportEquipmentType sportEquipmentType) throws ServiceException;

    boolean rentSportEquipment(SportEquipment sportEquipment) throws ServiceException;
}
