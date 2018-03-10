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
import by.tc.task02.service.validation.CriteriaChecker;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShopServiceImpl implements ShopService {

    private String renter;
    private Shop shop;
    private RentUnit rentUnitForUser;
    private SportEquipmentDAO dao;

    public ShopServiceImpl() throws ServiceException {
        shop = new Shop();
        try {
            dao = new SportEquipmentDAOImpl();
            shop.setAvailableCount(dao.getAllAvailableCount());
        } catch (DAOException ex) {
            throw new ServiceException(ServiceException.FILE_NAME_ERROR);
        }
        renter = CriteriaConstants.NO_RENTER;
        rentUnitForUser=new RentUnit();
        shop.setAvailable(loadAllAvailable());
    }

    @Override
    public List<SportEquipment> getRentedSportEquipments() throws ServiceException {
        return rentUnitForUser.getUnits();
    }

    public List<SportEquipment> setRenter(String renter) throws ServiceException {
        try {
            this.renter=renter;
            Criteria criteria = new Criteria();
            criteria.add(SearchCriteria.RENTER, renter);
            List<SportEquipment> sportEquipments = dao.find(criteria);
            rentUnitForUser.setUnits(sportEquipments);
            return sportEquipments;
        } catch (DAOException ex) {
            throw new ServiceException(ServiceException.FILE_NAME_ERROR);
        }
    }

    @Override
    public List<SportEquipment> getAllRented() throws ServiceException {
        try {
            List<SportEquipment> sportEquipments;
            Criteria criteria = new Criteria();
            criteria.add(SearchCriteria.RENTER, CriteriaConstants.NO_RENTER);
            criteria.isCriteriaReversed();
            sportEquipments = dao.find(criteria);
            return sportEquipments;
        } catch (DAOException ex) {
            throw new ServiceException(ServiceException.FILE_NAME_ERROR);
        }
    }

    @Override
    public List<SportEquipment> getAvailableOfType(SportEquipmentType sportEquipmentType) throws ServiceException {

        Criteria criteria = new Criteria();
        criteria.add(SearchCriteria.TYPE, sportEquipmentType);
        criteria.add(SearchCriteria.RENTER, CriteriaConstants.NO_RENTER);
        List<SportEquipment> sportEquipments = shop.getAvailable();
        sportEquipments.stream().filter(o-> {
           boolean isTypeCorrect=o.getClass().getSimpleName().toUpperCase().equals(SearchCriteria.TYPE.toString());
           boolean isNoRenter=o.getRenter().equals(CriteriaConstants.NO_RENTER);
           return isNoRenter&&isTypeCorrect;
        }).collect(Collectors.toList());
        return sportEquipments;

    }

    @Override
    public List<SportEquipment> getAllAvailable() throws ServiceException {
        return shop.getAvailable();
    }

    private List<SportEquipment> loadAllAvailable() throws ServiceException {
        try {
            Criteria criteria = new Criteria();
            criteria.add(SearchCriteria.RENTER, CriteriaConstants.NO_RENTER);
            List<SportEquipment> sportEquipments = dao.find(criteria);
            shop.setAvailable(sportEquipments);
            return sportEquipments;
        } catch (DAOException ex) {
            throw new ServiceException(ServiceException.FILE_NAME_ERROR);
        }
    }
    @Override
    public Map<SportEquipmentType, Integer> getAllAvailableCount() {
        return shop.getAvailableCount();
    }

    @Override
    public boolean rentSportEquipment(SportEquipment sportEquipment) throws ServiceException {
        if (renter.equals(CriteriaConstants.NO_RENTER)) {
            throw new ServiceException(ServiceException.RENTER_ERROR);
        }
        if (shop.removeAvailable(sportEquipment)) {
            Criteria criteria = new Criteria();
            criteria.add(SearchCriteria.TITLE, sportEquipment.getTitle());
            criteria.add(SearchCriteria.PRICE, sportEquipment.getPrice());
            criteria.add(SearchCriteria.RENTER, CriteriaConstants.NO_RENTER);
            sportEquipment.setRenter(renter);
            try {
                dao.updateFirst(criteria, sportEquipment);
            } catch (DAOException e) {
                throw new ServiceException(ServiceException.FILE_NAME_ERROR);
            }
            rentUnitForUser.addUnit(sportEquipment);
            return true;
        }
        return false;
    }

}
