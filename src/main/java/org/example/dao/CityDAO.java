package org.example.dao;

import org.example.entity.City;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class CityDAO extends GenericDao<City> {
    public CityDAO(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }

    public City getByName(String name) {
        Query<City> query = getCurrentSession().createQuery("from City where city = :name", City.class);
        query.setParameter("name", name);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
