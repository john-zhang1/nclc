package org.nclc.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.nclc.content.Category;
import org.nclc.content.Collection;
import org.nclc.content.ECase;
import org.nclc.content.EPerson;
import org.nclc.content.Sample;
import org.nclc.dao.CollectionDao;
import org.nclc.dao.ECaseDao;
import org.nclc.service.CategoryService;
import org.nclc.service.CollectionService;
import org.nclc.service.ECaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ECaseServiceImpl implements ECaseService {

    /** log4j category */
    private static Logger log = Logger.getLogger(ECaseServiceImpl.class);

    @Autowired(required = true)
    private ECaseDao eCaseDao;
    @Autowired(required = true)
    private CollectionDao collectionDao;

    @Autowired(required = true)
    protected CategoryService categoryService;
    @Autowired(required = true)
    protected CollectionService collectionService;

    @Override
    public ECase createECase(ECase eCase, Collection collection) throws SQLException {
        if(eCase!=null && eCase.getId()==null){
            eCaseDao.create(eCase);
        }
        if(collection!=null && collection.getId()==null){
            collectionDao.create(collection);
        }
        if(eCase!=null && collection!=null){
            eCase.addCollection(collection);
            collection.addECase(eCase);
            eCaseDao.update(eCase);
        }

        return eCase;
    }

    @Override
    public List<ECase> findAllECases() throws SQLException {
        return eCaseDao.findAll(ECase.class);
    }

    @Override
    public ECase findECaseById(Integer id) throws SQLException {
        return eCaseDao.findByID(ECase.class, id);
    }

    @Override
    public void saveECase(ECase eCase) throws SQLException {
        eCaseDao.save(eCase);
    }

    @Override
    public void updateECase(ECase eCase) throws SQLException {
        eCaseDao.update(eCase);
    }

    @Override
    public void deleteECase(ECase eCase) throws SQLException {
        eCaseDao.delete(eCase);
    }

    @Override
    public List<Category> getCategories(ECase eCase) throws SQLException {
        List<Category> result = new ArrayList<>();
        List<Collection> collections = eCase.getCollections();
        for (Collection collection : collections) {
            result.addAll(categoryService.getAllParents(collection));
        }

        return result;
    }

    @Override
    public int countTotal() throws SQLException {
        return eCaseDao.countRows();
    }

    @Override
    public List<ECase> findByCollection(Collection collection) throws SQLException {
        return eCaseDao.findAllByCollection(collection);
        
    }

    @Override
    public void move(ECase eCase, Collection from, Collection to) throws SQLException, IOException {

        // Move the ECase from one Collection to the other
        collectionService.addECase(to, eCase);
        collectionService.removeECase(from, eCase);
    }

    @Override
    public int countECases(Collection collection) throws SQLException {
        return eCaseDao.countECases(collection);
    }

    @Override
    public int countECases(Category category) throws SQLException {
        List<Collection> collections = categoryService.getAllCollections(category);
        
        return eCaseDao.countECases(collections);
    }

    @Override
    public List<ECase> findByEPerson(EPerson eperson) throws SQLException {
        return eCaseDao.findByEPerson(eperson);
    }

    @Override
    public List<ECase> findBySample(Sample sample) throws SQLException {
        return eCaseDao.findBySample(sample);
    }

}
