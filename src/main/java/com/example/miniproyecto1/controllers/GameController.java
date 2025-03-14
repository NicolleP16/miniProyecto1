package com.example.miniproyecto1.controllers;

import com.example.miniproyecto1.models.Game;
import com.example.miniproyecto1.models.RandomWords;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameController {

    @FXML
    void onActionEnviarButtom(ActionEvent event) {
        game.increaseLevel();
        levelLabel.setText("Nivel: " + game.getLevel());
    }
    @FXML
    private Label randomWordLabel;

    private RandomWords randomWords = new RandomWords();
    private Game game = new Game();

    public void initialize() {
        String word = randomWords.getRandomWord(1);
        randomWordLabel.setText(word);

        levelLabel.setText("Nivel: " + game.getLevel());
    }

    private void updateWord(int level) {
        String word = randomWords.getRandomWord(level);
        randomWordLabel.setText(word);
    }

    @FXML
    private Label levelLabel;

}
