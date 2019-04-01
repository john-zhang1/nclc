package org.nclc.dao;

import java.sql.SQLException;
import java.util.List;


/**
 * Generic Database Access Object interface class that should be implemented by all DAOs.
 * It offers up a lot of general methods so these don't need to be declared again in each DAO.
 * The default hibernate implementation offers up a class that implements all these methods.
 *
 * @param <T> class type
 */
public interface GenericDao<T> {

    public T create(T t) throws SQLException;

    public void save(T t) throws SQLException;

    public void update(T t) throws SQLException;

    public void delete(T t) throws SQLException;

    /**
     * Fetch all persisted instances of a given object type.
     *
     * @param clazz the desired type.
     * @return list of DAOs of the same type as clazz
     * @throws SQLException if database error
     */
    public List<T> findAll(Class<T> clazz) throws SQLException;

    /**
     * Execute a JPQL query returning a unique result.
     *
     * @param query JPQL query string
     * @return a DAO specified by the query string
     * @throws SQLException if database error
     */
    public T findUnique(String query) throws SQLException;

    public T findByID(Class clazz, Long id) throws SQLException;

    public T findByID(Class clazz, Integer id) throws SQLException;

    /**
     * Execute a JPQL query and return a collection of results.
     *
     * @param context
     * @param query JPQL query string
     * @return list of DAOs specified by the query string
     * @throws SQLException if database error
     */
    public List<T> findMany(String query) throws SQLException;
}
