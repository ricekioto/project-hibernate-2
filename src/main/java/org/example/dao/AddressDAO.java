package org.example.dao;

import org.example.entity.Address;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class AddressDAO extends GenericDao<Address> {

    public AddressDAO(SessionFactory sessionFactory) {
        super(Address.class, sessionFactory);
    }

}
