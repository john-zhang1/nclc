package org.nclc.dao.impl;

import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.nclc.content.Collection;
import org.nclc.content.ECase;
import org.nclc.content.EPerson;
import org.nclc.content.Sample;
import org.nclc.dao.AbstractHibernateDao;
import org.nclc.dao.ECaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ECaseDaoImpl extends AbstractHibernateDao<ECase> implements ECaseDao {

    private static final Logger log = Logger.getLogger(ECaseDaoImpl.class);

    @Autowired
    private ECaseDao ecaseDao;

    public ECaseDaoImpl() {
        super();
    }

    @Override
    public List<ECase> findAllByCollection(Collection collection) throws SQLException {
        Query query = createQuery("select e from ECase e join e.collections c WHERE :collection IN c");
        query.setParameter("collection", collection);

        return list(query);
    }

    @Override
    public int countECases(Collection collection) throws SQLException {
        Query query = createQuery("select count(e) from ECase e join e.collections c WHERE :collection IN c");
        query.setParameter("collection", collection);

        return count(query);
    }

    @Override
    public int countECases(List<Collection> collections) throws SQLException {
        if (collections.isEmpty()) {
            return 0;
        }
        Query query = createQuery("select count(distinct e) from ECase e " +
                                            "join e.collections collection " +
                                            "WHERE collection IN (:collections)");
        query.setParameterList("collections", collections);

        return count(query);
    }

    @Override
    public int countRows() throws SQLException {
        return count(createQuery("SELECT count(*) FROM ECase"));
    }

    @Override
    public List<ECase> findByEPerson(EPerson eperson) throws SQLException {
        Query query = createQuery("FROM ECase WHERE ePerson= :ePerson");
        query.setParameter("ePerson", eperson);
        return list(query);
    }

    @Override
    public List<ECase> findBySample(Sample sample) throws SQLException {
        Query query = createQuery("FROM ECase WHERE sample= :sample");
        query.setParameter("sample", sample);
        return list(query);
    }
    
}
