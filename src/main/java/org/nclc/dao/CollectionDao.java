package org.nclc.dao;

import java.sql.SQLException;
import org.nclc.content.Collection;


public interface CollectionDao extends GenericDao<Collection> {

    int countRows() throws SQLException;
}
