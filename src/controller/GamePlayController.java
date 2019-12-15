package controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

public class GamePlayController implements Initializable
{
    @FXML
        private ImageView card00;
    @FXML
        private ImageView card01;
    @FXML
        private ImageView card02;
    @FXML
        private ImageView card03;
    @FXML
        private ImageView card10;
    @FXML
        private ImageView card11;
    @FXML
        private ImageView card12;
    @FXML
        private ImageView card13;
    @FXML
        private ImageView card20;
    @FXML
        private ImageView card21;
    @FXML
        private ImageView card22;
    @FXML
        private ImageView card23;
    @FXML
        private ImageView card30;
    @FXML
        private ImageView card31;
    @FXML
        private ImageView card32;
    @FXML
        private ImageView card33;
    @FXML
        private ImageView card40;
    @FXML
        private ImageView card41;
    @FXML
        private ImageView card42;
    @FXML
        private ImageView card43;

    public static ObservableList<ImageView> cardList = FXCollections.observableArrayList();
    public static int i = 1;

    public void flipCard()
    {
        initializeCardList();
        try
        {
            cardList.get(0).setImage(new Image("File:src/img/1.png"));
        }
        catch (Exception e)
        {
            System.out.println("here");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        initializeCardList();
    }

    public void initializeCardList()
    {
        cardList.addAll(card00, card01, card02, card03, card10, card11, card12, card13,
                card20, card21, card22, card23, card30, card31, card32, card33, card40, card41, card42, card43);
        cardList.forEach((card) ->
        {
            card.setImage(new Image("File:src/img/" + i + ".png"));
            if (i == 10)
                i = 1;
            else
                i++;
        });
    }
}
