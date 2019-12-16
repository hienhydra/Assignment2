package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowController
{
    private Stage window;

    public WindowController(Stage window)
    {
        this.window = window;
    }

    public void load(String fxmlFile)
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            window.setScene(new Scene(root));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
