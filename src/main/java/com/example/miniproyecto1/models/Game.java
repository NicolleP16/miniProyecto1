package com.example.miniproyecto1.models;

public class Game {
    private int level;
    private int timeRemaining;
    private int oportunities;

    public Game() {
        this.level = 1;
        this.timeRemaining = 20;
        this.oportunities = 4;
    }

    public void increaseLevel() {
        level++;
        if (level % 5 == 0 && timeRemaining > 2) {
            timeRemaining -= 2;
        }
    }

    public boolean reduceOpportunity() {
        oportunities--;
        return oportunities > 0;
    }

    public void reduceTime() {
        if (timeRemaining > 0) {
            timeRemaining--;
        }
    }

    public void resetTime() {
        this.timeRemaining = 20 - ((level / 5) * 2);
        if (this.timeRemaining < 2) {
            this.timeRemaining = 2;
        }
    }


    public int getLevel() {
        return level;
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public int getOportunity() {
        return oportunities;
    }
}
