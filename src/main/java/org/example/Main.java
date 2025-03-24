package org.example;

import org.example.config.MySessionFactory;
import org.example.dao.*;
import org.example.entity.Address;
import org.example.entity.City;
import org.example.entity.Customer;
import org.example.entity.Store;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {

    private final SessionFactory sessionFactory;

    private final ActorDAO actorDAO;
    private final AddressDAO addressDAO;
    private final CategoryDAO categoryDAO;
    private final CityDAO cityDAO;
    private final CountryDAO countryDAO;
    private final CustomerDAO customerDAO;
    private final FilmDAO filmDAO;
    private final FilmTextDAO filmTextDAO;
    private final InventoryDAO inventoryDAO;
    private final LanguageDAO languageDAO;
    private final PaymentDAO paymentDAOr;
    private final RentalDAO rentalDAO;
    private final StaffDAO staffDAO;
    private final StoreDAO storeDAO;

    public Main() {
        sessionFactory = MySessionFactory.getSessionFactory();

        actorDAO = new ActorDAO(sessionFactory);
        addressDAO = new AddressDAO(sessionFactory);
        categoryDAO = new CategoryDAO(sessionFactory);
        cityDAO = new CityDAO(sessionFactory);
        countryDAO = new CountryDAO(sessionFactory);
        customerDAO = new CustomerDAO(sessionFactory);
        filmDAO = new FilmDAO(sessionFactory);
        filmTextDAO = new FilmTextDAO(sessionFactory);
        inventoryDAO = new InventoryDAO(sessionFactory);
        languageDAO = new LanguageDAO(sessionFactory);
        paymentDAOr = new PaymentDAO(sessionFactory);
        rentalDAO = new RentalDAO(sessionFactory);
        staffDAO = new StaffDAO(sessionFactory);
        storeDAO = new StoreDAO(sessionFactory);
    }

    public static void main(String[] args) {
        Main main = new Main();
        Customer customer = main.createCustomer();
        System.out.println(customer);

    }

    public Customer createCustomer() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Store store = storeDAO.getItems(0, 1).get(0);
            City city = cityDAO.getByName("al-Hawiya");

            Address address = Address.builder()
                    .address("432 Octun")
                    .district("Virto")
                    .phone("769514580428")
                    .postalCode("94101")
                    .city(city)
                    .build();
            addressDAO.save(address);

            Customer customer = Customer.builder()
                    .store(store)
                    .address(address)
                    .firstName("HUMAN")
                    .lastName("MARTIN")
                    .isActive(true)
                    .email("human@example.com")
                    .build();
            customerDAO.save(customer);

            session.getTransaction().commit();

            return customer;
        }
    }



}