package controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.Card;


import javax.swing.*;
import java.net.URL;
import java.util.*;
import java.util.Timer;

public class GamePlayController implements Initializable
{
    @FXML
        private ImageView card1, card2, card3, card4, card5, card6, card7, card8,
            card9, card10, card11, card12, card13, card14, card15, card16, card17, card18, card19, card20;
    @FXML
        private GridPane gamePlay;

    public static ObservableList<ImageView> cardViewList = FXCollections.observableArrayList();
    public static ArrayList<Card> cardList = new ArrayList<>();
    private static String faceDownImage = "File:src/img/0.png";
    private static int cardIndex;
    private static Card selectedCard1 = null;
    private static Card selectedCard2 = null;
    private static int noOfSelectedCards = 0;
    private static int delayTime = 3000;
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

    private void compareCards()
    {
        boolean results = false;
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
                System.out.println("here 2 = ");
                cardViewList.get(selectedCard1.getPosition()).setImage(null);
                cardViewList.get(selectedCard2.getPosition()).setImage(null);
                cardViewList.get(selectedCard1.getPosition()).setOnMouseClicked(null);
                cardViewList.get(selectedCard2.getPosition()).setOnMouseClicked(null);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        initializeCardList();
        initializeCardViewList();
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

    public void initializeCardViewList()
    {
        cardViewList.addAll(card1, card2, card3, card4, card5, card6, card7, card8,
                card9, card10, card11, card12, card13, card14, card15, card16, card17, card18, card19, card20);
        cardViewList.forEach((card) ->
        {
            card.setImage(new Image(faceDownImage));
        });
    }
}
