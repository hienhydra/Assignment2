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
*/

package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.CardModel;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

// This class is created to control cards and their views
public class CardController
{
    // declare needed variables
    private final String faceDownImage = "File:src/img/0.png";
    public  ObservableList<ImageView> cardViewList = FXCollections.observableArrayList();
    private ArrayList<CardModel> cardModelList = new ArrayList<>();
    private int cardIndex;
    private CardModel selectedCardModel1 = null;
    private CardModel selectedCardModel2 = null;
    private int noOfSelectedCards = 0;
    private boolean isComparing = true;
    private int delayTime = MainMenuController.selectedLevel * 1000;

    // this method is to initialize the cards and their appearances on the game
    public void initialize()
    {
        initializeCardModelList();
        initializeCardViewList();
    }

    // this method is to initialize the appearances of cards on the game
    private void initializeCardViewList()
    {
        cardViewList.forEach(card -> card.setImage(new Image(faceDownImage)));
    }

    // this method is to assigning cards to positions in the game
    private void initializeCardModelList()
    {
        int cardNumber;
        int []checkList = new int[20];
        for (int i = 0; i < 20; i++)
        {
            do
            {   // generate a random number to assign card to a specific position on the game
                cardNumber = (int) (Math.random() * 10) + 1;
            } while(noOfOccurrences(checkList, cardNumber) >= 2);   // check if the card has selected two times
            checkList[i] = cardNumber;                 // assign the card to checkList for later verifying
            cardModelList.add(new CardModel(cardNumber, i));     // add the card to the cardList, index = position - 1;
        }
    }

    // this method is to flip the card
    public void flipCard(MouseEvent event)
    {
        if (noOfSelectedCards < 2)          // check if two card have been selected
        {                                   // if not let the player flip the card
            noOfSelectedCards++;
            isComparing = !isComparing;
            ImageView sourceCardView = (ImageView) event.getSource();   // get the clickedCard
            cardIndex = (Integer.parseInt(sourceCardView.getId().replace("card", "")) - 1);
            sourceCardView.setImage(new Image(cardModelList.get(cardIndex).getImage())); // face up the card

            if (!compareCards())            // compare two cards,
            {                               // if they are not the same, flip them again after a delay time
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
    }

    // this method is to count the occurrences of a number in a list
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

    // this method is to compare two cards
    private boolean compareCards()
    {
        boolean result = false;
        if (!isComparing)
            selectedCardModel1 = cardModelList.get(cardIndex);
        else
        {
            selectedCardModel2 = cardModelList.get(cardIndex);
            // avoid the player to click on the same card by checking their position
            if (selectedCardModel1.compareTo(selectedCardModel2) && (selectedCardModel1.getPosition() != selectedCardModel2.getPosition()))
            {
                // if they are match, delete them
                cardViewList.get(selectedCardModel1.getPosition()).setImage(null);
                cardViewList.get(selectedCardModel2.getPosition()).setImage(null);
                cardViewList.get(selectedCardModel1.getPosition()).setOnMouseClicked(null);
                cardViewList.get(selectedCardModel2.getPosition()).setOnMouseClicked(null);
                cardModelList.get(selectedCardModel1.getPosition()).isRemoved = true;
                cardModelList.get(selectedCardModel2.getPosition()).isRemoved = true;
                noOfSelectedCards--;
                result = true;
            }
        }
        return result;
    }

    // this method is to check if all cards have been removed,
    // which is needed for the winning condition of the game
    public boolean isAllCardsRemoved()
    {
        for (CardModel cardModel : cardModelList)
        {
            if (!cardModel.isRemoved)
                return  false;
        }
        return true;
    }
}
