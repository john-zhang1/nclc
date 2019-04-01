package org.nclc.dao;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import org.nclc.content.Category;
import org.nclc.content.Collection;
import org.nclc.content.EPerson;
import org.nclc.content.Sample;
import org.nclc.content.TFile;

public interface TFileDao extends GenericDao<TFile> {

    public List<TFile> findTFilesWithNoRecentChecksum() throws SQLException;

    public Iterator<TFile> findByCategory(Category category) throws SQLException;

    public Iterator<TFile> findByCollection(Collection collection) throws SQLException;

    public Iterator<TFile> findByEPerson(EPerson ePerson) throws SQLException;

    public Iterator<TFile> findBySample(Sample sample) throws SQLException;

    public Long countByStoreNumber(Integer storeNumber) throws SQLException;

    int countWords() throws SQLException;

    int countSingleCharacters() throws SQLException;

    List<TFile> getNotReferencedTFiles() throws SQLException;
}
