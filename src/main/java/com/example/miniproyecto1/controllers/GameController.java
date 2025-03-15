package com.example.miniproyecto1.controllers;

import com.example.miniproyecto1.models.Game;
import com.example.miniproyecto1.models.RandomWords;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

public class GameController {
    /**
     * GameController class manages the game logic and user interactions.
     *
     * @author Nicolle Paz
     * @version 1.0.0
     */

    @FXML
    private Label randomWordLabel;

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

    @FXML
    private ImageView eclipseImageView;

    @FXML
    private Label feedbackLabel;

    @FXML
    private Label fastTypingLabel;

    private Image[] eclipseImages = new Image[5];

    /**
     * To initialize the game state
     */
    public void initialize() {
        for (int i = 0; i < 5; i++) {
            eclipseImages[i] = new Image(getClass().getResourceAsStream(
                    "/com/example/miniproyecto1/images/sol" + (i) + ".png"));
        }

        eclipseImageView.setImage(eclipseImages[0]);

        String word = randomWords.getRandomWord(1);
        randomWordLabel.setText(word);

        levelLabel.setText("Nivel: " + game.getLevel());
        timeLabel.setText(formatTime(game.getTimeRemaining()));
        startTimer();
    }

    /**
     * Handles the action event that is triggered when the “Enviar” button is clicked.
     * @param event
     */
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
                int remainingOpportunities = game.getOpportunity();
                int index = 4 - remainingOpportunities;
                if (index < 5) {
                    eclipseImageView.setImage(eclipseImages[index]);
                }

                game.resetTime();
                updateWord(game.getLevel());
                startTimer();
                messageLabel.setText("¡Incorrecto! :( Intenta de nuevo");
                messageLabel.setStyle("-fx-text-fill: red;");
            } else {
                eclipseImageView.setImage(eclipseImages[4]);

                messageLabel.setText("¡Incorrecto!");
                messageLabel.setStyle("-fx-text-fill: red;");
                endGame();
            }
        }
        wordTextField.clear();
    }

    /**
     * Handles the key event triggered by a key press in the word text field.
     * @param keyEvent
     */
    @FXML
    void onKeyPressedWordTextField(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            onActionEnviarButtom(new ActionEvent());
        }
    }

    private RandomWords randomWords = new RandomWords();
    private Game game = new Game();

    /**
     * Ends the game by stopping the timer, updating the labels, and disabling the input controls.
     */
    private void endGame() {

        timeline.stop();
        randomWordLabel.setText("Fin");
        levelLabel.setText("Nivel: " + game.getLevel());
        timeLabel.setText("00:00");

        feedbackLabel.setText("¡Perdiste! Nivel alcanzado: " + game.getLevel());

        wordTextField.setDisable(true);
        enviarButton.setDisable(true);
    }

    /**
     * Updates the displayed word based on the current level.
     * @param level
     */
    private void updateWord(int level) {
        String word = randomWords.getRandomWord(level);
        randomWordLabel.setText(word);
    }

    /**
     * Formats the time from total seconds into a "MM:SS" string.
     * @param totalSeconds
     * @return
     */
    private String formatTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    /**
     * Starts the timer, updating the time every second and triggering an action when the time runs out.
     */
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
                onActionEnviarButtom(new ActionEvent());
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}