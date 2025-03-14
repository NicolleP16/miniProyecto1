package com.example.miniproyecto1.controllers;

import com.example.miniproyecto1.models.Game;
import com.example.miniproyecto1.models.RandomWords;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.TextField;

public class GameController {

    @FXML
    void onActionEnviarButtom(ActionEvent event) {
        if (wordTextField.isDisabled()) {
            return;
        }

        String enteredWord = wordTextField.getText();
        String correctWord = randomWordLabel.getText();

        if (enteredWord.equals(correctWord)) {
            game.increaseLevel();
            game.resetTime();
            levelLabel.setText("Nivel: " + game.getLevel());
            updateWord(game.getLevel());
            startTimer();
            messageLabel.setText("¡Correcto! Muy bien :)");
            messageLabel.setStyle("-fx-text-fill: green;");
        } else {
            if (game.reduceOpportunity()) {
                updateWord(game.getLevel());
                messageLabel.setText("¡Incorrecto! :( Intenta de nuevo");
                messageLabel.setStyle("-fx-text-fill: red;");
            } else {
                endGame();
            }
        }
        wordTextField.clear();
    }

    @FXML
    void onKeyPressedWordTextField(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            onActionEnviarButtom(new ActionEvent());
        }
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

    private void endGame() {
        timeline.stop();
        randomWordLabel.setText("Juego terminado");
        levelLabel.setText("Nivel: " + game.getLevel());
        timeLabel.setText("00:00");

        wordTextField.setDisable(true);
        enviarButton.setDisable(true);
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
    private Label messageLabel;

    @FXML
    private TextField wordTextField;

    @FXML
    private Button enviarButton;

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
                endGame();
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}