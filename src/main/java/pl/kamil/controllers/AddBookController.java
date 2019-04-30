package pl.kamil.controllers;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.kamil.models.BookModel;

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
	private Button returnButton;

	private BookModel bookModel = new BookModel();

	@FXML
	private void initialize()
	{
		saveButton.disableProperty().bind(
				Bindings.isEmpty(bookNameTextField.textProperty())
						.or(Bindings.isEmpty(authorTextField.textProperty())
								.or(Bindings.isEmpty(publisherTextField.textProperty())
										.or(Bindings.isEmpty(releaseDateTextField.textProperty())
										))));
		//TODO validation
		bookModel.getBookFxObjectProperty().nameProperty().bind(bookNameTextField.textProperty());
		bookModel.getBookFxObjectProperty().authorProperty().bind(authorTextField.textProperty());
		bookModel.getBookFxObjectProperty().publisherProperty().bind(publisherTextField.textProperty());
		bookModel.getBookFxObjectProperty().releaseDateProperty().bind(releaseDateTextField.textProperty());
	}

	@FXML
	private void exitCurrentWindowAndReturn()
	{
		Stage stage = (Stage) returnButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void saveBook(ActionEvent event)
	{
		bookModel.saveBookInDataBase();
		exitCurrentWindowAndReturn();
	}

}

