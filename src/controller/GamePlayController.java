/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 2019C
  Assessment: Assignment 2
  Author: Le Quang Hien
  ID: s3695516
  Created  date: 13/12/2019
  Last modified: 13/12/2019
  Acknowledgement:
*/

package controller;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.net.URL;
import java.util.*;


public class GamePlayController implements Initializable
{

    // linking objects in FXML file
    @FXML
    private ImageView card1, card2, card3, card4, card5, card6, card7, card8,
            card9, card10, card11, card12, card13, card14, card15, card16, card17, card18, card19, card20;
    @FXML
        private ProgressBar timeBar;
    @FXML
        private Button playAgainBt;
    @FXML
        private Label scoreLabel, timerLabel;

    //
    private final String backGroundSound = "src/audio/music.mp3";
    private final String loseSound = "src/audio/lose.mp3";
    private final String winSound = "src/audio/win.mp3";

    //  create variables for game play
    private static int score = 0;
    private Timeline timeline = null;
    private AnimationTimer animationTimer = null;
    private int currentTime;
    CardController cardController = null;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        System.out.println("score = " + score);
        scoreLabel.setText("Score: " + score +  " points");
        currentTime = 1;
        cardController = new CardController();
        cardController.cardViewList.addAll(card1, card2, card3, card4, card5, card6, card7, card8,
                card9, card10, card11, card12, card13, card14, card15, card16, card17, card18, card19, card20);
        cardController.initialize();
        initializeTimeBar();
        initializeButtons();
        initializeTimer();
    }

    public void initializeTimeBar()
    {
        timeline = new Timeline();
        KeyValue keyValue = new KeyValue(timeBar.progressProperty(), 1.0);
        KeyFrame keyFrame = new KeyFrame(new Duration(120000), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    public void initializeButtons()
    {
        playAgainBt.setVisible(false);
        playAgainBt.setDisable(true);
    }

    public void initializeTimer()
    {
        animationTimer = new AnimationTimer() {
            private long startTime ;

            @Override
            public void start() {
                startTime = System.currentTimeMillis();
                super.start();
            }

            @Override
            public void handle(long timestamp) {
                long now = System.currentTimeMillis();
                if (isGameEnded()) endGame();
                if (currentTime <= 0)
                    timerLabel.setText("Time is up");
                else
                {
                    currentTime = (int) (120000 - (now - startTime));
                    int minutes = (currentTime / 1000) / 60;
                    int seconds = (currentTime / 1000) % 60;
                    int hundredths = (currentTime/10) % 100;
                    timerLabel.setText("Time left: 0" + minutes+ ":" + seconds + ":" + hundredths);
                }
            }
        };
        animationTimer.start();
    }

    public void endGame()
    {
        playAgainBt.setVisible(true);
        playAgainBt.setDisable(false);
        animationTimer.stop();
        timeline.stop();
        MediaController.stop();

        if (currentTime <= 0)
            MediaController.playNewAudio(loseSound);
        else
        {
            score += currentTime/1000;
            scoreLabel.setText("Score: " + score +  " points");
            MediaController.playNewAudio(winSound);
        }
    }

    public boolean isGameEnded()
    {
        if (currentTime <= 0 || cardController.isAllCardsRemoved())
            return true;
        return false;
    }

    public void cardOnMouseClicked(MouseEvent event)
    {
        cardController.flipCard(event);
    }

    public void soundBtHandler(ActionEvent event)
    {
        ButtonController.soundBtHandler(event);
    }

    public void quitGameBtHandler(ActionEvent event)
    {
        animationTimer.stop();
        timeline.stop();
        ButtonController.quitGameBtHandler(event);
    }

    public void playAgainBtHandler(ActionEvent event)
    {
        System.out.println("score = " + score);
        animationTimer.stop();
        timeline.stop();
        MediaController.playNewAudio(backGroundSound);
        ButtonController.startGameBtHandler(event);
    }
}
