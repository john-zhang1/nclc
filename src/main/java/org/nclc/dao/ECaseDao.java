package org.nclc.dao;

import java.sql.SQLException;
import java.util.List;
import org.nclc.content.Collection;
import org.nclc.content.ECase;
import org.nclc.content.EPerson;
import org.nclc.content.Sample;


public interface ECaseDao extends GenericDao<ECase> {

    public List<ECase> findAllByCollection(Collection collection) throws SQLException;

    public int countECases(Collection collection) throws SQLException;

    public int countECases(List<Collection> collections) throws SQLException;

    public List<ECase> findByEPerson(EPerson eperson) throws SQLException;

    public List<ECase> findBySample(Sample sample) throws SQLException;

    public int countRows() throws SQLException;

}
