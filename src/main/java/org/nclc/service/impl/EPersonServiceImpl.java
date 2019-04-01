package org.nclc.service.impl;

import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import org.nclc.content.EPerson;
import org.nclc.content.data.EducationalStage;
import org.nclc.content.data.LanguageCode;
import org.nclc.dao.EPersonDao;
import org.nclc.service.EPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EPersonServiceImpl implements EPersonService {

    /**
     * log4j category
     */
    private static final Logger log = Logger.getLogger(EPersonServiceImpl.class);

    @Autowired
    private EPersonDao ePersonDao;

    @Override
    public EPerson createEPerson(EPerson ePerson) throws SQLException {
        if (ePerson.getId() == null && isIn(ePerson) == null) {
            return ePersonDao.create(ePerson);
        } else {
            return ePerson;
        }
    }

    @Override
    public List<EPerson> findAllEPersons() throws SQLException {
        return ePersonDao.findAll(EPerson.class);
    }

    @Override
    public EPerson findById(Integer id) throws SQLException {
        return ePersonDao.findByID(EPerson.class, id);
    }

    @Override
    public EPerson findByEmail(String email) throws SQLException {
        return ePersonDao.findByEmail(email);
    }

    @Override
    public List<EPerson> findByLanguage(LanguageCode language) throws SQLException {
        return ePersonDao.findByLanguage(language);
    }

    @Override
    public List<EPerson> findByEducationalStage(EducationalStage stage) throws SQLException {
        return ePersonDao.findByEducationalStage(stage);
    }

    @Override
    public int countRows() throws SQLException {
        return ePersonDao.countRows();
    }

    @Override
    public void saveEPerson(EPerson ePerson) throws SQLException {
        ePersonDao.save(ePerson);
    }

    @Override
    public void updateEPerson(EPerson ePerson) throws SQLException {
        ePersonDao.update(ePerson);
    }

    @Override
    public void deleteEPerson(EPerson ePerson) throws SQLException {
        ePersonDao.delete(ePerson);
    }

    @Override
    public List<String> search(String email) throws SQLException {
        return ePersonDao.search(email);
    }

    @Override
    public EPerson isIn(EPerson ePerson) throws SQLException {
        List<EPerson> persons = findAllEPersons();
        EPerson p = findSampleInstance(ePerson.getEmail(), persons);
        return p;
    }

    private EPerson findSampleInstance(String email, List<EPerson> persons) {
        return persons.stream()
                .filter(person -> person.getEmail().toLowerCase().equals(email.toLowerCase()))
                .findFirst()
                .orElse(null);
    }
}
