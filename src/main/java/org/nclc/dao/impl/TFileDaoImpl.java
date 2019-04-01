package org.nclc.dao.impl;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import org.nclc.content.Category;
import org.nclc.content.Collection;
import org.nclc.content.EPerson;
import org.nclc.content.Sample;
import org.nclc.content.TFile;
import org.nclc.dao.AbstractHibernateDao;
import org.nclc.dao.TFileDao;
import org.springframework.stereotype.Repository;

@Repository
public class TFileDaoImpl extends AbstractHibernateDao<TFile> implements TFileDao {

    public TFileDaoImpl() {
        super();
    }

    @Override
    public List<TFile> findTFilesWithNoRecentChecksum() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<TFile> findByCategory(Category category) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<TFile> findByCollection(Collection collection) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<TFile> findByEPerson(EPerson ePerson) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<TFile> findBySample(Sample sample) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long countByStoreNumber(Integer storeNumber) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int countWords() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int countSingleCharacters() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TFile> getNotReferencedTFiles() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
