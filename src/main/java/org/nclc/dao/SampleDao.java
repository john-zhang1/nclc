package org.nclc.dao;

import java.sql.SQLException;
import java.util.List;
import org.nclc.content.Sample;


public interface SampleDao extends GenericDao<Sample> {

    public List<Sample> findByText(String text) throws SQLException;

    public List<Sample> findBySampleErrorText(String errorText) throws SQLException;

    int countRows() throws SQLException;    

}
