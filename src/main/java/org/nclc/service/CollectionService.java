package org.nclc.service;

import java.sql.SQLException;
import java.util.List;
import org.nclc.content.Category;
import org.nclc.content.Collection;
import org.nclc.content.ECase;


public interface CollectionService {

    public Collection createCollection(Collection collection, Category category) throws SQLException;

    public List<Collection> findAllCollections() throws SQLException ;

    public Collection findCollectionById(Integer id) throws SQLException;

    public void saveCollection(Collection collection) throws SQLException;

    public void updateCollection(Collection collection) throws SQLException;

    void deleteCollection(Collection collection) throws SQLException;
    
    List<Category> getCategories(Collection collection) throws SQLException ;
    
    int countTotal() throws SQLException;

    public void addECase(Collection collection, ECase eCase) throws SQLException;

    public void removeECase(Collection collection, ECase eCase) throws SQLException;

}
