package com.example.onlineSeller.Dao;

import com.example.onlineSeller.Entity.Seller;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SellerDao {
    @Autowired
    private SessionFactory sessionFactory;

    // create seller
    public void createSeller(Seller seller) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(seller);
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

    // update seller
    public void updateSeller(Seller seller) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(seller);
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

    // delete seller
    public void deleteSeller(Seller seller) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(seller);
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

    // return all sellers who sell input product
    public List<Seller> getAllSellers(String SKU) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            String sql = "select s from Seller s where s.product.SKU=:n";
            Query query = session.createQuery(sql, Seller.class);
            query.setParameter("n", SKU);
            List<Seller> list = query.list();
            if (list != null) return list;
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return new ArrayList<>();
    }
}