package com.hanbat.football;

import com.hanbat.football.Model.*;
import com.hanbat.football.Model.Enum.FootType;
import com.hanbat.football.Model.Enum.Position;
import com.hanbat.football.Util.HibernateUtil;
import com.hanbat.football.View.View;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.HashSet;

public class Main {

    public static void main(String... args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {

            tx = session.beginTransaction();

            Country korea = new Country("Korea", "Seoul");
            session.save(korea);

            Country england = new Country("England", "London");
            session.save(england);

            League league = new League("Premier League", england, new Date());
            session.save(league);

            Stadium stadium = new Stadium("Wembley Stadium", 90000, new Date(), england);
            session.save(stadium);

            Player player = new Player("Son Heung Min", 25, true, korea, FootType.BOTH, Position.FW, 183, 76, null, new HashSet<>());
            session.save(player);
//
            Team team = new Team("Tottenham Hotspur FC", "Mauricio Pochittino", 4, null, new Date(), england, stadium, league, new HashSet<>());
            session.save(team);

            TeamPlayer teamPlayer = new TeamPlayer();
            teamPlayer.setPlayer(player);
            teamPlayer.setTeam(team);
            session.save(teamPlayer);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        sessionFactory.close();

        View.main(args);
    }
}
