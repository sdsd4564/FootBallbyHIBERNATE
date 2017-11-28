package com.hanbat.football;

import com.hanbat.football.Model.*;
import com.hanbat.football.Model.Enum.FootType;
import com.hanbat.football.Model.Enum.Position;
import com.hanbat.football.Util.DatabaseHelper;
import com.hanbat.football.View.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

public class Main {
    public static final String ABSOLUTE_PATH = "C:/Users/Encho/Desktop/football/src/main/resources";
    private static final String PLAYER_PATH = "/Images/player/";
    private static final String COUNTRY_PATH = "/Images/country/";
    private static final String TEAM_PATH = "/Images/team/";
    private static final String LEAGUE_PATH = "/Images/league/";
    private static final SimpleDateFormat MY_DATE = new SimpleDateFormat("yyyyMMdd");

    public static void main(String... args) {

        DatabaseHelper.sessionOpen();

//        try {
//
//            Country korea = new Country("대한민국", "서울", COUNTRY_PATH + "korea.png");
//            DatabaseHelper.saveObject(korea);
//
//            Country england = new Country("England", "London", COUNTRY_PATH + "england.png");
//            DatabaseHelper.saveObject(england);
//
//            League league = new League("프리미어 리그", england, MY_DATE.parse("19920220"), LEAGUE_PATH + "pre_lea.png", "Premier League", "https://www.premierleague.com/");
//            england.getLeagues().add(league);
//            DatabaseHelper.saveObject(league);
//
//            League league1 = new League("EFL 챔피언쉽", england, MY_DATE.parse("20040101"), LEAGUE_PATH + "efl.png", "EFL Championship", "efl.com");
//            england.getLeagues().add(league1);
//            DatabaseHelper.saveObject(league1);
//
//            Stadium stadium = new Stadium("Wembley Stadium", 90000, new Date(), england);
//            DatabaseHelper.saveObject(stadium);
//
//            Player player = new Player("손흥민",
//                    MY_DATE.parse("19920708"),
//                    true,
//                    korea,
//                    FootType.BOTH, Position.FW,
//                    183, 76,
//                    PLAYER_PATH + "son.png", new HashSet<>());
//            DatabaseHelper.saveObject(player);
//
//            Player player1 = new Player("Harry Edward Kane", MY_DATE.parse("19930728"), true, england, FootType.RIGHT, Position.FW, 188, 86, PLAYER_PATH + "herry.png", new HashSet<>());
//            DatabaseHelper.saveObject(player1);
//
//            Team team = new Team("Tottenham Hotspur FC", "Mauricio Pochittino", 4, TEAM_PATH + "tot.png", new Date(), england, stadium, league, new HashSet<>());
//            DatabaseHelper.saveObject(team);
//            league.getTeams().add(team);
//
//            TeamPlayer teamPlayer = new TeamPlayer();
//            teamPlayer.setPlayer(player);
//            teamPlayer.setTeam(team);
//            team.getPlayers().add(teamPlayer);
//            player.getTeams().add(teamPlayer);
//            DatabaseHelper.saveObject(teamPlayer);
//
//            teamPlayer = new TeamPlayer();
//            teamPlayer.setPlayer(player1);
//            teamPlayer.setTeam(team);
//            team.getPlayers().add(teamPlayer);
//            player1.getTeams().add(teamPlayer);
//            DatabaseHelper.saveObject(teamPlayer);
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        View.main(args);

    }
}
