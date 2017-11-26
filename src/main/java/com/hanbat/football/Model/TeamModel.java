package com.hanbat.football.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class TeamModel {

    private StringProperty name;
    private StringProperty coach;
    private IntegerProperty rank;

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getCoach() {
        return coach.get();
    }

    public StringProperty coachProperty() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach.set(coach);
    }

    public int getRank() {
        return rank.get();
    }

    public IntegerProperty rankProperty() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank.set(rank);
    }

    public TeamModel(StringProperty name, StringProperty coach, IntegerProperty rank) {

        this.name = name;
        this.coach = coach;
        this.rank = rank;
    }
}
