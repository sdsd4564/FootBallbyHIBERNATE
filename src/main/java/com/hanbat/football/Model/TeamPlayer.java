package com.hanbat.football.Model;


import javax.persistence.*;

@Entity
public class TeamPlayer {

    @EmbeddedId
    private TeamPlayerID id = new TeamPlayerID();

    @MapsId("team")
    @ManyToOne
    @JoinColumn(name = "team_name")
    private Team team;

    @MapsId("player")
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

//    private int backNumber;
//
//    private Date career;
//
//    @Column(name = "number_of_goal")
//    private int numberOfGoal;
//
//    private int apperances;


    public TeamPlayer() {
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}


