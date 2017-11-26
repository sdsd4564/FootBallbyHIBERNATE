package com.hanbat.football.Model.Enum;

public enum Position {
    GK("골키퍼"),

    DF("수비수"),
    CB("센터백"),
    SW("스위퍼/리베로"),
    FB("풀백"), LB("레프트백"), RB("라이트백"),
    WB("윙백"), LWB("레프트 윙백"), RWB("라이트 윙백"),

    MF("미드필더"),
    CM("중앙 미드필더"),
    DM("수비형 미드필더"),
    AM("공격형 미드필더"),
    LW("레프트 윙어"), LM("왼쪽 미드필더"), RW("라이트 윙어"), RM("오른쪽 미드필더"),

    FW("공격수"),
    CF("중앙 공격수"),
    SS("세컨드 스트라이커"),
    False9("펄스나인");


    private final String description;

    Position(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
