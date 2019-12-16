package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable
{
    @FXML
        private ChoiceBox selectLevelBox;
    ObservableList<String> levelBox = FXCollections.observableArrayList("Easy", "Medium", "Hard");
    public static int selectedLevel = 2;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        selectLevelBox.setValue("Medium");
        selectLevelBox.setItems(levelBox);
        MediaController.initializeMedia();
    }

    public void soundBtHandler(ActionEvent event)
    {
        ButtonController.soundBtHandler(event);
    }

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

    public void exitGameBtHandler(ActionEvent event)
    {
        ButtonController.exitGameBtHandler(event);
    }
}
