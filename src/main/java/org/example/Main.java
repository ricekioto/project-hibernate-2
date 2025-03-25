package org.example;

import org.example.config.MySessionFactory;
import org.example.dao.*;
import org.example.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

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
    private final PaymentDAO paymentDAO;
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
        paymentDAO = new PaymentDAO(sessionFactory);
        rentalDAO = new RentalDAO(sessionFactory);
        staffDAO = new StaffDAO(sessionFactory);
        storeDAO = new StoreDAO(sessionFactory);
    }

    public static void main(String[] args) {
        Main main = new Main();
//        Customer customer = main.createCustomer();
//        main.customerReturnInbentoryToStore();
//        main.customerRentInventory();
        main.shootingNewMovie();
    }

    private void shootingNewMovie() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Language language = languageDAO.getItems(0, 1).get(0);
            List<Category> categories = categoryDAO.getItems(0, 5);
            List<Actor> actors = actorDAO.getItems(0, 20);

            Film film = Film.builder()
                    .title("EIGHTY NINE")
                    .year(2025)
                    .languageId(language)
                    .rentalDuration((byte) 5)
                    .rentalRate(BigDecimal.valueOf(6.65))
                    .length((short) 144)
                    .replacementCost(BigDecimal.valueOf(20.0))
                    .rating(Rating.PG13)
                    .specialFeatures(Feature.BEHIND_THE_SCENES.getValue())
                    .actors(new HashSet<>(actors))
                    .categories(new HashSet<>(categories))
                    .build();
            filmDAO.save(film);
            session.getTransaction().commit();
        }
    }

    public void customerRentInventory() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Customer customer = customerDAO.getItems(0, 1).get(0);
            Film film = filmDAO.getFirstAvailableFilmRent();
            Store store = storeDAO.getItems(0, 1).get(0);
            Inventory inventory = Inventory.builder()
                    .film(film)
                    .store(store)
                    .build();
            inventoryDAO.save(inventory);

            Staff staff = store.getStaff();
            Rental rental = Rental.builder()
                    .rentalDate(LocalDateTime.now())
                    .inventory(inventory)
                    .customer(customer)
                    .staff(staff)
                    .build();
            rentalDAO.save(rental);

            Payment payment = Payment.builder()
                    .customer(customer)
                    .staff(staff)
                    .rental(rental)
                    .amount(BigDecimal.valueOf(200.22))
                    .build();
            paymentDAO.save(payment);

            session.getTransaction().commit();
        }
    }

    public void customerReturnInbentoryToStore() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Rental rental = rentalDAO.getAnyUnreturnedRental();
            rental.setReturnDate(LocalDateTime.now());
            rentalDAO.save(rental);
            System.out.println("rental = " + rental);

            session.getTransaction().commit();
        }
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