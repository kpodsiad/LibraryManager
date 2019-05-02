package pl.kamil.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pl.kamil.models.LoanFx;
import pl.kamil.models.LoanModel;

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
	private Button returnButton;

	private LoanModel loanModel = new LoanModel();


	@FXML
	private void initialize()
	{
		loanIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		bookTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
		memberIdColumn.setCellValueFactory(new PropertyValueFactory<>("memberId"));

		tableView.getItems().clear();
		loanModel.refresh();
		tableView.getItems().addAll(loanModel.getLoans());
	}

	@FXML
	void closeStageAndReturn()
	{
		Stage stage = (Stage) returnButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	void searchForBook()
	{

	}

}
