package com.hanbat.football;

import com.hanbat.football.Model.League;
import com.hanbat.football.Model.Player;
import com.hanbat.football.Model.Team;
import com.hanbat.football.Model.TeamPlayer;
import com.hanbat.football.Util.DatabaseHelper;
import com.hanbat.football.View.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static final String ABSOLUTE_PATH = "C:/Users/Encho/Desktop/football/src/main/resources";
    private static final String PLAYER_PATH = "/Images/player/";
    private static final String COUNTRY_PATH = "/Images/country/";
    private static final String TEAM_PATH = "/Images/team/";
    private static final String LEAGUE_PATH = "/Images/league/";
    private static final SimpleDateFormat MY_DATE = new SimpleDateFormat("yyyyMMdd");

    public static void main(String... args) throws ParseException {

        DatabaseHelper.sessionOpen();


        View.main(args);

        /*League fifa = new League("국제 축구 연맹", DatabaseHelper.getCountry("Belgium"), MY_DATE.parse("19040521"), LEAGUE_PATH + "fifa.png", "FIFA", "http://www.fifa.com/");
        DatabaseHelper.saveObject(fifa);

        Team korea = new Team("대한민국 축구 국가대표 팀", "신태용", 59, TEAM_PATH + "kor.png", MY_DATE.parse("19330919"), DatabaseHelper.getCountry("대한민국"),
                null, fifa, new HashSet<>());

        TeamPlayer teamPlayer = new TeamPlayer(korea, DatabaseHelper.getPlayer("손흥민"));
        DatabaseHelper.getPlayer("손흥민").getTeams().add(teamPlayer);
        korea.getPlayers().add(teamPlayer);
        DatabaseHelper.saveObject(korea, teamPlayer);*/
        /*try {
            Country korea = new Country("대한민국", "서울", COUNTRY_PATH + "korea.png");
            DatabaseHelper.saveObject(korea);

            Country england = new Country("England", "London", COUNTRY_PATH + "england.png");
            DatabaseHelper.saveObject(england);

            League league = new League("프리미어 리그", england, MY_DATE.parse("19920220"), LEAGUE_PATH + "pre_lea.png", "Premier League", "https://www.premierleague.com/");
            england.getLeagues().add(league);
            DatabaseHelper.saveObject(league);

            League league1 = new League("EFL 챔피언쉽", england, MY_DATE.parse("20040101"), LEAGUE_PATH + "efl.png", "EFL Championship", "efl.com");
            england.getLeagues().add(league1);
            DatabaseHelper.saveObject(league1);

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

            Team team = new Team("Tottenham Hotspur FC", "Mauricio Pochittino", 5, TEAM_PATH + "tot.png", new Date(), england, null, league, new HashSet<>());
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
            DatabaseHelper.saveObject(teamPlayer);

            Country spain = new Country("Spain", "Madrid", COUNTRY_PATH + "spain.png");
            DatabaseHelper.saveObject(spain);

            Team manCity = new Team("Manchester City FC", "Josep Guardiloa", 1, TEAM_PATH + "mancity.png", MY_DATE.parse("18940101"),
                    DatabaseHelper.getCountry("England"), null, DatabaseHelper.getLeague("프리미어 리그"), new HashSet<>());
            league.getTeams().add(manCity);

            dummyData(new Team("Manchester United FC", "Jose Mourinho", 2, TEAM_PATH + "manu.png", MY_DATE.parse("18780101"), england, null, league, new HashSet<>()), league);

            dummyData(new Team("Chelsea FC", "Antonio Conte", 3, TEAM_PATH + "chelsea.png", MY_DATE.parse("19050310"), england, null, league, new HashSet<>()), league);

            dummyData(new Team("Arsenal FC", "Arsene Wenger", 4, TEAM_PATH + "arsenal.png", MY_DATE.parse("18860101") ,england, null, league, new HashSet<>()), league);

            dummyData(new Team("Liverpool FC", "Jürgen Klopp", 6, TEAM_PATH + "liverpool.png", MY_DATE.parse("18920603"), england, null, league, new HashSet<>()), league);

            dummyData(new Team("Burnley FC", "Sean Dyche", 7, TEAM_PATH + "burnley.png", MY_DATE.parse("18820501"), england, null, league, new HashSet<>()), league);

            dummyData(new Team("Watford FC", "Marco Silva", 8, TEAM_PATH + "watford.png", MY_DATE.parse("18810101"), england, null, league, new HashSet<>()), league);

            dummyData(new Team("Leicester City FC", "Claude Puel", 9, TEAM_PATH + "leicester.png", MY_DATE.parse("18840101"), england, null, league, new HashSet<>()), league);

            dummyData(new Team("Brighton & Hove Albion FC", "Chris Hughton", 10, TEAM_PATH + "bnh.png", MY_DATE.parse("19010624"), england, null, league, new HashSet<>()), league);

            dummyData(new Team("Southampton FC", "Mauricio Pellegrino", 11, TEAM_PATH + "southampton.png", MY_DATE.parse("18851121"), england, null, league, new HashSet<>()), league);

            dummyData(new Team("Newcastle United FC", "Rafael Benitez", 12, TEAM_PATH + "newcastle.png", MY_DATE.parse("18921209"), england, null, league, new HashSet<>()), league);

            dummyData(new Team("Huddersfield Town FC", "David Wagner", 13, TEAM_PATH + "huddersfield.png", MY_DATE.parse("19080815"), england, null, league, new HashSet<>()), league);


            Country chile = new Country("Chile", "Santiago", COUNTRY_PATH + "chile.png");

            Player claudioBravo = new Player("Claudio Andrés Bravo Muñoz", MY_DATE.parse("19830413"), true, chile, FootType.RIGHT, Position.GK,
                    184, 80, PLAYER_PATH + "claudio_bravo.png", new HashSet<>());

            teamPlayer = new TeamPlayer(manCity, claudioBravo);
            manCity.getPlayers().add(teamPlayer);
            claudioBravo.getTeams().add(teamPlayer);
            DatabaseHelper.saveObject(manCity, chile, claudioBravo, teamPlayer);

            Player kyleWalker = new Player("Kyle Andrew Walker", MY_DATE.parse("19900528"), true, DatabaseHelper.getCountry("England"), FootType.RIGHT,
                    Position.RB, 183, 83, PLAYER_PATH + "kyle_walker.png", new HashSet<>());

            teamPlayer = new TeamPlayer(manCity, kyleWalker);
            manCity.getPlayers().add(teamPlayer);
            kyleWalker.getTeams().add(teamPlayer);
            DatabaseHelper.saveObject(kyleWalker, teamPlayer);

            Country brasil = new Country("Brazil", "São Paulo", COUNTRY_PATH + "brazil.png");

            Player danilo = new Player("Danilo Luiz da Silva", MY_DATE.parse("19910715"), true, brasil, FootType.RIGHT, Position.RB, 184, 78,
                    PLAYER_PATH + "danilo_luiz.png", new HashSet<>());

            teamPlayer = new TeamPlayer(manCity, danilo);
            manCity.getPlayers().add(teamPlayer);
            danilo.getTeams().add(teamPlayer);
            DatabaseHelper.saveObject(brasil, danilo, teamPlayer);

            Country belgium = new Country("Belgium", "Brussels", COUNTRY_PATH + "belgium.png");

            Player vincentKompany = new Player("Vincent Jean Mpoy Kompany", MY_DATE.parse("19840410"), true, belgium, FootType.RIGHT, Position.CB,
                    193, 85, PLAYER_PATH + "vincent_kompany.png", new HashSet<>());

            teamPlayer = new TeamPlayer(manCity, vincentKompany);
            manCity.getPlayers().add(teamPlayer);
            vincentKompany.getTeams().add(teamPlayer);
            DatabaseHelper.saveObject(belgium, vincentKompany, teamPlayer);

            Player johnStones = new Player("John Stones", MY_DATE.parse("19940528"), true, DatabaseHelper.getCountry("England"), FootType.RIGHT, Position.CB,
                    188, 70, PLAYER_PATH + "john_stones.png", new HashSet<>());

            teamPlayer = new TeamPlayer(manCity, johnStones);
            manCity.getPlayers().add(teamPlayer);
            johnStones.getTeams().add(teamPlayer);
            DatabaseHelper.saveObject(johnStones, teamPlayer);

            Country jamaica = new Country("Jamaica", "Kingston", COUNTRY_PATH + "jamaica.png");

            Player raheem = new Player("Raheem Shaquille Sterling", MY_DATE.parse("19941208"), true, jamaica, FootType.RIGHT, Position.LW, 170, 69,
                    PLAYER_PATH + "raheem_sterling.png", new HashSet<>());

            teamPlayer = new TeamPlayer(manCity, raheem);
            manCity.getPlayers().add(teamPlayer);
            raheem.getTeams().add(teamPlayer);
            DatabaseHelper.saveObject(jamaica, raheem, teamPlayer);

            Country germany = new Country("Germany", "Berlin", COUNTRY_PATH + "germany.png");

            Player kwondogan = new Player("İlkay Gündoğan", MY_DATE.parse("19901024"), true, germany, FootType.RIGHT, Position.CM, 180, 79,
                    PLAYER_PATH + "kwondogan.png", new HashSet<>());

            teamPlayer = new TeamPlayer(manCity, kwondogan);
            manCity.getPlayers().add(teamPlayer);
            kwondogan.getTeams().add(teamPlayer);
            DatabaseHelper.saveObject(germany, kwondogan, teamPlayer);

            Country argentina = new Country("Argentina", "Buenos Aires", COUNTRY_PATH + "argentina.png");

            Player sergioAguero = new Player("Sergio Leonel Agüero del Castillo", MY_DATE.parse("19880602"), true, argentina, FootType.RIGHT, Position.FW,
                    172, 70, PLAYER_PATH + "sergio_aguero.png", new HashSet<>());

            teamPlayer = new TeamPlayer(manCity, sergioAguero);
            manCity.getPlayers().add(teamPlayer);
            sergioAguero.getTeams().add(teamPlayer);
            DatabaseHelper.saveObject(argentina, sergioAguero, teamPlayer);

            Country frence = new Country("France", "Paris", COUNTRY_PATH + "france.png");

            Country turky = new Country("Turkiye", "Ankara", ABSOLUTE_PATH + "/images/country/turky.png");
            DatabaseHelper.saveObject(turky);


            Player eliaquim = new Player("Eliaquim Hans Mangala", MY_DATE.parse("19910213"), true, frence, FootType.LEFT, Position.DF,
                    187, 84, PLAYER_PATH + "eliaquim.png", new HashSet<>());

            teamPlayer = new TeamPlayer(manCity, eliaquim);
            manCity.getPlayers().add(teamPlayer);
            eliaquim.getTeams().add(teamPlayer);
            DatabaseHelper.saveObject(frence, eliaquim, teamPlayer);

            Player kevinDe = new Player("Kevin De Bruyne", MY_DATE.parse("19910628"), true, belgium, FootType.BOTH, Position.CM, 181, 76,
                    PLAYER_PATH + "kevin_de_braw.png", new HashSet<>());

            teamPlayer = new TeamPlayer(manCity, kevinDe);
            manCity.getPlayers().add(teamPlayer);
            kevinDe.getTeams().add(teamPlayer);
            DatabaseHelper.saveObject(kevinDe, teamPlayer);

            Player fabianDelph = new Player("Fabian Delph", MY_DATE.parse("19891121"), true, DatabaseHelper.getCountry("England"), FootType.LEFT, Position.LB,
                    175, 72, PLAYER_PATH + "fabian_delph.png", new HashSet<>());

            teamPlayer = new TeamPlayer(manCity, fabianDelph);
            manCity.getPlayers().add(teamPlayer);
            fabianDelph.getTeams().add(teamPlayer);
            DatabaseHelper.saveObject(fabianDelph, teamPlayer);

            Player leroySane = new Player("Leroy Sané", MY_DATE.parse("19960111"), true, germany, FootType.LEFT, Position.RW, 183, 75,
                    PLAYER_PATH + "leroy_sane.png", new HashSet<>());

            teamPlayer = new TeamPlayer(manCity, leroySane);
            manCity.getPlayers().add(teamPlayer);
            leroySane.getTeams().add(teamPlayer);
            DatabaseHelper.saveObject(leroySane, teamPlayer);

            Country portugal = new Country("Portugal", "Lisbon", COUNTRY_PATH + "portugal.png");
            DatabaseHelper.saveObject(portugal);

            dummyData(new Player("Bernardo Mota Veiga de Carvalho e Silva", MY_DATE.parse("19940810"), true, portugal, FootType.LEFT, Position.AM, 173, 64,
                    PLAYER_PATH + "bernardo_silva.png", new HashSet<>()), manCity);

            dummyData(new Player("David Josué Jiménez Silva", MY_DATE.parse("19860108"), true, spain, FootType.LEFT, Position.CM,
                    170, 67, PLAYER_PATH + "david_silva.png", new HashSet<>()), manCity);

            dummyData(new Player("Benjamin Mendy", MY_DATE.parse("19940717"), true, frence, FootType.LEFT, Position.LB, 185, 85,
                    PLAYER_PATH + "ben_mendy.png", new HashSet<>()), manCity);

            dummyData(new Player("Abdul-Nasir Oluwatosin Tosin Adarabioyo", MY_DATE.parse("19970924"), true, DatabaseHelper.getCountry("England"),
                    FootType.RIGHT, Position.CB, 196, 80, PLAYER_PATH + "tosin_adara.png", new HashSet<>()), manCity);

            dummyData(new Player("Fernando Luiz Roza", MY_DATE.parse("19850504"), true, brasil, FootType.RIGHT, Position.DM, 177, 67,
                    PLAYER_PATH + "fernandinho.png", new HashSet<>()), manCity);

            dummyData(new Player("Nicolás Hernán Gonzalo Otamendi", MY_DATE.parse("19880212"), true, argentina, FootType.RIGHT, Position.CB, 183, 81,
                    PLAYER_PATH + "nicolas_ota.png", new HashSet<>()), manCity);

            dummyData(new Player("Ederson Santana de Moraes", MY_DATE.parse("19930817"), true, brasil, FootType.LEFT, Position.GK, 188, 89,
                    PLAYER_PATH + "ederson_santana.png", new HashSet<>()), manCity);

            dummyData(new Player("Gabriel Fernando de Jesus", MY_DATE.parse("19970403"), true, brasil, FootType.RIGHT, Position.FW, 175, 73,
                    PLAYER_PATH + "gabriel_de_jesus.png", new HashSet<>()), manCity);

            Country ivoryCost = new Country("Ivory Coast", "Yamoussoukro", COUNTRY_PATH + "ivory_cost.png");
            DatabaseHelper.saveObject(ivoryCost);

            dummyData(new Player("Gnégnéri Yaya Touré", MY_DATE.parse("19830513"), true, ivoryCost, FootType.RIGHT, Position.CM, 189, 90,
                    PLAYER_PATH + "yaya_toure.png", new HashSet<>()), manCity);

            League laliga = new League("라 리가", spain, MY_DATE.parse("19290101"), LEAGUE_PATH + "laliga.png", "La liga", "http://www.laliga.es");
            DatabaseHelper.saveObject(laliga);

            Team barcelona = new Team("Barcelona FC", "Ernesto Valverde Tejedor", 1, TEAM_PATH + "barcelona.png", MY_DATE.parse(String.valueOf(18991129)), argentina, null, laliga, new HashSet<>());
            DatabaseHelper.saveObject(barcelona);

            Team crystalPalace = new Team("Crystal Palace F.C.", "Roy Hodgson", 20, TEAM_PATH + "crystalPalace.png",
                    MY_DATE.parse("19050910"), england, null, league, new HashSet<>());
            DatabaseHelper.saveObject(crystalPalace);

            Team swansea = new Team("Swansea City A.F.C.", "Paul Clement", 19, TEAM_PATH + "swansea.png",
                    MY_DATE.parse("19120101"), england, null, league, new HashSet<>());
            DatabaseHelper.saveObject(swansea);

            Team westHam = new Team("West Ham United F.C.", "David William Moyes", 18, TEAM_PATH + "westHam.png",
                    MY_DATE.parse("18950629"), england, null, league, new HashSet<>());
            DatabaseHelper.saveObject(westHam);

            Team everton = new Team("Everton F.C.", "David Unsworth", 17, TEAM_PATH + "everton.png",
                    MY_DATE.parse(String.valueOf(18780101)), england, null, league, new HashSet<>());
            DatabaseHelper.saveObject(everton);

            Team albion = new Team("West Bromwich Albion F.C.", "Gary Megson", 16, TEAM_PATH + "albion.png",
                    MY_DATE.parse(String.valueOf(18780101)), england, null, league, new HashSet<>());
            DatabaseHelper.saveObject(albion);

            Team stock = new Team("Stoke City F.C.", "Mark Hughes", 15, TEAM_PATH + "stoke.png",
                    MY_DATE.parse(String.valueOf(18630101)), england, null, league, new HashSet<>());
            DatabaseHelper.saveObject(stock);

            Team bournemouth = new Team("A.F.C. Bournemouth", "Eddie Howe", 14, TEAM_PATH + "bournemouth.png",
                    MY_DATE.parse(String.valueOf(18990101)), england, null, league, new HashSet<>());
            DatabaseHelper.saveObject(bournemouth);

            Player messi_cuccittini = new Player("Lionel Andrés Messi Cuccittini", MY_DATE.parse("19870624"),
                    true, argentina, FootType.LEFT, Position.FW, PLAYER_PATH + "messi.jpg",
                    new HashSet<>());
            DatabaseHelper.saveObject(messi_cuccittini);

            Player dembele = new Player("Ousmane Dembele", MY_DATE.parse("19970515"),
                    true, frence, FootType.LEFT, Position.FW, PLAYER_PATH + "dembele.jpg",
                    new HashSet<>());
            DatabaseHelper.saveObject(dembele);

            Player paulinho = new Player("Paulinho", MY_DATE.parse("19880725"),
                    true, brasil, FootType.LEFT, Position.MF, PLAYER_PATH + "paulinho.jpg",
                    new HashSet<>());
            DatabaseHelper.saveObject(paulinho);

            Player lujan = new Player("Andres Iniesta Lujan", MY_DATE.parse("19840511"),
                    true, spain, FootType.LEFT, Position.MF, PLAYER_PATH + "lujan.jpg",
                    new HashSet<>());
            DatabaseHelper.saveObject(lujan);

            Player semedo = new Player("Nélson Cabral Semedo", MY_DATE.parse("19840511"),
                    true, portugal, FootType.LEFT, Position.RB, PLAYER_PATH + "semedo.jpg",
                    new HashSet<>());
            DatabaseHelper.saveObject(semedo);


            Player turan = new Player("Arda Turan", MY_DATE.parse("19870130"),
                    true, turky, FootType.LEFT, Position.LW, PLAYER_PATH + "turan.jpg",
                    new HashSet<>());
            DatabaseHelper.saveObject(turan);

            Player nascimento = new Player("Rafael Alcântara do Nascimento", MY_DATE.parse("19930212"),
                    true, brasil, FootType.LEFT, Position.MF, PLAYER_PATH + "nascimento.jpg", new HashSet<>());
            DatabaseHelper.saveObject(nascimento);

            teamPlayer = new TeamPlayer();
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
            DatabaseHelper.saveObject(teamPlayer);


        } catch (ParseException e) {
            e.printStackTrace();
        }*/

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


    }

    private static void dummyData(Player player, Team team) {
        TeamPlayer teamPlayer = new TeamPlayer(team, player);
        team.getPlayers().add(teamPlayer);
        player.getTeams().add(teamPlayer);
        DatabaseHelper.saveObject(player, teamPlayer);
    }

    private static void dummyData(Team team, League league) {
        DatabaseHelper.saveObject(team);
        league.getTeams().add(team);
    }
}
