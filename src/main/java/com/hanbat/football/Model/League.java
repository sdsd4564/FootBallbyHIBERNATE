package com.hanbat.football.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class League {

    @Id
    private String name;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Country country;

    @Temporal(TemporalType.DATE)
    private Date foundationDay;

    public League() {
    }

    public League(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public League(String name, Country country, Date foundationDay) {
        this.name = name;
        this.country = country;
        this.foundationDay = foundationDay;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    public Date getFoundationDay() {
        return foundationDay;
    }

    public void setFoundationDay(Date foundationDay) {
        this.foundationDay = foundationDay;
    }
}
