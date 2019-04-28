package pl.kamil.controllers;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.kamil.database.DAO.BookDao;
import pl.kamil.database.mapping.models.Book;

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

	@FXML
	private void initialize()
	{
		saveButton.disableProperty().bind(
				Bindings.isEmpty(bookNameTextField.textProperty())
						.or(Bindings.isEmpty(authorTextField.textProperty())
								.or(Bindings.isEmpty(publisherTextField.textProperty())
										.or(Bindings.isEmpty(releaseDateTextField.textProperty())
										))));
	}

	@FXML
	private void exitCurrentWindowAndReturn(ActionEvent event)
	{
		Stage stage = (Stage) returnButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void saveBook(ActionEvent event)
	{
		Book book = new Book();
		book.setAuthor(authorTextField.getText());
		book.setBookName(bookNameTextField.getText());
		book.setPublisher(publisherTextField.getText());
		book.setReleasedDate(releaseDateTextField.getText());
		BookDao bookDao = new BookDao();
		bookDao.createOrUpdate(book);

		Stage stage = (Stage) saveButton.getScene().getWindow();
		stage.close();
	}

}

