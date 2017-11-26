package com.hanbat.football.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class League {

    @Id
    private String name;


    @ManyToOne
    @JoinColumn(nullable = false)
    private Country country;

    @Temporal(TemporalType.DATE)
    private Date foundationDay;

    private String filepath;
    private String englishName;
    private String homepageUrl;

    @OneToMany(mappedBy = "league")
    private Set<Team> teams = new HashSet<>();

    public League() {
    }

    public League(String name, Country country, Date foundationDay, String filepath, String englishName, String homepageUrl) {
        this.name = name;
        this.country = country;
        this.foundationDay = foundationDay;
        this.filepath = filepath;
        this.englishName = englishName;
        this.homepageUrl = homepageUrl;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getHomepageUrl() {
        return homepageUrl;
    }

    public void setHomepageUrl(String homepageUrl) {
        this.homepageUrl = homepageUrl;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
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

    @Override
    public String toString() {
        return this.englishName != null
                ? this.name + "(" + this.englishName + ")"
                : this.name;
    }
}
