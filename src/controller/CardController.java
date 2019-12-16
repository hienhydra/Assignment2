package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Card;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class CardController
{
    private final String faceDownImage = "File:src/img/0.png";
    public  ObservableList<ImageView> cardViewList = FXCollections.observableArrayList();
    private ArrayList<Card> cardList = new ArrayList<>();
    private int cardIndex;
    private Card selectedCard1 = null;
    private Card selectedCard2 = null;
    private int noOfSelectedCards = 0;
    private int delayTime = MainMenuController.selectedLevel * 1000;

    public void initialize()
    {
        initializeCardList();
        initializeCardViewList();
    }

    public void initializeCardViewList()
    {
        for(int i = 0; i < 20; i++)
        {
            cardViewList.get(i).setImage(new Image(faceDownImage));
        }
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
    private static int noOfOccurrences(int []list, int number)
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
        if(noOfSelectedCards == 2)
        {
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

    public boolean isAllCardsRemoved()
    {
        for (Card card: cardList)
        {
            if (!card.isRemoved)
                return  false;
        }
        return true;
    }
}
