package org.nclc.dao;

import java.sql.SQLException;
import java.util.List;
import org.nclc.content.Category;


public interface CategoryDao extends GenericDao<Category> {

    List<Category> findAllNoParent() throws SQLException;

    /**
     * Get immediate child categories
     * @param category
     * @return
     * @throws SQLException 
     */
    List<Category> getSubCategories(Category category) throws SQLException;

    List<Category> getParentCategories(Category category) throws SQLException;

    int countRows() throws SQLException;

    int updateCategory(Category category) throws SQLException;
}
