package org.nclc.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import org.nclc.content.Category;
import org.nclc.content.Collection;
import org.nclc.content.ECase;
import org.nclc.content.TFile;


public interface TFileService {

    public List<TFile> findAll() throws SQLException;

    /**
     * Create a new tFile, with a new ID. The checksum and file size are
     * calculated. No authorization checks are made in this method.
     *
     * @param is
     *            the bits to put in the tfile
     *
     * @return the newly created tfile
     * @throws IOException if IO error
     * @throws SQLException if database error
     */
    public TFile create(InputStream is) throws IOException, SQLException;

    public TFile create(TFile tFile) throws IOException, SQLException;

    /**
     * Retrieve the contents of the tfile
     *
     * @param  context DSpace context object
     * @param  tfile DSpace tfile
     * @return a stream from which the tfile can be read.
     * @throws IOException if IO error
     * @throws SQLException if database error
     * @throws AuthorizeException if authorization error
     */
    public InputStream retrieve(TFile tFile) throws IOException, SQLException;

    public TFile getECaseTFile(ECase eCase) throws SQLException;

    public Iterator<TFile> getCollectionTFiles(Collection collection) throws SQLException;

    public Iterator<TFile> getCategoryTFiles(Category category) throws SQLException;

    public List<TFile> findTFilesWithNoRecentChecksum() throws SQLException;

    public List<TFile> getTFilesByName(String tFileName) throws SQLException;

    int countTotal() throws SQLException;

    int countWords(TFile tFile) throws SQLException;

    int countSingleCharacters(TFile tFile) throws SQLException;

    List<TFile> getNotReferencedTFiles() throws SQLException;
}
