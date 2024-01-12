package com.example.register2.models.enums;


import java.util.List;

public enum Difficulty {
    EASY, MEDIUM, HARD;
    public String toString(){
        switch (this) {
            case EASY -> {
                return "Легкий";
            }
            default -> {
                return "Средний";
            }
            case HARD -> {
                return "Сложный";
            }
        }
    }
}
