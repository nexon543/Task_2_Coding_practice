package by.tc.task02.dao;

import by.tc.task02.dao.exception.DAOException;
import by.tc.task02.dao.impl.SportEquipmentDAOImpl;

public class DAOFactory {
    private static final DAOFactory daoFactory;
     static {
         try {
             daoFactory=new DAOFactory();
         } catch (Exception ex) {
             throw new ExceptionInInitializerError(ex);
         }
     }

     public DAOFactory getInstance (){
         return daoFactory;
     }
     private SportEquipmentDAO sportEquipmentDAO;
     public DAOFactory() throws DAOException {
         sportEquipmentDAO=new SportEquipmentDAOImpl();
     }
     public SportEquipmentDAO getSportEquipmentDAO(){
         return sportEquipmentDAO;
     }
}
