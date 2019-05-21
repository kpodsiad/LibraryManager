package pl.kamil.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pl.kamil.models.LoanFx;
import pl.kamil.models.LoanModel;

import java.util.stream.Collectors;

public class MemberLoansController
{
	@FXML
	private TableView<LoanFx> tableView;
	@FXML
	private TableColumn<LoanFx, Long> loanIdColumn;
	@FXML
	private TableColumn<LoanFx, Long> bookIdColumn;
	@FXML
	private TableColumn<LoanFx, String> bookTitleColumn;
	@FXML
	private Button returnButton;

	private LoanModel loanModel;
	private Long memberId;

	@FXML
	void closeStageAndReturn()
	{
		Stage stage = (Stage) returnButton.getScene().getWindow();
		stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
	}

	@FXML
	private void initialize()
	{
		loanIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		bookTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
	}

	public void setModel(LoanModel loanModel)
	{
		this.loanModel = loanModel;
	}

	public void setMemberId(Long memberId)
	{
		this.memberId = memberId;
	}

	public void init()
	{
		tableView.getItems().clear();
		tableView.getItems().addAll(loanModel.getLoansFromDataBase().stream().filter(loanFx -> loanFx.getMemberId() == memberId).collect(Collectors.toList()));
	}
}
