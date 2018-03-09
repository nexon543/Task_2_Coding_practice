package by.tc.task02.dao;

import by.tc.task02.dao.impl.SportEquipmentDAOImpl;

public class DAOFactory {
    private static final DAOFactory daoFactory;
     static {
         daoFactory=new DAOFactory();
     }

     public DAOFactory getInstance (){
         return daoFactory;
     }
     private SportEquipmentDAO sportEquipmentDAO;
     public DAOFactory(){
         sportEquipmentDAO=new SportEquipmentDAOImpl();
     }
     public SportEquipmentDAO getSportEquipmentDAO(){
         return sportEquipmentDAO;
     }
}
