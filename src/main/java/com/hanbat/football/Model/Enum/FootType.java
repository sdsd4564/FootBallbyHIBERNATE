package com.hanbat.football.Model.Enum;

public enum FootType {
    LEFT("왼발"), RIGHT("오른발"), BOTH("양발");

    private final String description;

    FootType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}


