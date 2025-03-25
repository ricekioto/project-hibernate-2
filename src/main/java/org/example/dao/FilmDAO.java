package org.example.dao;

import org.example.entity.Film;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class FilmDAO extends GenericDao<Film> {
    public FilmDAO(SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
    }

    public Film getFirstAvailableFilmRent() {
        Query<Film> query = getCurrentSession().createQuery("from Film f where f.id not in (select distinct film.id from Inventory i)", Film.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

}
