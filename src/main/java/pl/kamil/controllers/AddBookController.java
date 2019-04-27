package pl.kamil.controllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.kamil.DB.DBManager;
import pl.kamil.models.Book;

import java.sql.SQLException;

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
	void saveBook(ActionEvent event) throws SQLException
	{
		Book book = new Book();
		book.setAuthor(authorTextField.getText());
		book.setBookName(bookNameTextField.getText());
		book.setPublishedDate(releaseDateTextField.getText());
		Dao<Book, Long> accountDao = DaoManager.createDao(DBManager.getConnectionSource(), Book.class);
		accountDao.create(book);
	}

}

