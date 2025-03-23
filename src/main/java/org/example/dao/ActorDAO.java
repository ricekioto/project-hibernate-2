package org.example.dao;

import org.example.entity.Actor;
import org.hibernate.SessionFactory;

public class ActorDAO extends GenericDao<Actor> {

    public ActorDAO(SessionFactory sessionFactory) {
        super(Actor.class, sessionFactory);
    }
}
