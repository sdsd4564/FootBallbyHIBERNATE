package com.hanbat.football;

import com.hanbat.football.Model.*;
import com.hanbat.football.Model.Enum.FootType;
import com.hanbat.football.Model.Enum.Position;
import com.hanbat.football.Util.DatabaseHelper;
import com.hanbat.football.View.View;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

public class Main {
    private static final String PLAYER_PATH = "/Images/player/";
    private static final String COUNTRY_PATH = "/Images/country/";
    private static final String TEAM_PATH = "/Images/team/";
    private static final String LEAGUE_PATH = "/Images/league/";
    private static final SimpleDateFormat MY_DATE = new SimpleDateFormat("yyyyMMdd");

    public static void main(String... args) {

        DatabaseHelper.sessionOpen();

        try {
            Country korea = new Country("대한민국", "서울", COUNTRY_PATH + "korea.png");
            DatabaseHelper.saveObject(korea);

            Country england = new Country("England", "London");
            DatabaseHelper.saveObject(england);

            Country spain = new Country("Spain", "Madrid");
            DatabaseHelper.saveObject(spain);

            League league = new League("프리미어 리그", england, MY_DATE.parse("19920220"), LEAGUE_PATH + "pre_lea.png", "Premier League", "https://www.premierleague.com/");
            DatabaseHelper.saveObject(league);

            Stadium stadium = new Stadium("Wembley Stadium", 90000, new Date(), england);
            DatabaseHelper.saveObject(stadium);

            Player player = new Player("손흥민",
                    MY_DATE.parse("19920708"),
                    true,
                    korea,
                    FootType.BOTH, Position.FW,
                    183, 76,
                    PLAYER_PATH + "son.png", new HashSet<>());
            DatabaseHelper.saveObject(player);

            Player player1 = new Player("Harry Edward Kane",
                    MY_DATE.parse("19930728"),
                    true,
                    england,
                    FootType.RIGHT, Position.FW,
                    188, 86,
                    PLAYER_PATH + "herry.png", new HashSet<>());
            DatabaseHelper.saveObject(player1);

            Player player2 = new Player("Fernando Javier Llorente Torres",
                    MY_DATE.parse("19850226"),
                    true,
                    spain,
                    FootType.RIGHT, Position.FW,
                    195, 90,
                    PLAYER_PATH + "torres.jpg", new HashSet<>());
            DatabaseHelper.saveObject(player2);

            Player player3 = new Player("Francisco Román Alarcón Suárez",
                    MY_DATE.parse("19920421"),
                    true,
                    spain,
                    FootType.RIGHT, Position.AM,
                    176,74,
                    PLAYER_PATH + "isco.png", new HashSet<>());
            DatabaseHelper.saveObject(player3);

            Team tottenham = new Team("Tottenham Hotspur FC", "Mauricio Pochittino", 4, TEAM_PATH + "tot.png", new Date(), england, stadium, league, new HashSet<>());
            DatabaseHelper.saveObject(tottenham);
            league.getTeams().add(tottenham);

            Team realmadrid = new Team("Real Madrid CF", "Zinedine Yazid Zidane", 1, TEAM_PATH + "realmadrid.png", new Date(), england, stadium, league, new HashSet<>());
            DatabaseHelper.saveObject(realmadrid);
            league.getTeams().add(realmadrid);

            TeamPlayer tottenhamPlayer = new TeamPlayer();
            tottenhamPlayer.setPlayer(player);
            tottenhamPlayer.setTeam(tottenham);
            DatabaseHelper.saveObject(tottenhamPlayer);

            TeamPlayer tottenhamPlayer1 = new TeamPlayer();
            tottenhamPlayer1.setPlayer(player1);
            tottenhamPlayer1.setTeam(tottenham);
            DatabaseHelper.saveObject(tottenhamPlayer1);

            TeamPlayer tottenhamPlayer2 = new TeamPlayer();
            tottenhamPlayer2.setPlayer(player2);
            tottenhamPlayer2.setTeam(tottenham);
            DatabaseHelper.saveObject(tottenhamPlayer2);

            tottenham.getPlayers().add(tottenhamPlayer);
            tottenham.getPlayers().add(tottenhamPlayer1);
            tottenham.getPlayers().add(tottenhamPlayer2);

            player.getTeams().add(tottenhamPlayer);
            player1.getTeams().add(tottenhamPlayer1);
            player2.getTeams().add(tottenhamPlayer2);

            TeamPlayer realmadridPlayer = new TeamPlayer();
            realmadridPlayer.setPlayer(player3);
            realmadridPlayer.setTeam(realmadrid);
            DatabaseHelper.saveObject(realmadridPlayer);

            realmadrid.getPlayers().add(realmadridPlayer);

            player3.getTeams().add(realmadridPlayer);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        View.main(args);
    }
}
