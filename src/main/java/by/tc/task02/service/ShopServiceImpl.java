package by.tc.task02.service;

import by.tc.task02.dao.SportEquipmentDAO;
import by.tc.task02.dao.exception.DAOException;
import by.tc.task02.dao.impl.SportEquipmentDAOImpl;
import by.tc.task02.model.RentUnit;
import by.tc.task02.model.Shop;
import by.tc.task02.model.SportEquipment;
import by.tc.task02.model.SportEquipmentType;
import by.tc.task02.model.criteria.Criteria;
import by.tc.task02.model.criteria.CriteriaConstants;
import by.tc.task02.model.criteria.SearchCriteria;
import by.tc.task02.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

public class ShopServiceImpl implements ShopService {

    private Shop shop;
    private RentUnit rentUnit;
    private SportEquipmentDAO dao;

    public ShopServiceImpl() throws ServiceException {
        dao = new SportEquipmentDAOImpl();
        shop = new Shop();
    }

    @Override
    public List<SportEquipment> getRentedSportEquipments() throws ServiceException {
        return rentUnit.getUnits();
    }

    public List<SportEquipment> setRenter(String renter) throws ServiceException {
        try {
            Criteria criteria = new Criteria();
            criteria.add(SearchCriteria.RENTER, renter);
            List<SportEquipment> sportEquipments = dao.find(criteria);
            rentUnit.setUnits(sportEquipments);
            return sportEquipments;
        } catch (DAOException ex) {
            throw new ServiceException(ServiceException.FILE_NAME_ERROR);
        }
    }

    @Override
    public List<SportEquipment> getAllRented() throws ServiceException {

        return null;
    }

    @Override
    public List<SportEquipment> getAvailable(SportEquipmentType sportEquipmentType) throws ServiceException {
        try {
            Criteria criteria = new Criteria();
            criteria.add(SearchCriteria.TYPE, sportEquipmentType);
            criteria.add(SearchCriteria.RENTER, CriteriaConstants.NO_RENTER);
            List<SportEquipment> sportEquipments = dao.find(criteria);
            return sportEquipments;
        } catch (DAOException ex) {
            throw new ServiceException(ServiceException.FILE_NAME_ERROR);
        }

    }

    @Override
    public List<SportEquipment> getAllAvailable() throws ServiceException {
        try {
            shop.setAvailable(dao.getAvailable());
        } catch (DAOException ex) {
            throw new ServiceException(ServiceException.FILE_NAME_ERROR);
        }
        return shop.getAvailable();
    }

    @Override
    public Map<SportEquipmentType, Integer> getAllAvailableCount() {
        return shop.getAvailableCount();
    }

    @Override
    public void saveData() {

    }

    @Override
    public boolean rentSportEquipment(SportEquipment sportEquipment) throws ServiceException {
        
        return false;
    }

}
