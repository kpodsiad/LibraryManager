package pl.kamil.controllers;

import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.kamil.models.BookModel;
import pl.kamil.utils.Regexes;

import java.util.regex.Pattern;

public class AddBookController
{


	@FXML
	private TextField bookTitleTextField;
	@FXML
	private TextField authorTextField;
	@FXML
	private TextField publisherTextField;
	@FXML
	private TextField releaseYearTextField;
	@FXML
	private Label titleValidateLabel;
	@FXML
	private Label authorValidateLabel;
	@FXML
	private Label publisherValidateLabel;
	@FXML
	private Label yearValidateLabel;
	@FXML
	private Button saveButton;
	@FXML
	private Button returnButton;
	private BookModel bookModel;


	@FXML
	private void exitCurrentWindowAndReturn()
	{
		Stage stage = (Stage) returnButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void saveBook()
	{
		bookModel.saveBookInDataBase();
		exitCurrentWindowAndReturn();
	}

	@FXML
	void validateTitle()
	{
		validate(bookTitleTextField, Regexes.ALPHANUMERIC, titleValidateLabel);
	}

	@FXML
	void validateAuthor()
	{
		validate(authorTextField, Regexes.ALPHANUMERIC, authorValidateLabel);
	}

	@FXML
	void validatePublisher()
	{
		validate(publisherTextField, Regexes.ONLY_LETTERS, publisherValidateLabel);
	}

	@FXML
	void validateYear()
	{
		validate(releaseYearTextField, Regexes.YEAR, yearValidateLabel);
	}

	private void validate(TextField textField, Pattern pattern, Label label)
	{
		String textFromTextField = textField.getText();
		if(textFromTextField.equals("") || pattern.matcher(textFromTextField).matches())
			label.setVisible(false);
		else
			label.setVisible(true);
	}

	public void init()
	{

		BooleanBinding saveButtonDisableBinding =
				authorTextField.textProperty().isEmpty()
						.or(authorTextField.textProperty().isEmpty())
						.or(publisherTextField.textProperty().isEmpty())
						.or(releaseYearTextField.textProperty().isEmpty())
						.or(titleValidateLabel.visibleProperty())
						.or(authorValidateLabel.visibleProperty())
						.or(publisherValidateLabel.visibleProperty())
						.or(yearValidateLabel.visibleProperty());

		saveButton.disableProperty().bind(saveButtonDisableBinding);

		bookModel.getBookFxObjectProperty().nameProperty().bind(bookTitleTextField.textProperty());
		bookModel.getBookFxObjectProperty().authorProperty().bind(authorTextField.textProperty());
		bookModel.getBookFxObjectProperty().publisherProperty().bind(publisherTextField.textProperty());
		bookModel.getBookFxObjectProperty().releaseDateProperty().bind(releaseYearTextField.textProperty());
	}

	public void setModel(BookModel bookModel)
	{
		this.bookModel = bookModel;
	}
}

