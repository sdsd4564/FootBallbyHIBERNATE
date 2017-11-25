package com.hanbat.football;

import com.hanbat.football.Model.*;
import com.hanbat.football.Model.Enum.FootType;
import com.hanbat.football.Model.Enum.Position;
import com.hanbat.football.Util.DatabaseHelper;
import com.hanbat.football.View.View;

import java.util.Date;
import java.util.HashSet;

public class Main {
    public static final String FILE_PATH = "//C:\\Users\\Encho\\Desktop\\football\\src\\main\\";

    public static void main(String... args) {

//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Transaction tx = null;

//        try (Session session = sessionFactory.openSession()) {
//
//            tx = session.beginTransaction();
        DatabaseHelper.sessionOpen();

        Country korea = new Country("Korea", "Seoul");
        DatabaseHelper.saveObject(korea);

        Country england = new Country("England", "London");
        DatabaseHelper.saveObject(england);

        League league = new League("Premier League", england, new Date());
        DatabaseHelper.saveObject(league);

        Stadium stadium = new Stadium("Wembley Stadium", 90000, new Date(), england);
        DatabaseHelper.saveObject(stadium);

        Player player = new Player("Son Heung Min", 25, true, korea, FootType.BOTH, Position.FW, 183, 76, null, new HashSet<>());
        DatabaseHelper.saveObject(player);

        Team team = new Team("Tottenham Hotspur FC", "Mauricio Pochittino", 4, null, new Date(), england, stadium, league, new HashSet<>());
        DatabaseHelper.saveObject(team);

        TeamPlayer teamPlayer = new TeamPlayer();
        teamPlayer.setPlayer(player);
        teamPlayer.setTeam(team);
        DatabaseHelper.saveObject(teamPlayer);
//
//            tx.commit();
//        } catch (Exception e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        }
//        sessionFactory.close();

        View.main(args);
    }
}
