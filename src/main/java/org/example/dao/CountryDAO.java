package org.example.dao;

import org.hibernate.SessionFactory;

public class CountryDAO extends GenericDao<CountryDAO> {
    public CountryDAO(SessionFactory sessionFactory) {
        super(CountryDAO.class, sessionFactory);
    }
}
