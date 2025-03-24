package org.example.dao;

import org.example.entity.Film;
import org.hibernate.SessionFactory;

public class FilmDAO extends GenericDao<Film> {
    public FilmDAO(SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
    }
}
