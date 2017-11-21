package com.hanbat.football.Model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class TeamPlayerID implements Serializable {

    private String team;

    private int player;

    public String getTeam() {
        return team;
    }

    public void setTeam(String teamName) {
        this.team = teamName;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int playerId) {
        this.player = playerId;
    }
}
