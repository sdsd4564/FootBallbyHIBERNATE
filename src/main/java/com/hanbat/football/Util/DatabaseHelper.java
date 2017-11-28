package com.hanbat.football.Util;

import com.hanbat.football.Model.Country;
import com.hanbat.football.Model.Team;
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

    public static Team getTeam(String teamName) {
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("FROM Team WHERE name = ?");
        query.setParameter(0, teamName);
        tx.commit();
        return (Team) query.uniqueResult();
    }

    public static List getTeams() {
        Transaction tx = session.beginTransaction();
        Query teamQuery = session.createQuery("FROM Team");
        tx.commit();
        return teamQuery.list();
    }

    public static List<String> getTeamNames() {
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("SELECT name FROM Team");
        tx.commit();
        return ((List<String>) query.list());
    }

    public static Country getCountry(String name) {
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("FROM Country WHERE name = ?");
        query.setParameter(0, name);
        tx.commit();
        return (Country) query.uniqueResult();
    }

    public static List getCountries() {
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from Country ");
        tx.commit();
        return query.list();
    }

    public static List<String> getCountryNames() {
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("SELECT Country .name FROM Country ");
        tx.commit();
        return ((List<String>) query.list());
    }

    public static List getLeagues() {
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from League ");
        tx.commit();
        return query.list();
    }

    public static void saveObject(Object... obj) {
        Transaction tx = session.beginTransaction();
        for (Object object : obj) {
            session.save(object);
        }
        tx.commit();
    }
}
