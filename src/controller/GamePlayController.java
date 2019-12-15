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


import java.net.URL;
import java.util.*;

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
    private static Card pickedCard1 = null;
    private static Card pickedCard2 = null;

    public void flipCard(MouseEvent event)
    {
        if (pickedCard2 == null)
        {
            ImageView sourceCardView = (ImageView) event.getSource();
            cardIndex = (Integer.parseInt(sourceCardView.getId().replace("card", "")) - 1);
            System.out.println("cardid = " + sourceCardView.getId());
            System.out.println(sourceCardView.getId() + "Image =  " + cardList.get(cardIndex).getImage());
            try
            {
                sourceCardView.setImage(new Image(cardList.get(cardIndex).getImage()));
            } catch (Exception e)
            {
                System.out.println("Image Not Found.");
            }

            if (pickedCard1 == null)
                pickedCard1 = cardList.get(cardIndex);
            else
                pickedCard2 = cardList.get(cardIndex);

            if (pickedCard1 != null && pickedCard2 != null)
            {
                if (pickedCard1.compareTo(pickedCard2))
                {
                    System.out.println("here2 = " + cardList.get(cardIndex).number);
                    cardViewList.get(pickedCard1.getPosition()).setImage(null);
                    cardViewList.get(pickedCard2.getPosition()).setImage(null);
                    cardViewList.get(pickedCard1.getPosition()).setOnMouseClicked(null);
                    cardViewList.get(pickedCard2.getPosition()).setOnMouseClicked(null);
                }
                pickedCard1 = null;
                pickedCard2 = null;
            }
            TimerTask task = new TimerTask()
            {
                @Override
                public void run()
                {
                    if (sourceCardView.getImage() != null)
                        sourceCardView.setImage(new Image(faceDownImage));
                }
            };

            Timer timer = new Timer();
            timer.schedule(task, 1000);
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
            System.out.println("card[" + i + "] = " + cardNumber);
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
