package org.nclc.service.impl;

import java.sql.SQLException;
import java.util.List;
import org.nclc.content.Category;
import org.nclc.content.Collection;
import org.nclc.content.ECase;
import org.nclc.dao.CategoryDao;
import org.nclc.dao.CollectionDao;
import org.nclc.service.CollectionService;
import org.nclc.service.ECaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionDao collectionDao;

    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private ECaseService eCaseService;

    @Override
    public Collection createCollection(Collection collection, Category category) throws SQLException {
         if(collection!=null && collection.getId()==null){
            collectionDao.create(collection);
        }
        if(category!=null && category.getId()==null){
            categoryDao.create(category);
        }
        collection.addCategory(category);;
        category.addCollection(collection);
        collectionDao.update(collection);
        return collection;
    }

    @Override
    public List<Collection> findAllCollections() throws SQLException {
        return collectionDao.findAll(Collection.class);
    }

    @Override
    public Collection findCollectionById(Integer id) throws SQLException {
        return collectionDao.findByID(Collection.class, id);
    }

    @Override
    public void saveCollection(Collection collection) throws SQLException {
        collectionDao.save(collection);
    }

    @Override
    public void updateCollection(Collection collection) throws SQLException {
        collectionDao.update(collection);
    }

    @Override
    public void deleteCollection(Collection collection) throws SQLException {
        collectionDao.delete(collection);
    }

    @Override
    public List<Category> getCategories(Collection collection) throws SQLException {
        return collection.getCategories();
    }

    @Override
    public int countTotal() throws SQLException {
        return collectionDao.countRows();
    }

    @Override
    public void addECase(Collection collection, ECase eCase) throws SQLException {
        if(!eCase.getCollections().contains(collection))
        {
            eCase.addCollection(collection);
            eCaseService.updateECase(eCase);
        }
    }

    @Override
    public void removeECase(Collection collection, ECase eCase) throws SQLException {
        if (eCase.getCollections().size() == 1)
        {
            // Orphan; delete it
            eCaseService.deleteECase(eCase);
        } else {
            //Remove the ecase from the collection if multiple collections
            eCase.removeCollection(collection);
            eCaseService.updateECase(eCase);
        }
    }
    
}
