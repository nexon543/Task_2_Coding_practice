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
import by.tc.task02.utility.source.SportEquipmentReaderWriter;
import by.tc.task02.utility.source.SportEquipmentReaderWriterImpl;
import by.tc.task02.utility.source.parser.SportEquipmentRecordParser;
import by.tc.task02.utility.source.parser.SportEquipmentRecordParserImpl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class SportEquipmentDAOImpl implements SportEquipmentDAO {

    private SportEquipmentReaderWriter sportEquipmentReader;
    private SportEquipmentRecordParser sportEquipmentRecordParser;
    private CriteriaChecker criteriaChecker;
    private SportEquipmentFactoryClient sportEquipmentFactoryClient;

    public SportEquipmentDAOImpl() throws DAOException {
        try {
            sportEquipmentReader = new SportEquipmentReaderWriterImpl();
        } catch (IOException ex) {
            throw new DAOException(DAOException.SOURCE_ERROR);
        }
        sportEquipmentReader.setSourceNameFromProperties();
        sportEquipmentRecordParser = new SportEquipmentRecordParserImpl();
        criteriaChecker = new CriteriaChecker();
        sportEquipmentFactoryClient = new SportEquipmentFactoryClient();
    }

    @Override
    public Map<SportEquipmentType, Integer> getAllAvailableCount() throws DAOException {
        List<SportEquipment> sportEquipments = new ArrayList<>();
        Map<SportEquipmentType, Integer> availableCount = new EnumMap<>(SportEquipmentType.class);
        Criteria criteria = new Criteria();
        criteria.add(SearchCriteria.RENTER, CriteriaConstants.NO_RENTER);
        String record;
        try (SportEquipmentReaderWriter sportEquipmentReader = new SportEquipmentReaderWriterImpl()) {
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
        try (SportEquipmentReaderWriter sportEquipmentReader = new SportEquipmentReaderWriterImpl()) {
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
        try (SportEquipmentReaderWriter sportEquipmentReader = new SportEquipmentReaderWriterImpl()) {
            for (SportEquipment sportEquipment : sportEquipments) {
                //sportEquipmentReader.write(sportEquipment.toString());
            }
        } catch (Exception ex) {
            throw new DAOException(DAOException.SOURCE_ERROR);
        }
    }

    public boolean updateFirst(Criteria criteriaOfUpdatedRecord, SportEquipment updatedRecordObject) throws DAOException {
        String record;
        boolean isUpdated = false;
        File tmpFile = new File(ConstantsDAO.TMP_FILE_PATH);
        try {
            tmpFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (SportEquipmentReaderWriter sportEquipmentReader = new SportEquipmentReaderWriterImpl()) {
            FileWriter newFileWriter = new FileWriter(tmpFile);
            BufferedWriter newFileBufferedWriter=new BufferedWriter(newFileWriter);
            while ((record = sportEquipmentReader.read()) != null) {
                Map<String, String> properties = sportEquipmentRecordParser.parse(record);
                boolean isValid = criteriaChecker.check(properties, criteriaOfUpdatedRecord);
                if (isValid && !isUpdated) {
                    record = sportEquipmentRecordParser.createRecord(updatedRecordObject);
                    isUpdated = true;
                }
                newFileBufferedWriter.write(record);
            }
            newFileBufferedWriter.close();
        } catch (Exception ex) {
            throw new DAOException(DAOException.SOURCE_ERROR);
        }
        String currentFilePath = sportEquipmentReader.getFilePath();
        File currentFile = new File(currentFilePath);
        currentFile.delete();
        tmpFile.renameTo(currentFile);
        return isUpdated;
    }
}
