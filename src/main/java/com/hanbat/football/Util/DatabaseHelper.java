package com.hanbat.football.Util;

import com.hanbat.football.Model.Country;
import com.hanbat.football.Model.League;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DatabaseHelper {
    private static Session session;

    public static void sessionOpen() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public static void sessionClose() {
        session.close();
        HibernateUtil.close();
    }

    public static List getPlayerNames() {
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("FROM Player ");
        tx.commit();
        return query.list();
    }

    public static List getCountries() {
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from Country ");
        tx.commit();
        return query.list();
    }

    public static void saveObject(Object obj) {
        Transaction tx = session.beginTransaction();
        session.save(obj);
        tx.commit();
    }
}
