package org.nclc.dao.impl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.Query;
import org.nclc.content.Category;
import org.nclc.dao.AbstractHibernateDao;
import org.nclc.dao.CategoryDao;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoImpl extends AbstractHibernateDao<Category> implements CategoryDao {

    public CategoryDaoImpl() {
        super();
    }

    @Override
    public List<Category> findAllNoParent() throws SQLException {
        Query query = createQuery("SELECT category FROM Category as category WHERE category.parentCategories IS EMPTY");
        return (List<Category>) query.list();
    }

    @Override
    public List<Category> getSubCategories(Category category) throws SQLException {
        Query query = createQuery("SELECT distinct category.subCategories FROM Category as category WHERE category.id=:id");
        query.setInteger("id", category.getId());
        return (List<Category>) query.list();
    }

    @Override
    public List<Category> getParentCategories(Category category) throws SQLException {
        Query query = createQuery("SELECT distinct category.parentCategories FROM Category category WHERE category.id=:id");
        query.setInteger("id", category.getId());
        return (List<Category>) query.list(); 
    }

    @Override
    public int countRows() throws SQLException {
        return count(createQuery("SELECT count(*) FROM Category"));
    }

    @Override
    public int updateCategory(Category category) throws SQLException {
        Query query = createQuery("update Category set title = :title where id = :id");
        query.setParameter("title", category.getTitle());
        query.setParameter("id", category.getId());

        return query.executeUpdate();
    }
    
}
