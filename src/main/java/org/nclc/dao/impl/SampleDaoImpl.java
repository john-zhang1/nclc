package org.nclc.dao.impl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.nclc.content.EPerson;
import org.nclc.content.Sample;
import org.nclc.dao.AbstractHibernateDao;
import org.nclc.dao.SampleDao;
import org.springframework.stereotype.Repository;

@Repository
public class SampleDaoImpl extends AbstractHibernateDao<Sample> implements SampleDao {

    public SampleDaoImpl() {
        super();
    }

    @Override
    public List<Sample> findByText(String text) throws SQLException {
        Criteria criteria = getSession().createCriteria(EPerson.class);
        criteria.add(Restrictions.eq("text", text.toLowerCase()));

        return (List<Sample>)criteria.list();
    }

    @Override
    public int countRows() throws SQLException {
        return count(createQuery("SELECT count(*) FROM Sample"));
    }

    @Override
    public List<Sample> findBySampleErrorText(String errorText) throws SQLException {
        Criteria criteria = getSession().createCriteria(EPerson.class);
        criteria.add(Restrictions.eq("errorText", errorText.toLowerCase()));

        return (List<Sample>)criteria.list();
    }
    
}
