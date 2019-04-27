package pl.kamil.controllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pl.kamil.DB.DBManager;
import pl.kamil.Utils.Converter;
import pl.kamil.models.Book;
import pl.kamil.models.BookFx;

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
	void closeStageAndReturn(ActionEvent event)
	{
		Stage stage = (Stage) returnButton.getScene().getWindow();
		stage.close();
	}


	@FXML
	void deleteBook(ActionEvent event)
	{
		try
		{
			BookFx selectedItem = tableView.getSelectionModel().getSelectedItem();
			tableView.getItems().remove(selectedItem);
			Dao<Book, Long> memberDao = DaoManager.createDao(DBManager.getConnectionSource(), Book.class);
			memberDao.delete(Converter.convertBookFxToBook(selectedItem));
		} catch(SQLException e)
		{
			e.printStackTrace();
		}

	}

	@FXML
	private void initialize()
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
