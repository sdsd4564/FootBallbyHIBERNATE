package com.hanbat.football.Model;

import com.hanbat.football.Model.Enum.FootType;
import com.hanbat.football.Model.Enum.Position;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birth;

    @Column(
            name = "sex",
            nullable = false
    )
    private boolean isMale;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Country country;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FootType footType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Position position;

    @OneToMany(
            mappedBy = "player",
            cascade = CascadeType.REFRESH
    )
    private Set<TeamPlayer> teams = new HashSet<>();

    private double height;

    private double weight;

    private String filePath;

    public Player() {
    }

    public Player(String name, Date birth, boolean isMale, Country country, FootType footType, Position position, String filePath, Set<TeamPlayer> teams) {
        this.name = name;
        this.birth = birth;
        this.isMale = isMale;
        this.country = country;
        this.footType = footType;
        this.position = position;
        this.filePath = filePath;
        this.teams = teams;
    }

    public Player(String name, Date birth, boolean isMale, Country country, FootType footType, Position position, double height, double weight, String filePath, Set<TeamPlayer> teams) {
        this.name = name;
        this.birth = birth;
        this.isMale = isMale;
        this.country = country;
        this.footType = footType;
        this.position = position;
        this.height = height;
        this.weight = weight;
        this.filePath = filePath;
        this.teams = teams;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Set<TeamPlayer> getTeams() {
        return teams;
    }

    public void setTeams(Set<TeamPlayer> teams) {
        this.teams = teams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public boolean isMale() {
        return isMale;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public FootType getFootType() {
        return footType;
    }

    public void setFootType(FootType footType) {
        this.footType = footType;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getPid() {
        return pid;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
