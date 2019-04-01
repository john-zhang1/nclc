package org.nclc.dao;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class AbstractHibernateDao<T> implements GenericDao<T> {

    @Autowired
    private SessionFactory sessionFactory;
    
    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }
    
    @Override
    public T create(T t) throws SQLException {
        getSession().persist(t);
        return t;
    }

    @Override
    public void save(T t) throws SQLException {
        getSession().save(t);
    }

    @Override
    public void update(T t) throws SQLException {
        getSession().update(t);
    }

    @Override
    public void delete(T t) throws SQLException {
        getSession().delete(t);
    }

    @Override
    public List<T> findAll(Class<T> clazz) throws SQLException {
        return list(getSession().createCriteria(clazz).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY));
    }

    @Override
    public T findUnique(String query) throws SQLException {
        @SuppressWarnings("unchecked")
        T result = (T) createQuery(query).uniqueResult();
        return result;
    }

    @Override
    public T findByID(Class clazz, Long id) throws SQLException {
        @SuppressWarnings("unchecked")
        T result = (T) getSession().get(clazz, id);
        return result;
    }

    @Override
    public T findByID(Class clazz, Integer id) throws SQLException {
        @SuppressWarnings("unchecked")
        T result = (T) getSession().get(clazz, id);
        return result;
    }

    @Override
    public List<T> findMany(String query) throws SQLException {
        @SuppressWarnings("unchecked")
        List<T> result = (List<T>) createQuery(query).list();
        return result;
    }

    public List<T> list(Criteria criteria)
    {
        @SuppressWarnings("unchecked")
        List<T> result = (List<T>) criteria.list();
        return result;
    }

    public Query createQuery(String query) throws SQLException {
        return getSession().createQuery(query);
    }

    public List<T> list(Query query)
    {
        @SuppressWarnings("unchecked")
        List<T> result = (List<T>) query.list();
        return result;
    }

    public int count(Criteria criteria)
    {
        return ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    public int count(Query query)
    {
        return ((Long) query.uniqueResult()).intValue();
    }

    public long countLong(Criteria criteria)
    {
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    public Iterator<T> iterate(Query query)
    {
        @SuppressWarnings("unchecked")
        Iterator<T> result = (Iterator<T>) query.iterate();
        return result;
    }
    
}
