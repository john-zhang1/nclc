package org.nclc.dao.impl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.nclc.content.EPerson;
import org.nclc.content.data.EducationalStage;
import org.nclc.content.data.LanguageCode;
import org.nclc.dao.AbstractHibernateDao;
import org.nclc.dao.EPersonDao;
import org.springframework.stereotype.Repository;

@Repository
public class EPersonDaoImpl extends AbstractHibernateDao<EPerson> implements EPersonDao {

    public EPersonDaoImpl() {
        super();
    }

    @Override
    public EPerson findByEmail(String email) throws SQLException {
        Criteria criteria = getSession().createCriteria(EPerson.class);
        criteria.add(Restrictions.eq("email", email.toLowerCase()));

        return (EPerson) criteria.uniqueResult();
    }

    @Override
    public List<EPerson> findByLanguage(LanguageCode language) throws SQLException {
        Criteria criteria = getSession().createCriteria(EPerson.class);
        criteria.add(Restrictions.eq("nativeLanguage", language));

        return (List<EPerson>)criteria.list();
    }

    @Override
    public int countRows() throws SQLException {
        return count(createQuery("SELECT count(*) FROM EPerson"));
    }

    @Override
    public void saveEPerson(EPerson ePerson) throws SQLException {
        create(ePerson);
    }

    @Override
    public List<String> search(String keyword) throws SQLException {
        Query query = createQuery("SELECT email FROM EPerson where email like :keyword");
        query.setParameter("keyword", "%" + keyword + "%");

        return (List<String>) query.list();
    }

    @Override
    public List<EPerson> findByEducationalStage(EducationalStage stage) throws SQLException {
        Criteria criteria = getSession().createCriteria(EPerson.class);
        criteria.add(Restrictions.eq("educationalStage", stage));

        return (List<EPerson>)criteria.list();
    }
}
