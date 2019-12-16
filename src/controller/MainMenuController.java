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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

// this class is to control the main menu of the game
public class MainMenuController implements Initializable
{
    @FXML
        private ChoiceBox selectLevelBox;
    ObservableList<String> levelBox = FXCollections.observableArrayList("Easy", "Medium", "Hard");
    public static int selectedLevel = 2;

    @Override
    // this method is to initialize the menu option with the choiceBox for selecting game level
    public void initialize(URL location, ResourceBundle resources)
    {
        selectLevelBox.setValue("Medium");
        selectLevelBox.setItems(levelBox);
        MediaController.initializeMedia();      // start the media
    }

    // this method is to handle the sound button in the main menu
    public void soundBtHandler(ActionEvent event)
    {
        ButtonController.soundBtHandler(event);
    }

    // this method is to handle the startGame button in the main menu
    public void startGameBtHandler(ActionEvent event)
    {
        String stringLevel = selectLevelBox.getValue().toString();
        if (stringLevel.equalsIgnoreCase("easy"))
            selectedLevel = 3;
        else if (stringLevel.equalsIgnoreCase("medium"))
            selectedLevel = 2;
        else
            selectedLevel = 1;
        ButtonController.startGameBtHandler(event);
    }

    // this method is to handle the exitGame button in the main menu
    public void exitGameBtHandler(ActionEvent event)
    {
        ButtonController.exitGameBtHandler(event);
    }
}
