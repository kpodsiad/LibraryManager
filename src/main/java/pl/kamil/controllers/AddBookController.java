package pl.kamil.controllers;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
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

	private BookModel bookModel;

	@FXML
	private void initialize()
	{
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

	public void init()
	{
		saveButton.disableProperty().bind(
				Bindings.isEmpty(authorTextField.textProperty())
						.or(Bindings.isEmpty(authorTextField.textProperty())
								.or(Bindings.isEmpty(publisherTextField.textProperty())
										.or(Bindings.isEmpty(releaseDateTextField.textProperty())
										))));
		//TODO validation
		SimpleBooleanProperty titleValid = new SimpleBooleanProperty();

		bookModel.getBookFxObjectProperty().nameProperty().bind(bookNameTextField.textProperty());
		bookModel.getBookFxObjectProperty().authorProperty().bind(authorTextField.textProperty());
		bookModel.getBookFxObjectProperty().publisherProperty().bind(publisherTextField.textProperty());
		bookModel.getBookFxObjectProperty().releaseDateProperty().bind(releaseDateTextField.textProperty());
	}

	public void setModel(BookModel bookModel)
	{
		this.bookModel = bookModel;
		System.out.println("Ustawianie");
	}
}

