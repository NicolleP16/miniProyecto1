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

    @FXML
    private Button ResetButtom;

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
     * This method checks if the entered word matches the correct word displayed.
     *  - If correct, the level increases, time resets, and a new word appears.
     *  - If incorrect, an opportunity is lost, and an eclipse image updates.
     *  - If no opportunities remain, the game ends.
     * @param event, the action event triggered by pressing the "Enviar" button.
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
     * If the Enter key is pressed, it triggers the same action as clicking
     * the "Enviar" button by calling onActionEnviarButtom(ActionEvent).
     * @param keyEvent, the key event triggered when a key is pressed in the text field.
     */
    @FXML
    void onKeyPressedWordTextField(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            onActionEnviarButtom(new ActionEvent());
        }
    }

    /**
     * Instance of the RandomWords class used to generate random words for the game.
     */
    private RandomWords randomWords = new RandomWords();

    /**
     * Instance of Game class that manages the game state, including level, time, and opportunities.
     */
    private Game game = new Game();

    /**
     * Handles the action event that is triggered when the “Restart” button is clicked.
     */
    public void onActionRestartGame() {
        game = new Game();
        randomWords = new RandomWords();

        randomWordLabel.setText(randomWords.getRandomWord(game.getLevel()));
        levelLabel.setText("Nivel: " + game.getLevel());
        timeLabel.setText(formatTime(game.getTimeRemaining()));

        feedbackLabel.setText("");
        messageLabel.setText("");
        eclipseImageView.setImage(eclipseImages[0]);

        wordTextField.setText("");
        wordTextField.setDisable(false);
        enviarButton.setDisable(false);

        startTimer();
    }

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
     * @param level, the current level of the game, which determines the difficulty of the word.
     */
    private void updateWord(int level) {
        String word = randomWords.getRandomWord(level);
        randomWordLabel.setText(word);
    }

    /**
     * Formats the time from total seconds into a "MM:SS" string.
     * @param totalSeconds, the total time in seconds to be formatted.
     * @return A string representing the formatted time in "MM:SS" format.
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