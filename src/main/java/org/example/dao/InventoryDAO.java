package org.example.dao;

import org.example.entity.Inventory;
import org.hibernate.SessionFactory;

public class InventoryDAO extends GenericDao<Inventory> {
    public InventoryDAO(SessionFactory sessionFactory) {
        super(Inventory.class, sessionFactory);
    }
}
