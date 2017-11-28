package com.hanbat.football.Model.Enum;

public enum FootType {
    LEFT("왼쪽"), RIGHT("오른쪽"), BOTH("양발");

    private final String description;

    FootType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


    @Override
    public String toString() {
        return description;
    }
}


