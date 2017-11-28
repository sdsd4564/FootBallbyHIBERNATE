package com.hanbat.football.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "team")
public class Team {

    @Id
    private String name;

    @Column(name = "coach", nullable = false)
    private String coach;

    @Column(name = "rank", nullable = false)
    private int rank;

    @Column(name = "logo_filepath")
    private String logoFilePath;

    @Temporal(TemporalType.DATE)
    @Column(name = "foundation_day")
    private Date foundationDay;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Country country;

    @ManyToOne
    @JoinColumn
    private Stadium stadium;

    @ManyToOne
    @JoinColumn(nullable = false)
    private League league;

    @OneToMany(
            mappedBy = "team",
            cascade = CascadeType.REFRESH
    )
    private Set<TeamPlayer> players = new HashSet<>();

    public void setPlayers(Set<TeamPlayer> players) {
        this.players = players;
    }

    public Team() {
    }

    /*
    * mappedBy reference an unknown target entity property: com.hanbat.football.Model.Player.team in com.hanbat.football.Model.Team.player
      Exception in thread "main" java.lang.ExceptionInInitializerError
    * */

    public Team(String name, String coach, int rank, String logoFilePath, Country country, League league, Set<TeamPlayer> players) {
        this.name = name;
        this.coach = coach;
        this.rank = rank;
        this.logoFilePath = logoFilePath;
        this.country = country;
        this.league = league;
        this.players = players;
    }

    public Team(String name, String coach, int rank, String logoFilePath, Date foundationDay, Country country, Stadium stadium, League league, Set<TeamPlayer> players) {
        this.name = name;
        this.coach = coach;
        this.rank = rank;
        this.logoFilePath = logoFilePath;
        this.foundationDay = foundationDay;
        this.country = country;
        this.stadium = stadium;
        this.league = league;
        this.players = players;
    }

    public Set<TeamPlayer> getPlayers() {
        return players;
    }

    public Date getFoundationDay() {
        return foundationDay;
    }

    public void setFoundationDay(Date birth) {
        this.foundationDay = birth;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoach() {
        return coach;
    }

    public int getRank() {
        return rank;
    }

    public String getLogoFilePath() {
        return logoFilePath;
    }

    public Country getCountry() {
        return country;
    }

    public League getLeague() {
        return league;
    }

    @Override
    public String toString() {
        return name;
    }
}
