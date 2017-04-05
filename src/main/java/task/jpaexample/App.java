/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package task.jpaexample;

import task.jpaexample.dao.CourierDao;
import task.jpaexample.dao.CustomerDao;
import task.jpaexample.dao.ProductDao;
import task.jpaexample.dao.PurchaseDao;
import task.jpaexample.dao.jpa.JpaCourierDao;
import task.jpaexample.dao.jpa.JpaCustomerDao;
import task.jpaexample.dao.jpa.JpaProductDao;
import task.jpaexample.dao.jpa.JpaPurchaseDao;
import task.jpaexample.model.*;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author user
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        CustomerDao cdao = new JpaCustomerDao();
        ProductDao pdao = new JpaProductDao();
        PurchaseDao dao = new JpaPurchaseDao();
        CourierDao codao = new JpaCourierDao();

        // utworzenie klienta
        CustomerDTO c1 = new CustomerDTO();
        c1.setFirstName("jan");
        c1.setLastName("kowalski");
        c1.setEmail("jan.kowalski@dot.com");
        // zapis klienta
        cdao.save(c1);

        // utworzenie produktu
        ProductDTO p1 = new ProductDTO();
        p1.setName("monitor");
        p1.setPrice(599.99);
        // zapis produktu
        pdao.save(p1);

        // utworzenie produktu
        ProductDTO p2 = new ProductDTO();
        p2.setName("karta graficzna");
        p2.setPrice(1599.99);
        // zapis produktu
        pdao.save(p2);

        // utworzenie zamowienia
        PurchaseDTO pe = new PurchaseDTO();
        pe.setCustomer(c1);
        PurchaseItemDTO pi = new PurchaseItemDTO();
        pi.setProduct(p1);
        pi.setQuantity(10);
        pe.addPurchaseItem(pi);
        pe.setDeliverDestination("Warszawa");
        pe.setDate(new Date(System.currentTimeMillis()));

        // zapis zamowienia
        dao.save(pe);

        // utworzenie zamowienia
        PurchaseDTO pe2 = new PurchaseDTO();
        pe2.setCustomer(c1);
        PurchaseItemDTO pi2 = new PurchaseItemDTO();
        pi2.setProduct(p2);
        pi2.setQuantity(2);
        pe2.addPurchaseItem(pi2);
        pe2.setDeliverDestination("Gdańsk");
        pe2.setDate(new Date(System.currentTimeMillis()));

        // zapis zamowienia
        dao.save(pe2);

        // utworzenie zamowienia
        PurchaseDTO pe3 = new PurchaseDTO();
        pe3.setCustomer(c1);
        PurchaseItemDTO pi3 = new PurchaseItemDTO();
        pi3.setProduct(p1);
        pi3.setQuantity(15);
        pe3.addPurchaseItem(pi3);
        PurchaseItemDTO pi33 = new PurchaseItemDTO();
        pi33.setProduct(p2);
        pi33.setQuantity(15);
        pe3.addPurchaseItem(pi33);
        pe3.setDeliverDestination("Białystok");
        pe3.setDate(new Date(System.currentTimeMillis()));

        // zapis zamowienia
        dao.save(pe);

        // utworzenie firmy kurierskiej
        CourierDTO co1 = new CourierDTO();
        co1.setName("DPD");
        co1.setAddress("Bialystok");
        co1.setEmail("dpd@dpd.com");
        co1.addPurchase(pe);
        co1.addPurchase(pe2);
        co1.addPurchase(pe3);

        // zapis firmy
        codao.save(co1);

        c1.setFirstName("janek");
        cdao.update(c1);

        // 1a) Wypisanie klientów o danym nazwisku
        System.out.println("Find client by surname:");
        for (CustomerDTO c : cdao.findCustomerBySurname("kowalski")) {
            System.out.println(c);
        }

        // 1b) Wypisanien produktów z w określonym przedziale cenowym
        System.out.println("Find product by price:");
        for (ProductDTO p : pdao.findByPriceBetween(500, 1000)) {
            System.out.println(p);
        }

        // 1c) Wypisanie najdroższych produktów
        System.out.println("Find most expensive product:");
        for (ProductDTO p : pdao.findMostExpensive()) {
            System.out.println(p);
        }

        // 1d) Wypisanie wszystkich zamówień danego klienta
        System.out.println("All customer purchases:");
        for (PurchaseDTO p : dao.findByClient(1)) {
            System.out.println(p);
        }

        // 2a) Wypisanie produktów na które nie ma zamówień
        System.out.println("Products that aren't bought yet:");
        for (ProductDTO p : dao.findNonOrderedProducts()) {
            System.out.println(p);
        }

        // 2b) Wypisanie produktów, dla których łączna ilość zamówionych sztuk jest największa
        System.out.println("Bestsellers:");
        for (ProductDTO p : dao.findBestsellers()) {
            System.out.println(p);
        }

        // 2c) Zmiana ceny wszystkich produktów o określony procent ceny
        System.out.println("Changing price");
        pdao.changeProductPrice(60);

        // 3) Wyświetlenie wszystkich zamówień
        System.out.println("All purchases:");
        for (PurchaseDTO p: dao.findAll()) {
            System.out.println(p);
        }
    }

}
