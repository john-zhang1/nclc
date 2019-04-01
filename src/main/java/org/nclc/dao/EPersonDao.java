package org.nclc.dao;

import java.sql.SQLException;
import java.util.List;
import org.nclc.content.EPerson;
import org.nclc.content.data.EducationalStage;
import org.nclc.content.data.LanguageCode;


public interface EPersonDao extends GenericDao<EPerson> {

    public EPerson findByEmail(String email) throws SQLException;

    public List<EPerson> findByLanguage(LanguageCode language) throws SQLException;

    public List<EPerson> findByEducationalStage(EducationalStage educationalStage) throws SQLException;

    public void saveEPerson(EPerson ePerson) throws SQLException;

    int countRows() throws SQLException;

    public List<String> search(String keyword) throws SQLException;
}
