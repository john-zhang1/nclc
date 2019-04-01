package org.nclc.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.nclc.content.Category;
import org.nclc.content.Collection;

public interface CategoryService {

//    Create a new top-level category
    Category createCategory(Category parent) throws SQLException;

//    Create a new sub-category within this category
    Category createSubCategory(Category parentCategory, Category category) throws SQLException;

    List<Category> findAllCategory() throws SQLException ;

    List<Category> findAllTop() throws SQLException;
    
    List<Category> getAllParents(Category category) throws SQLException;

    List<Category> getAllParents(Collection collection) throws SQLException;

//    List<Collection> getCollections(Category category) throws SQLException;
            
    Category findCategoryById(Integer id) throws SQLException;

    void saveCategory(Category category) throws SQLException;

    void updateCategory(Category category) throws SQLException;

    void deleteCategory(Category category) throws SQLException;

    public List<Category> getParentCategories(Category category) throws SQLException;

    List<Category> getSubCategories(Category category) throws SQLException ;
    
    public void addCollection( Category category, Collection collection) throws SQLException;

//    Add an existing category as a subcategory to the category
//    public void addSubcategory( Category parentCategory, Category childCategory) throws SQLException;

    public void removeCollection( Category category, Collection c) throws SQLException;

    public void removeSubCategory( Category parentCategory, Category childCategory) throws SQLException;

    int countTotal() throws SQLException;

    Category getParentCategoryObject(Category category) throws SQLException;
    
    public List<Collection> getAllCollections(Category category) throws SQLException;

    /**
     * 
     * @param category
     * @return category chain for breadCrumb 
     * @throws SQLException 
     */
    public Map<String, String> getCategoryPath(Category category) throws SQLException;

}
