package controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import sample.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable
{

    @FXML
        private static Button soundBt;

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
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        MediaController.initializeMedia();
    }

    public void soundBtHandler()
    {

    }
}
