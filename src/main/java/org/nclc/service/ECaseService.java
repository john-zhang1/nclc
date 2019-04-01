package org.nclc.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.nclc.content.Category;
import org.nclc.content.Collection;
import org.nclc.content.ECase;
import org.nclc.content.EPerson;
import org.nclc.content.Sample;

public interface ECaseService {

    public ECase createECase(ECase eCase, Collection collection) throws SQLException;

    public List<ECase> findAllECases() throws SQLException ;

    public ECase findECaseById(Integer id) throws SQLException;

    public void saveECase(ECase eCase) throws SQLException;

    public void updateECase(ECase eCase) throws SQLException;

    public void deleteECase(ECase eCase) throws SQLException;
    
    public List<Category> getCategories(ECase eCase) throws SQLException ;
    
    public int countTotal() throws SQLException;

    public List<ECase> findByCollection(Collection collection) throws SQLException;

    /**
     * Moves the ecase from one collection to another one
     * @param eCase
     * @param from
     * @param to
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public void move(ECase eCase, Collection from, Collection to) throws SQLException, IOException;

    public int countECases(Collection collection) throws SQLException;

    public int countECases(Category category) throws SQLException;

    public List<ECase> findByEPerson(EPerson eperson) throws SQLException;

    public List<ECase> findBySample(Sample sample) throws SQLException;

}
