/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 2019C
  Assessment: Assignment 2
  Author: Le Quang Hien
  ID: s3695516
  Created  date: 13/12/2019
  Last modified: 16/12/2019
  Acknowledgement:
  For creating the Time ProgressBar
    https://stackoverflow.com/questions/34198190/javafx-progressbar-animation-or-transition
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

    // create final String for file paths
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
    // this method is to initialize for the GamePlay window
    public void initialize(URL url, ResourceBundle rb)
    {
        scoreLabel.setText("Score: " + score +  " points");     // display the current score
        currentTime = 1;                                        // initialize the game by set the timer = 1

        //link the objects of cards in fxml file to the cardController
        cardController = new CardController();
        cardController.cardViewList.addAll(card1, card2, card3, card4, card5, card6, card7, card8,
                card9, card10, card11, card12, card13, card14, card15, card16, card17, card18, card19, card20);

        //initialize other features of the game
        cardController.initialize();
        initializeTimeBar();
        initializeButtons();
        initializeTimer();
    }

    // this method is to initialize the TimeBar o the game
    public void initializeTimeBar()
    {
        timeline = new Timeline();
        KeyValue keyValue = new KeyValue(timeBar.progressProperty(), 1.0);
        KeyFrame keyFrame = new KeyFrame(new Duration(120000), keyValue);   // set it progresses in 120seconds
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    // this method is to disable the playAgain button when the game is playing
    public void initializeButtons()
    {
        playAgainBt.setVisible(false);
        playAgainBt.setDisable(true);
    }

    // this method is to establish the timer of the game
    public void initializeTimer()
    {
        animationTimer = new AnimationTimer() {
            private long startTime ;

            @Override
            public void start() {
                startTime = System.currentTimeMillis();     // save the start time
                super.start();
            }

            @Override
            public void handle(long timestamp) {
                long now = System.currentTimeMillis();
                if (isGameEnded()) endGame();               // check if the game has ended or not
                if (currentTime <= 0)                       // if time is up notify the player
                    timerLabel.setText("Time is up");
                else
                {   // if time is not up, update the timer every GUI frameworks (approximately 60fps)
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

    // this method is end the current game
    public void endGame()
    {
        playAgainBt.setVisible(true);
        playAgainBt.setDisable(false);
        animationTimer.stop();
        timeline.stop();
        MediaController.stop();

        if (currentTime <= 0)
            MediaController.playNewAudio(loseSound);    // if the player lose, play the losing sound effect
        else
        {
            score += currentTime/1000;                  // add up new score when player wins
            scoreLabel.setText("Score: " + score +  " points");
            MediaController.playNewAudio(winSound);     // // if the player lose, play the losing sound effect
        }
    }

    // this method is to check if the game is ended
    public boolean isGameEnded()
    {
        // the game is ended either when time is up or all cards have been removed
        return currentTime <= 0 || cardController.isAllCardsRemoved();
    }

    // this method is to control the click action of player on cards
    public void cardOnMouseClicked(MouseEvent event)
    {
        cardController.flipCard(event);
    }

    // this method is to control the sound button
    public void soundBtHandler(ActionEvent event)
    {
        ButtonController.soundBtHandler(event);
    }

    // this method is to control the quit game button
    public void quitGameBtHandler(ActionEvent event)
    {
        animationTimer.stop();                      // stop the animation of the timeBar
        timeline.stop();                            // stop the timer
        ButtonController.quitGameBtHandler(event);  // go back to the main menu
    }

    // this method is to control the Play Again button
    public void playAgainBtHandler(ActionEvent event)
    {
        animationTimer.stop();                          // stop the animation of the timeBar
        timeline.stop();                                // stop the timer
        MediaController.playNewAudio(backGroundSound);  // play the background music again
        ButtonController.startGameBtHandler(event);     // load the game again
    }
}
