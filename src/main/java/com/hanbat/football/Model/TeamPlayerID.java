package com.hanbat.football.Model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class TeamPlayerID implements Serializable {


    private String teamId;

    private int playerId;

    public String getTeam() {
        return teamId;
    }

    public void setTeam(String teamName) {
        this.teamId = teamName;
    }

    public int getPlayer() {
        return playerId;
    }

    public void setPlayer(int playerId) {
        this.playerId = playerId;
    }
}
