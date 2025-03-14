package com.example.miniproyecto1.controllers;

import com.example.miniproyecto1.models.Game;
import com.example.miniproyecto1.models.RandomWords;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

public class GameController {

    @FXML
    void onActionEnviarButtom(ActionEvent event) {
        game.increaseLevel();
        game.resetTime();
        levelLabel.setText("Nivel: " + game.getLevel());
        updateWord(game.getLevel());
        startTimer();
    }
    @FXML
    private Label randomWordLabel;

    private RandomWords randomWords = new RandomWords();
    private Game game = new Game();

    public void initialize() {
        String word = randomWords.getRandomWord(1);
        randomWordLabel.setText(word);

        levelLabel.setText("Nivel: " + game.getLevel());
        timeLabel.setText(formatTime(game.getTimeRemaining()));
        startTimer();
    }

    private void updateWord(int level) {
        String word = randomWords.getRandomWord(level);
        randomWordLabel.setText(word);
    }

    @FXML
    private Label levelLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private Timeline timeline;

    private String formatTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    private void startTimer() {
        if (timeline != null) {
            timeline.stop();
        }

        timeLabel.setText(formatTime(game.getTimeRemaining()));

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (game.getTimeRemaining() > 0) {
                game.reduceTime();
                timeLabel.setText(formatTime(game.getTimeRemaining()));
            } else {
                timeline.stop();
            }
        }));

        timeline.setCycleCount(game.getTimeRemaining());
        timeline.play();
    }
}
