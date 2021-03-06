package com.hanbat.football.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Country {

    @Id
    private String name;

    @Column(name = "capital", nullable = false)
    private String capital;
    private String filePath;

    @OneToMany(mappedBy = "country")
    private Set<League> leagues = new HashSet<>();

    public Country() {
    }

    public Country(String name, String capital) {
        this.name = name;
        this.capital = capital;
    }

    public Country(String name, String capital, String filePath) {
        this.name = name;
        this.capital = capital;
        this.filePath = filePath;
    }

    public Set<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(Set<League> leagues) {
        this.leagues = leagues;
    }


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    @Override
    public String toString() {
        return name;
    }
}
