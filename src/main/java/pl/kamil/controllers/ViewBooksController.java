package pl.kamil.controllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.PreparedQuery;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pl.kamil.database.DAO.BookDao;
import pl.kamil.database.DBManager;
import pl.kamil.database.mapping.models.Book;
import pl.kamil.modelsFX.BookFx;
import pl.kamil.modelsFX.BookModel;
import pl.kamil.utils.Converter;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ViewBooksController
{
	@FXML
	private TableView<BookFx> tableView;
	@FXML
	private TableColumn<BookFx, String> nameColumn;
	@FXML
	private TableColumn<BookFx, String> authorColumn;
	@FXML
	private TableColumn<BookFx, String> publisherColumn;
	@FXML
	private TableColumn<BookFx, String> releaseDateColumn;
	@FXML
	private Button deleteBookButton;
	@FXML
	private Button returnButton;
	@FXML
	private TextField bookNameTextField;

	private BookModel bookModel = new BookModel();

	@FXML
	private void closeStageAndReturn(ActionEvent event)
	{
		Stage stage = (Stage) returnButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void deleteBook(ActionEvent event)
	{
		ObservableList<BookFx> selectedItems = tableView.getSelectionModel().getSelectedItems();
		List<Book> booksToDelete = selectedItems.stream().map(Converter::convertBookFxToBook).collect(Collectors.toList());
		tableView.getItems().removeAll(selectedItems);
		BookDao bookDao = new BookDao();
		bookDao.delete(booksToDelete, Book.class);
	}

	@FXML
	private void searchForBook(InputEvent event)
	{
		try
		{
			tableView.getItems().clear();
			Dao<Book, Long> memberDao = DaoManager.createDao(DBManager.getConnectionSource(), Book.class);
			PreparedQuery<Book> query = memberDao.queryBuilder().where().like("NAME", bookNameTextField.getText() + "%").prepare();
			List<Book> books = memberDao.query(query);
			List<BookFx> queredBooks = books.stream().map(Converter::convertBookToBookFx).collect(Collectors.toList());
			tableView.getItems().addAll(queredBooks);
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	public void initialize()
	{
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
		publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
		releaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));

		makeCellsWrap(nameColumn);
		makeCellsWrap(authorColumn);
		makeCellsWrap(publisherColumn);
		makeCellsWrap(releaseDateColumn);

		try
		{
			Dao<Book, Long> bookDao = DaoManager.createDao(DBManager.getConnectionSource(), Book.class);
			List<BookFx> books = bookDao.queryForAll().stream().map(Converter::convertBookToBookFx).collect(Collectors.toList());
			tableView.getItems().addAll(books);
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	private void makeCellsWrap(TableColumn<BookFx, String> column)
	{
		column.setCellFactory(param ->
		{
			TableCell<BookFx, String> cell = new TableCell<>();
			Text text = new Text();
			cell.setGraphic(text);
			cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
			text.wrappingWidthProperty().bind(param.widthProperty());
			text.textProperty().bind(cell.itemProperty());
			return cell;
		});
	}
}
