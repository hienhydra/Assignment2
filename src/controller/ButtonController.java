package controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ButtonController
{
    private static final String gamePlayWindow = "../view/GamePlay.fxml";
    private static final String mainMenuWindow = "../view/MainMenu.fxml";

    public static void quitGameBtHandler(ActionEvent event)
    {
        MediaController.stop();
        WindowController window = new WindowController((Stage) ((Node)event.getSource()).getScene().getWindow());
        window.load(mainMenuWindow);
    }

    public static void exitGameBtHandler(ActionEvent event)
    {
        System.exit(0);
    }

    public static void soundBtHandler(ActionEvent event)
    {
        String btImage = "";
        if(MediaController.isPlaying())
        {
            MediaController.pause();
            btImage = "File:src/img/soundOff.png";
        }
        else
        {
            MediaController.play();
            btImage = "File:src/img/soundOn.png";
        }
        ImageView imageView = new ImageView (new Image(btImage));
        imageView.setFitHeight(51);
        imageView.setFitWidth(51);
        Button button = (Button)event.getSource();
        button.setGraphic((imageView));
    }

    public static void startGameBtHandler(ActionEvent event)
    {
        WindowController window = new WindowController((Stage) ((Node)event.getSource()).getScene().getWindow());
        window.load(gamePlayWindow);
    }
}
