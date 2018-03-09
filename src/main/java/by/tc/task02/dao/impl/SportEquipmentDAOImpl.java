package by.tc.task02.dao.impl;

import by.tc.task02.dao.SportEquipmentDAO;
import by.tc.task02.dao.exception.DAOException;
import by.tc.task02.model.SportEquipment;
import by.tc.task02.model.criteria.Criteria;
import by.tc.task02.model.criteria.CriteriaConstants;
import by.tc.task02.model.criteria.SearchCriteria;
import by.tc.task02.service.factory.SportEquipmentFactoryClient;
import by.tc.task02.service.validation.CriteriaChecker;
import by.tc.task02.utility.reader.SportEquipmentReader;
import by.tc.task02.utility.reader.SportEquipmentReaderImpl;
import by.tc.task02.utility.reader.parser.SportEquipmentRecordParser;
import by.tc.task02.utility.reader.parser.SportEquipmentRecordParserImpl;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SportEquipmentDAOImpl implements SportEquipmentDAO {

    private SportEquipmentReader sportEquipmentReader;
    private SportEquipmentRecordParser sportEquipmentRecordParser;
    private CriteriaChecker criteriaChecker;
    private SportEquipmentFactoryClient sportEquipmentFactoryClient;
    public SportEquipmentDAOImpl(){
        sportEquipmentReader=new SportEquipmentReaderImpl();
        sportEquipmentReader.setSourceNameFromProperties();
        sportEquipmentRecordParser=new SportEquipmentRecordParserImpl();
        criteriaChecker=new CriteriaChecker();
        sportEquipmentFactoryClient=new SportEquipmentFactoryClient();
    }

    @Override
    public List<SportEquipment> getAvailable() throws DAOException {
        List <SportEquipment> sportEquipments=null;
        Criteria criteria=new Criteria();
        criteria.add(SearchCriteria.RENTER, CriteriaConstants.NO_RENTER);
        try {
            sportEquipmentReader.openConnection();

        }
        catch (FileNotFoundException ex){
            throw new DAOException(DAOException.SOURCE_ERROR);
        }
        sportEquipments=filterRecords(criteria);
        return sportEquipments;
    }

    private List<SportEquipment> filterRecords(Criteria criteria) throws DAOException {
        List <SportEquipment> sportEquipments=new ArrayList<>();
        try {
            String record;
            while ((record = sportEquipmentReader.read()) != null){
                Map <String, String> properties=sportEquipmentRecordParser.parse(record);
                boolean isValid=criteriaChecker.check(properties, criteria);
                if (isValid){
                    SportEquipment sportEquipment=sportEquipmentFactoryClient.getSportEquipment(properties);
                    sportEquipments.add(sportEquipment);
                }
            }
        }
        catch (IOException ex){
            throw new DAOException(DAOException.RECORD_ERROR);
        }
        return sportEquipments;
    }

    public List<SportEquipment> find(Criteria criteria) throws DAOException {

        return filterRecords(criteria);
    }

    public void persist(List<SportEquipment> sportEquipments) {

    }
}
