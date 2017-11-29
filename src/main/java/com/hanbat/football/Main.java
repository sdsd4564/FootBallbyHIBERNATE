package com.hanbat.football;

import com.hanbat.football.Model.*;
import com.hanbat.football.Model.Enum.FootType;
import com.hanbat.football.Model.Enum.Position;
import com.hanbat.football.Util.DatabaseHelper;
import com.hanbat.football.View.View;

import javax.activation.DataHandler;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static final String ABSOLUTE_PATH = "C:/Users/Hansol/IdeaProjects/FootBallbyHIBERNATE/src/main/resources";
    private static final String PLAYER_PATH = "/Images/player/";
    private static final String COUNTRY_PATH = "/Images/country/";
    private static final String TEAM_PATH = "/Images/team/";
    private static final String LEAGUE_PATH = "/Images/league/";
    private static final SimpleDateFormat MY_DATE = new SimpleDateFormat("yyyyMMdd");

    public static void main(String... args) {

        DatabaseHelper.sessionOpen();

        //try {
///////////이전 자료
            /*Country korea = new Country("대한민국", "서울", COUNTRY_PATH + "korea.png");
            DatabaseHelper.saveObject(korea);

            Country england = new Country("England", "London", COUNTRY_PATH + "england.png");
            DatabaseHelper.saveObject(england);

            League league = new League("프리미어 리그", england, MY_DATE.parse("19920220"), LEAGUE_PATH + "pre_lea.png", "Premier League", "https://www.premierleague.com/");
            england.getLeagues().add(league);
            DatabaseHelper.saveObject(league);

            League league1 = new League("EFL 챔피언쉽", england, MY_DATE.parse("20040101"), LEAGUE_PATH + "efl.png", "EFL Championship", "efl.com");
            england.getLeagues().add(league1);
            DatabaseHelper.saveObject(league1);

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

            Player player1 = new Player("Harry Edward Kane", MY_DATE.parse("19930728"), true, england, FootType.RIGHT, Position.FW, 188, 86, PLAYER_PATH + "herry.png", new HashSet<>());
            DatabaseHelper.saveObject(player1);

            Team team = new Team("Tottenham Hotspur FC", "Mauricio Pochittino", 4, TEAM_PATH + "tot.png", new Date(), england, stadium, league, new HashSet<>());
            DatabaseHelper.saveObject(team);
            league.getTeams().add(team);

            TeamPlayer teamPlayer = new TeamPlayer();
            teamPlayer.setPlayer(player);
            teamPlayer.setTeam(team);
            team.getPlayers().add(teamPlayer);
            player.getTeams().add(teamPlayer);
            DatabaseHelper.saveObject(teamPlayer);

            teamPlayer = new TeamPlayer();
            teamPlayer.setPlayer(player1);
            teamPlayer.setTeam(team);
            team.getPlayers().add(teamPlayer);
            player1.getTeams().add(teamPlayer);
            DatabaseHelper.saveObject(teamPlayer);*/

            /*Country argentina = new Country("Argentina", "Buenos Aires", ABSOLUTE_PATH + "/images/country/argentina.jpg");
            DatabaseHelper.saveObject(argentina);

            Country frence = new Country("Frence", "Paris", ABSOLUTE_PATH + "/images/country/frence.png");
            DatabaseHelper.saveObject(frence);

            Country brasil = new Country("Brasil", "Brasilia", ABSOLUTE_PATH + "/images/country//Brasil.jpg");
            DatabaseHelper.saveObject(brasil);

            Country portugal = new Country("Portugal", "Lisbon", ABSOLUTE_PATH + "/images/country//portugal.png");
            DatabaseHelper.saveObject(portugal);

            Country turky = new Country("Turkiye", "Ankara", ABSOLUTE_PATH + "/images/country/turky.png");
            DatabaseHelper.saveObject(turky);


            Team barcelona = new Team("Barcelona FC", "Ernesto Valverde Tejedor", 1, ABSOLUTE_PATH + "/images/team/barcelona.png", MY_DATE.parse(18991129), argentina, null, laliga, new HashSet<>());
            DatabaseHelper.saveObject(barcelona);

            Team crystalPalace = new Team("Crystal Palace F.C.", "Roy Hodgson", 20, ABSOLUTE_PATH + "/images/team/crystalPalace.png",
                    MY_DATE.parse(19050910), england, null, league, new HashSet<>());
            DatabaseHelper.saveObject(crystalPalace);

            Team swansea = new Team("Swansea City A.F.C.", "Paul Clement", 19, ABSOLUTE_PATH + "/images/team/swansea.png",
                    MY_DATE.parse(19120101), england, null, league, new HashSet<>());
            DatabaseHelper.saveObject(swansea);

            Team westHam = new Team("West Ham United F.C.", "David William Moyes", 18, ABSOLUTE_PATH + "/images/team/westHam.png",
                    MY_DATE.parse(18950629), england, null, league, new HashSet<>());
            DatabaseHelper.saveObject(westHam);

            Team everton = new Team("Everton F.C.", "David Unsworth", 17, ABSOLUTE_PATH + "/images/team/everton.png",
                    MY_DATE.parse(18780101), england, null, league, new HashSet<>());
            DatabaseHelper.saveObject(everton);

            Team albion = new Team("West Bromwich Albion F.C.", "Gary Megson", 16, ABSOLUTE_PATH + "/images/team/albion.png",
                    MY_DATE.parse(18780101), england, null, league, new HashSet<>());
            DatabaseHelper.saveObject(albion);

            Team stock = new Team("Stoke City F.C.", "Mark Hughes", 15, ABSOLUTE_PATH + "/images/team/stock.png",
                    MY_DATE.parse(18630101), england, null, league, new HashSet<>())
            DatabaseHelper.saveObject(stock);

            Team bournemouth = new Team("A.F.C. Bournemouth", "Eddie Howe", 14, ABSOLUTE_PATH + "/images/team/bournemouth.png",
                    MY_DATE.parse(1899), england, null, league, new HashSet<>());
            DatabaseHelper.saveObject(bournemouth);


            Player messi_cuccittini = new Player("Lionel Andrés Messi Cuccittini", MY_DATE.parse(19870624),
                    true, argentina, FootType.LEFT, Position.FW, ABSOLUTE_PATH + "/images/player/messi.jpg",
                    new HashSet<>());
            DatabaseHelper.saveObject(messi_cuccittini);

            Player dembele = new Player("Ousmane Dembele", MY_DATE.parse(19970515),
                    true, frence, FootType.LEFT, Position.FW, ABSOLUTE_PATH + "/images/player/dembele.jpg",
                    new HashSet<>());
            DatabaseHelper.saveObject(dembele);

            Player paulinho = new Player("Paulinho", MY_DATE.parse(19880725),
                    true, brasil, FootType.LEFT, Position.MF, ABSOLUTE_PATH + "/images/player/paulinho.jpg",
                    new HashSet<>());
            DatabaseHelper.saveObject(paulinho);

            Player lujan = new Player("Andres Iniesta Lujan", MY_DATE.parse(19840511),
                    true, spain, FootType.LEFT, Position.MF, ABSOLUTE_PATH + "/images/player/lujan.jpg",
                    new HashSet<>());
            DatabaseHelper.saveObject(lujan);

            Player semedo = new Player("Nélson Cabral Semedo", MY_DATE.parse(19840511),
                    true, portugal, FootType.LEFT, Position.RB, ABSOLUTE_PATH + "/images/player/semedo.jpg",
                    new HashSet<>());
            DatabaseHelper.saveObject(semedo);


            Player turan = new Player("Arda Turan", MY_DATE.parse(19870130),
                    true, turky, FootType.LEFT, Position.LW, ABSOLUTE_PATH + "/images/player/turan.jpg",
                    new HashSet<>());
            DatabaseHelper.saveObject(turan);

            Player nascimento = new Player("Rafael Alcântara do Nascimento", MY_DATE.parse(19930212),
                    true, brasil, FootType.LEFT, Position.MF, ABSOLUTE_PATH + "/images/player/nascimento.jpg");
            DatabaseHelper.saveObject(nascimento);

            TeamPlayer teamPlayer = new TeamPlayer();
            teamPlayer.setPlayer(messi_cuccittini);
            teamPlayer.setTeam(barcelona);
            barcelona.getPlayers().add(teamPlayer);
            messi_cuccittini.getTeams().add(teamPlayer);
            DatabaseHelper.saveObject(teamPlayer);

            teamPlayer.setPlayer(dembele);
            teamPlayer.setTeam(barcelona);
            barcelona.getPlayers().add(teamPlayer);
            dembele.getTeams().add(teamPlayer);
            DatabaseHelper.saveObject(teamPlayer);

            teamPlayer.setPlayer(paulinho);
            teamPlayer.setTeam(barcelona);
            barcelona.getPlayers().add(teamPlayer);
            paulinho.getTeams().add(teamPlayer);
            DatabaseHelper.saveObject(teamPlayer);

            teamPlayer.setPlayer(lujan);
            teamPlayer.setTeam(barcelona);
            barcelona.getPlayers().add(teamPlayer);
            lujan.getTeams().add(teamPlayer);
            DatabaseHelper.saveObject(teamPlayer);

            teamPlayer.setPlayer(semedo);
            teamPlayer.setTeam(barcelona);
            barcelona.getPlayers().add(teamPlayer);
            semedo.getTeams().add(teamPlayer);
            DatabaseHelper.saveObject(teamPlayer);

            teamPlayer.setPlayer(turan);
            teamPlayer.setTeam(barcelona);
            barcelona.getPlayers().add(teamPlayer);
            turan.getTeams().add(teamPlayer);
            DatabaseHelper.saveObject(teamPlayer);

            teamPlayer.setPlayer(nascimento);
            teamPlayer.setTeam(barcelona);
            barcelona.getPlayers().add(teamPlayer);
            nascimento.getTeams().add(teamPlayer);
            DatabaseHelper.saveObject(teamPlayer);*/


//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
        View.main(args);

    }
}
