package com.hanbat.football.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "country")
public class Country {

    @Id
    private String name;

    @Column(name = "capital", nullable = false)
    private String capital;
    private String filePath;

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
}
