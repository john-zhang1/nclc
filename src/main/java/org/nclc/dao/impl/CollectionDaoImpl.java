package org.nclc.dao.impl;

import java.sql.SQLException;
import org.nclc.content.Collection;
import org.nclc.dao.AbstractHibernateDao;
import org.nclc.dao.CollectionDao;
import org.springframework.stereotype.Repository;

@Repository
public class CollectionDaoImpl extends AbstractHibernateDao<Collection> implements CollectionDao {

    @Override
    public int countRows() throws SQLException {
        return count(createQuery("SELECT count(*) FROM Collection"));
    }

}
