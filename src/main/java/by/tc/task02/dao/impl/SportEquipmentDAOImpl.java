package by.tc.task02.dao.impl;

import by.tc.task02.dao.ConstantsDAO;
import by.tc.task02.dao.SportEquipmentDAO;
import by.tc.task02.dao.exception.DAOException;
import by.tc.task02.model.SportEquipment;
import by.tc.task02.model.SportEquipmentType;
import by.tc.task02.model.criteria.Criteria;
import by.tc.task02.model.criteria.CriteriaConstants;
import by.tc.task02.model.criteria.SearchCriteria;
import by.tc.task02.service.factory.SportEquipmentFactoryClient;
import by.tc.task02.service.validation.CriteriaChecker;
import by.tc.task02.utility.source.SportEquipmentReader;
import by.tc.task02.utility.source.SportEquipmentReaderImpl;
import by.tc.task02.utility.source.SportEquipmentWriter;
import by.tc.task02.utility.source.SportEquipmentWriterImpl;
import by.tc.task02.utility.source.parser.SportEquipmentRecordParser;
import by.tc.task02.utility.source.parser.SportEquipmentRecordParserImpl;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class SportEquipmentDAOImpl implements SportEquipmentDAO {

    private SportEquipmentRecordParser sportEquipmentRecordParser;
    private CriteriaChecker criteriaChecker;
    private SportEquipmentFactoryClient sportEquipmentFactoryClient;

    public SportEquipmentDAOImpl() {
        sportEquipmentRecordParser = new SportEquipmentRecordParserImpl();
        criteriaChecker = new CriteriaChecker();
        sportEquipmentFactoryClient = new SportEquipmentFactoryClient();
    }

    @Override
    public Map<SportEquipmentType, Integer> getAllAvailableCount() throws DAOException {
        Map<SportEquipmentType, Integer> availableCount = new EnumMap<>(SportEquipmentType.class);
        Criteria criteria = new Criteria();
        criteria.add(SearchCriteria.RENTER, CriteriaConstants.NO_RENTER);
        String record;
        try (SportEquipmentReader sportEquipmentReader = new SportEquipmentReaderImpl()) {
            while ((record = sportEquipmentReader.read()) != null) {
                Map<String, String> properties = sportEquipmentRecordParser.parse(record);
                boolean isValid = criteriaChecker.check(properties, criteria);
                if (isValid) {

                    SportEquipment sportEquipment = sportEquipmentFactoryClient.getSportEquipment(properties);
                    SportEquipmentType sportEquipmentType = SportEquipmentType.
                            valueOf(sportEquipment.getClass().getSimpleName().toUpperCase());
                    Integer count = availableCount.get(sportEquipmentType);
                    if (count == null) {
                        count = new Integer(1);
                    }
                    availableCount.put(sportEquipmentType, count++);
                }
            }
        } catch (Exception ex) {
            throw new DAOException(DAOException.SOURCE_ERROR);
        }
        return availableCount;
    }

    public List<SportEquipment> find(Criteria criteria) throws DAOException {

        List<SportEquipment> sportEquipments = new ArrayList<>();
        String record;
        try (SportEquipmentReader sportEquipmentReader = new SportEquipmentReaderImpl()) {
            while ((record = sportEquipmentReader.read()) != null) {
                Map<String, String> properties = sportEquipmentRecordParser.parse(record);
                boolean isValid = criteriaChecker.check(properties, criteria);
                if (isValid) {
                    SportEquipment sportEquipment = sportEquipmentFactoryClient.getSportEquipment(properties);
                    sportEquipments.add(sportEquipment);
                }
            }
        } catch (Exception ex) {
            throw new DAOException(DAOException.SOURCE_ERROR);
        }
        return sportEquipments;
    }

    public void insert(List<SportEquipment> sportEquipments) throws DAOException {
        try (SportEquipmentWriter sportEquipmentWriter = new SportEquipmentWriterImpl(ConstantsDAO.TMP_FILE_PATH)) {
            for (SportEquipment sportEquipment : sportEquipments) {
                sportEquipmentWriter.write(sportEquipment.toString());
            }
        } catch (Exception ex) {
            throw new DAOException(DAOException.SOURCE_ERROR);
        }
    }

    public boolean updateFirst(Criteria criteriaOfUpdatedRecord, SportEquipment updatedRecordObject) throws DAOException {
        String record;
        boolean isUpdated = false;

        try (SportEquipmentReader sportEquipmentReader = new SportEquipmentReaderImpl();
             SportEquipmentWriter sportEquipmentWriter = new SportEquipmentWriterImpl(ConstantsDAO.TMP_FILE_PATH)) {
            while ((record = sportEquipmentReader.read()) != null) {
                Map<String, String> properties = sportEquipmentRecordParser.parse(record);
                boolean isValid = criteriaChecker.check(properties, criteriaOfUpdatedRecord);
                if (isValid && !isUpdated) {
                    record = sportEquipmentRecordParser.createRecord(updatedRecordObject);
                    isUpdated = true;
                }
                sportEquipmentWriter.write(record + "\n");
            }
        } catch (Exception ex) {
            throw new DAOException(DAOException.SOURCE_ERROR);
        }

        return isUpdated;
    }
}
