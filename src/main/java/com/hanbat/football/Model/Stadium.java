package com.hanbat.football.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Stadium {

    @Id
    private String name;

    @Column(nullable = false)
    private Date openDate;

    private int capacity;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Country country;
//    private Team team;

    public Stadium() {
    }

    public Stadium(String name, Date openDate, Country country) {
        this.name = name;
        this.openDate = openDate;
        this.country = country;
    }

    public Stadium(String name, int capacity, Date openDate, Country country) {
        this.name = name;
        this.capacity = capacity;
        this.openDate = openDate;
        this.country = country;
    }


    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Country getCountry() {
        return country;
    }
}
