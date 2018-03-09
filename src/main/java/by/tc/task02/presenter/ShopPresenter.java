package by.tc.task02.presenter;

import by.tc.task02.model.SportEquipmentType;
import by.tc.task02.service.exception.ServiceException;

public interface ShopPresenter {
    void initUser();

    int showAvailableSportEquipment() throws ServiceException;

    int showSportEquipmentOfType(SportEquipmentType sportEquipmentType) throws ServiceException;

    void rentSportEquipment();

}
