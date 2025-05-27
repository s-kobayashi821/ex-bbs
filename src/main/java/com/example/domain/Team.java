package com.example.domain;

public class Team {
    /*id*/
    private  Integer id;

    /*リーグ名*/
    private String leagueName;

    /*チーム名*/
    private String teamName;


    /*本拠地名*/
    private  String headquarters;

    /*本拠地名*/
    private String inauguration;

    /*歴史*/
    private String history;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    public String getInauguration() {
        return inauguration;
    }

    public void setInauguration(String inauguration) {
        this.inauguration = inauguration;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", leagueName='" + leagueName + '\'' +
                ", headquarters='" + headquarters + '\'' +
                ", inauguration='" + inauguration + '\'' +
                ", history='" + history + '\'' +
                '}';
    }
}
