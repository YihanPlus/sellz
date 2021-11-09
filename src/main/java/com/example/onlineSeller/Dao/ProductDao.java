package com.example.onlineSeller.Dao;

import com.example.onlineSeller.Entity.Customer;
import com.example.onlineSeller.Entity.Product;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ProductDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private CustomerDao customerDao;

    // generate list of product under the customer
    public List<Product> getAllProducts(String email) {
        Customer customer = customerDao.getCustomer(email);
        System.out.println("this is customer id" + customer.getUserId());
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria cr = session.createCriteria(Product.class);
            cr.add(Restrictions.eq("customer", customer));
            List<Product> results = cr.list();
            if (results == null) System.out.println("product list null");
            else if (results.isEmpty()) System.out.println("product list empty");
            if (results != null) {
                System.out.println("this is result size: " + results.size());
                return results;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return new ArrayList<>();
    }

    // create new product
    public void createProduct(Product product) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(product);
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

    // update product
    public void updateProduct(Product product) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(product);
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

    // delete product
    public void deleteProduct(Product product) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(product);
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

    public Product getProduct(String sku) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria cr = session.createCriteria(Product.class);
            cr.add(Restrictions.eq("SKU", sku));

            List<Product> results = cr.list();
            if (results != null) {
                return results.get(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return new Product();
    }


}
