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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Card;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.Timer;

public class GamePlayController implements Initializable
{
    // linking objects in FXML file
    @FXML
        private ImageView card1, card2, card3, card4, card5, card6, card7, card8,
            card9, card10, card11, card12, card13, card14, card15, card16, card17, card18, card19, card20;
    @FXML
        private ProgressBar timeBar;
    @FXML
        private Button soundBt, playAgainBt;
    @FXML
        private Label scoreLabel, timerLabel;

    //
    private static final String loseSound = "src/audio/lose.mp3";
    private static final String winSound = "src/audio/win.mp3";
    private static final String faceDownImage = "File:src/img/0.png";

    //  create variables for game play
    public static ObservableList<ImageView> cardViewList = FXCollections.observableArrayList();
    public static ArrayList<Card> cardList = new ArrayList<>();
    private static int cardIndex;
    private static Card selectedCard1 = null;
    private static Card selectedCard2 = null;
    private static int noOfSelectedCards = 0;
    private static int delayTime = 1000;
    private static int score = 0;
    private static Timeline timeline = null;
    private static AnimationTimer animationTimer = null;
    private static int currentTime = 1;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        initializeCardList();
        initializeCardViewList();
        initializeTimeBar();
        initializeButtons();
        initializeTimer();
    }

    public void initializeCardList()
    {
        int cardNumber = -1;
        int []checkList = new int[20];
        for (int i = 0; i < 20; i++)
        {
            do
            {
                cardNumber = (int) (Math.random() * 10) + 1;
            } while(noOfOccurrences(checkList, cardNumber) >= 2);
            checkList[i] = cardNumber;
            cardList.add(new Card(cardNumber, i));
        }
    }

    public void initializeCardViewList()
    {
        cardViewList.addAll(card1, card2, card3, card4, card5, card6, card7, card8,
                card9, card10, card11, card12, card13, card14, card15, card16, card17, card18, card19, card20);
        cardViewList.forEach((card) ->
        {
            card.setImage(new Image(faceDownImage));
        });
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
                    currentTime = (int) (4000 - (now - startTime));
                    timerLabel.setText("Time left: " + Integer.toString(currentTime));
                }
            }
        };
        animationTimer.start();
    }

    public void flipCard(MouseEvent event)
    {
        if (noOfSelectedCards < 2)
        {
            noOfSelectedCards++;
            ImageView sourceCardView = (ImageView) event.getSource();
            cardIndex = (Integer.parseInt(sourceCardView.getId().replace("card", "")) - 1);
            sourceCardView.setImage(new Image(cardList.get(cardIndex).getImage()));

            compareCards();
            TimerTask task = new TimerTask()
            {
                @Override
                public void run()
                {
                    if (sourceCardView.getImage() != null)
                        sourceCardView.setImage(new Image(faceDownImage));
                    noOfSelectedCards--;
                }
            };

            Timer timer = new Timer();
            timer.schedule(task, delayTime);
        }
    }
    public void soundBtHandler()
    {
        if(MediaController.isPlaying())
        {
            MediaController.pause();
            ImageView imageView = new ImageView (new Image("File:src/img/soundOff.png"));
            imageView.setFitHeight(51);
            imageView.setFitWidth(51);
            soundBt.setGraphic((imageView));
        }
        else
        {
            MediaController.play();
            ImageView imageView = new ImageView (new Image("File:src/img/soundOn.png"));
            imageView.setFitHeight(51);
            imageView.setFitWidth(51);
            soundBt.setGraphic(imageView);
        }
    }

    @FXML
    private void startGameBtHandler(ActionEvent event) {
        Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        try
        {
            Parent root2 = FXMLLoader.load(getClass().getResource("../view/GamePlay.fxml"));
            currentStage.setScene(new Scene(root2));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    private int noOfOccurrences(int []list, int number)
    {
        int count = 0;
        for (int i : list)
        {
            if (i == number)
                count++;
        }
        return count;
    }

    private void compareCards()
    {
        if (noOfSelectedCards == 1)
            selectedCard1 = cardList.get(cardIndex);
        else
            selectedCard2 = cardList.get(cardIndex);
        System.out.println("number of Selected Cards = " + noOfSelectedCards);
        if(noOfSelectedCards == 2)
        {
            System.out.println("here 1 = ");
            System.out.println("card1 = " + selectedCard1.getImage());
            System.out.println("card2 = " + selectedCard2.getImage());
            if (selectedCard1.compareTo(selectedCard2) && (selectedCard1.getPosition() != selectedCard2.getPosition()))
            {
                cardViewList.get(selectedCard1.getPosition()).setImage(null);
                cardViewList.get(selectedCard2.getPosition()).setImage(null);
                cardViewList.get(selectedCard1.getPosition()).setOnMouseClicked(null);
                cardViewList.get(selectedCard2.getPosition()).setOnMouseClicked(null);
                cardList.get(selectedCard1.getPosition()).isRemoved = true;
                cardList.get(selectedCard2.getPosition()).isRemoved = true;
            }
        }
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
            score += 120-currentTime;
            scoreLabel.setText("Score: " + score +  " points");
            MediaController.playNewAudio(winSound);
        }
    }

    public boolean isGameEnded()
    {
        if (currentTime <= 0)
            return true;
        for (Card card: cardList)
        {
            if (!card.isRemoved)
                return  false;
        }
        System.out.println("game ended");
        return true;
    }
}
