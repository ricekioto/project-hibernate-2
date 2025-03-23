package org.example;

import org.example.config.MySessionFactory;
import org.example.dao.ActorDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = MySessionFactory.getSessionFactory();
        ActorDAO actorDAO = new ActorDAO(sessionFactory);
        actorDAO.findAll().forEach(System.out::println);
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            System.out.println(session);

            session.getTransaction().commit();
        }
    }
}