package com.example.miniproyecto1.models;

/**
 * Game class manages the game state, including the level, remaining time, and opportunities.
 */
public class Game {
    private int level;
    private int timeRemaining;
    private int opportunities;

    public Game() {
        this.level = 1;
        this.timeRemaining = 20;
        this.opportunities = 4;
    }

    /**
     * Increases the level and reduces the remaining time every 5 levels, if time allows.
     */
    public void increaseLevel() {
        level++;
        if (level % 5 == 0 && timeRemaining > 2) {
            timeRemaining -= 2;
        }
    }

    /**
     * Reduces the number of opportunities by 1 and returns whether there are still opportunities left.
     * @return
     */
    public boolean reduceOpportunity() {
        opportunities--;
        return opportunities > 0;
    }

    /**
     * Reduces the remaining time by 1 second, if there is time left.
     */
    public void reduceTime() {
        if (timeRemaining > 0) {
            timeRemaining--;
        }
    }

    /**
     * Resets the remaining time based on the level, ensuring it doesn't go below 2 seconds.
     */
    public void resetTime() {
        this.timeRemaining = 20 - ((level / 5) * 2);
        if (this.timeRemaining < 2) {
            this.timeRemaining = 2;
        }
    }

    /**
     * Returns the current level of the game.
     * @return
     */
    public int getLevel() {
        return level;
    }

    /**
     * Returns the remaining time in the game.
     * @return
     */
    public int getTimeRemaining() {
        return timeRemaining;
    }

    /**
     * Returns the number of remaining opportunities in the game.
     * @return
     */
    public int getOpportunity() {
        return opportunities;
    }
}
