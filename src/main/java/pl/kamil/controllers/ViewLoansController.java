package pl.kamil.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pl.kamil.models.BookFx;
import pl.kamil.models.BookModel;
import pl.kamil.models.LoanFx;
import pl.kamil.models.LoanModel;

import java.util.Collection;
import java.util.stream.Collectors;

public class ViewLoansController
{
	@FXML
	private TableView<LoanFx> tableView;
	@FXML
	private TableColumn<LoanFx, Long> loanIdColumn;
	@FXML
	private TableColumn<LoanFx, String> bookTitleColumn;
	@FXML
	private TableColumn<LoanFx, Long> bookIdColumn;
	@FXML
	private TableColumn<LoanFx, Long> memberIdColumn;
	@FXML
	private TextField bookTitleTextField;
	@FXML
	private Button deleteLoanButton;
	@FXML
	private Button returnButton;

	private LoanModel loanModel;
	private BookModel bookModel;


	@FXML
	private void initialize()
	{
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		loanIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		bookTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
		memberIdColumn.setCellValueFactory(new PropertyValueFactory<>("memberId"));
	}

	@FXML
	void closeStageAndReturn()
	{
		Stage stage = (Stage) returnButton.getScene().getWindow();
		stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
	}

	@FXML
	private void searchForBook()
	{
		String partialTitle = bookTitleTextField.getText();
		if(partialTitle == null)
			return;

		tableView.getItems().clear();
		tableView.getItems().addAll(loanModel.searchForBook(partialTitle));

	}

	@FXML
	private void deleteLoan()
	{
		ObservableList<LoanFx> selectedItems = tableView.getSelectionModel().getSelectedItems();
		Collection<Long> loansIds = selectedItems.stream().map(LoanFx::getId).collect(Collectors.toList());
		Collection<Long> booksIds = selectedItems.stream().map(LoanFx::getBookId).collect(Collectors.toList());
		ObservableList<BookFx> booksFromDataBase = bookModel.getBooksFromDataBase();
		for(int i = 0; i < booksFromDataBase.size(); ++i)
		{
			BookFx bookFx = booksFromDataBase.get(i);
			if(booksIds.contains(bookFx.getId()))
			{
				bookFx.setAvailable(true);
				bookModel.createOrUpdate(bookFx);
			}
		}
		loanModel.deleteLoansByIds(loansIds);
		tableView.getItems().removeAll(selectedItems);
	}

	public void setModel(LoanModel loanModel)
	{
		this.loanModel = loanModel;
	}

	public void setBookModel(BookModel bookModel)
	{
		this.bookModel = bookModel;
	}

	public void init()
	{
		refresh();
	}

	public void refresh()
	{
		tableView.getItems().clear();
		tableView.getItems().addAll(loanModel.getLoansFromDataBase());
	}


}
