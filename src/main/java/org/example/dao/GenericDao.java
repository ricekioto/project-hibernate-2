package org.example.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.lang.reflect.Field;
import java.util.List;

public abstract class GenericDao<T> {
    private final Class<T> clazz;
    private Class<?> clazzId;

    private final SessionFactory sessionFactory;

    public GenericDao(Class<T> clazz, SessionFactory sessionFactory) {
        this.clazz = clazz;
        this.sessionFactory = sessionFactory;

        for (Field field : clazz.getDeclaredFields()) {
            if (field.getName().equals("id"))
                clazzId = field.getType();
        }
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public T findById(int id) {
        return (T) getCurrentSession().get(clazz, id);
    }

    public List<T> getItems(int offset, int limit) {
        Query<T> query = getCurrentSession().createQuery("from " + clazz.getName(), clazz);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getName(), clazz).getResultList();
    }

    public T save(final T entity) {
        getCurrentSession().persist(entity);
        return entity;
    }

    public T update(final T entity) {
        getCurrentSession().merge(entity);
        return entity;
    }

    public void delete(final T entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteById(final int id) {
        final T entity = findById(id);
        delete(entity);
    }
}
