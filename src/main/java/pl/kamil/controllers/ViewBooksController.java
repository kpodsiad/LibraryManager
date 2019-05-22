package pl.kamil.controllers;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pl.kamil.models.BookFx;
import pl.kamil.models.BookModel;
import pl.kamil.models.LoanModel;

import java.util.Collection;
import java.util.stream.Collectors;

public class ViewBooksController
{
	@FXML
	private TableView<BookFx> tableView;
	@FXML
	private TableColumn<BookFx, Long> idColumn;
	@FXML
	private TableColumn<BookFx, String> availableColumn;
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

	private BookModel bookModel;
	private LoanModel loanModel;

	public BookModel getBookModel()
	{
		return bookModel;
	}

	@FXML
	private void closeStageAndReturn()
	{
		Stage stage = (Stage) returnButton.getScene().getWindow();
		stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
	}

	@FXML
	private void deleteBook()
	{
		ObservableList<BookFx> selectedItems = tableView.getSelectionModel().getSelectedItems();
		Collection<Long> loansToDelete = selectedItems.stream().map(BookFx::getId).collect(Collectors.toList());
		loanModel.deleteLoansByBooksIds(loansToDelete);
		bookModel.deleteBooksInDataBase(selectedItems);
		tableView.getItems().removeAll(selectedItems);
	}

	@FXML
	private void searchForBook()
	{
		String partialName = bookNameTextField.getText();
		if(partialName == null)
			return;

		tableView.getItems().clear();
		tableView.getItems().addAll(bookModel.searchForBook(partialName));
	}

	@FXML
	public void initialize()
	{
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		availableColumn.setCellValueFactory(param -> Bindings.createStringBinding(() -> param.getValue().isAvailableInString()));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
		publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
		releaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));

		makeCellsWrap(nameColumn);
		makeCellsWrap(authorColumn);
		makeCellsWrap(publisherColumn);
		makeCellsWrap(releaseDateColumn);
	}

	public void refresh()
	{
		tableView.getItems().clear();
		tableView.getItems().addAll(bookModel.getBooksFromDataBase());
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

	public void setModel(BookModel bookModel)
	{
		this.bookModel = bookModel;
	}

	public void setLoanModel(LoanModel loanModel)
	{
		this.loanModel = loanModel;
	}

}
