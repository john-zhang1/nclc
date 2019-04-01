package org.nclc.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.nclc.content.Category;
import org.nclc.content.Collection;
import org.nclc.dao.CategoryDao;
import org.nclc.dao.CollectionDao;
import org.nclc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    /** log4j category */
    private static Logger log = Logger.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CollectionDao collectionDao;

    @Override
    public Category createCategory(Category parent) throws SQLException {
        return categoryDao.create(parent);
    }

    @Override
    public Category createSubCategory(Category parentCategory, Category category) throws SQLException {
        if(parentCategory!=null && parentCategory.getId()==null){
            categoryDao.create(parentCategory);
        }
        if(category!=null && category.getId()==null){
            categoryDao.create(category);
        }
        category.addParentCategory(parentCategory);
        parentCategory.addSubCategory(category);
        categoryDao.update(parentCategory);
        return category;
    }

    @Override
    public List<Category> findAllCategory() throws SQLException {
        return categoryDao.findAll(Category.class);
    }

    @Override
    public List<Category> findAllTop() throws SQLException {
        return categoryDao.findAllNoParent();
    }

    @Override
    public List<Category> getAllParents(Category category) throws SQLException {
        List<Category> result = new ArrayList<>();
        List<Category> categories = category.getParentCategoryList();
        result.addAll(categories);
        for (Category cat : categories) {
            result.addAll(getAllParents(cat));
        }

        return result;
    }

    @Override
    public List<Category> getAllParents(Collection collection) throws SQLException {
        List<Category> result = new ArrayList<>();
        List<Category> categories = collection.getCategories();
        result.addAll(categories);
        for (Category category : categories) {
            result.addAll(getAllParents(category));
        }
        return result;
    }

    @Override
    public Category findCategoryById(Integer id) throws SQLException {
        return categoryDao.findByID(Category.class, id);
    }

    @Override
    public void saveCategory(Category category) throws SQLException {
        categoryDao.save(category);
    }

    @Override
    public void updateCategory(Category category) throws SQLException {
        categoryDao.updateCategory(category);
    }

    @Override
    public void deleteCategory(Category category) throws SQLException {
        categoryDao.delete(category);
    }

    @Override
    public List<Category> getParentCategories(Category category) throws SQLException {
        return categoryDao.getParentCategories(category);
    }

    @Override
    public List<Category> getSubCategories(Category category) throws SQLException {
        return categoryDao.getSubCategories(category);
    }

    protected void addCollectionList(Category category, List<Collection> collectionList) throws SQLException
    {
        for (Category subCategory : category.getSubCategories())
        {
            addCollectionList(subCategory, collectionList);
        }

        for (Collection collection : category.getCollections())
        {
            collectionList.add(collection);
        }
    }

    @Override
    public void addCollection(Category category, Collection collection) throws SQLException {
        if(category!=null && collection!=null){
            category.addCollection(collection);
            collection.addCategory(category);
            collectionDao.update(collection);
        }
    }

    @Override
    public void removeCollection(Category category, Collection c) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeSubCategory(Category parentCategory, Category childCategory) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int countTotal() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Category getParentCategoryObject(Category category) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Collection> getAllCollections(Category category) throws SQLException {
        List<Collection> collectionList = new ArrayList<>();
        List<Category> subCatgories = category.getSubCategoryList();
        for (Category subCatgory : subCatgories)
        {
            addCollectionList(subCatgory, collectionList);
        }

        List<Collection> collections = category.getCollectionList();
        for (Collection collection : collections)
        {
            collectionList.add(collection);
        }
        return collectionList;
    }

    @Override
    public Map<String, String> getCategoryPath(Category category) throws SQLException {
        Map<String, String> categoryChain = new LinkedHashMap<>();
        if(category != null) {
            List<Category> parentCategories = getAllParents(category);
            Collections.reverse(parentCategories);
            for(Category cat : parentCategories){
                categoryChain.put("show-" + cat.getId() + "-category", cat.getTitle());
            }            
            categoryChain.put("show-" + category.getId() + "-category", category.getTitle());
        }
        return categoryChain;
    }
    
}
