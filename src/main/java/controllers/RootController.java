package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class RootController
{
	@FXML
	private Button addBookButton;

	@FXML
	void addBook(ActionEvent event)
	{
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/fxml/AddBook.fxml"));
		Parent root = null;
		try
		{
			root = loader.load();
			stage.setTitle("Add Book!");
			stage.setScene(new Scene(root));
			stage.show();
		} catch(IOException e)
		{
			e.printStackTrace();
		}

	}
}
