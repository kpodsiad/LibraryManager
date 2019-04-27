package pl.kamil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.kamil.DB.DBManager;

import java.io.IOException;

public class Main extends Application
{
	@Override
	public void start(Stage primaryStage)
	{
		Parent root = null;
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/fxml/Root.fxml"));
			root = loader.load();
			primaryStage.setTitle("Library Manager");
			primaryStage.setScene(new Scene(root, 800, 600));
			primaryStage.setMinHeight(565);
			primaryStage.setHeight(600);
			primaryStage.setMinWidth(800);
			primaryStage.show();

		} catch(IOException e)
		{
			e.printStackTrace();
		}
		DBManager.initDatabase();
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
