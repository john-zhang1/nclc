package org.nclc.service;

import java.sql.SQLException;
import java.util.List;
import org.nclc.content.EPerson;
import org.nclc.content.data.EducationalStage;
import org.nclc.content.data.LanguageCode;

public interface EPersonService {

    public EPerson createEPerson(EPerson ePerson) throws SQLException;

    public List<EPerson> findAllEPersons() throws SQLException ;
    
    public EPerson findById(Integer id) throws SQLException;
    
    public EPerson findByEmail(String email) throws SQLException;

    public List<EPerson> findByLanguage(LanguageCode language) throws SQLException;

    public List<EPerson> findByEducationalStage(EducationalStage stage) throws SQLException;

    int countRows() throws SQLException;

    public void saveEPerson(EPerson ePerson) throws SQLException;

    public void updateEPerson(EPerson ePerson) throws SQLException;

    public void deleteEPerson(EPerson ePerson) throws SQLException;

    public EPerson isIn(EPerson ePerson) throws SQLException;

    public List<String> search(String email) throws SQLException;
}
