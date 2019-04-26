package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddBookController
{

	@FXML
	private TextField bookNameTextField;

	@FXML
	private TextField authorTextField;

	@FXML
	private TextField publisherTextField;

	@FXML
	private TextField releaseDateTextField;

	@FXML
	private Button saveButton;

	@FXML
	private Button cancelButton;

	@FXML
	void cancelSaving(ActionEvent event)
	{
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	void saveBook(ActionEvent event)
	{
		System.out.println("Saving Book");
	}

}

