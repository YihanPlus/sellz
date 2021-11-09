package com.example.onlineSeller.Dao;

import com.example.onlineSeller.Entity.Authorities;
import com.example.onlineSeller.Entity.Customer;
import com.example.onlineSeller.Entity.Product;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void signUp(Customer customer) {
        // assign authorities to user
        Authorities authorities = new Authorities();
        authorities.setAuthorities("ROLE_USER");
        authorities.setEmail(customer.getEmail());
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(authorities);
            session.save(customer);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Customer validateCustomer(String email, String password) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            String sql = "select c from Customer c where c.email=:e and c.password=:w";
            Query query = session.createQuery(sql, Customer.class);
            query.setParameter("e", email);
            query.setParameter("w", password);
            List<Customer> list = query.list();
            System.out.println(list.size());
            if (list.size() != 0) {
                // System.out.println(list.get(0));
                return list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Customer getCustomer(String email) {
        System.out.println("input email" + email);
        Session session = null;

        try {
            session = sessionFactory.openSession();
            Criteria cr = session.createCriteria(Customer.class);
            cr.add(Restrictions.eq("email", email));
            List<Customer> results = cr.list();
            if (results == null || results.isEmpty()) System.out.println("customer does not found");
            else {
                System.out.println("this is customer size: " + results.size());
                return results.get(0);
            }
//            session = sessionFactory.openSession();
//            session.beginTransaction();
//            String hql = "FROM Customer c WHERE c.email = :e";
//            Query query = session.createQuery(hql);
//            query.setParameter("e",email);
//            List<Customer> results = query.list();
//            if (results.size() > 0) {
//                System.out.println(results.get(0).getEmail());
//                return results.get(0);
//            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new Customer();
    }
}
