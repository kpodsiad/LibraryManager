package pl.kamil.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pl.kamil.models.MemberFx;
import pl.kamil.models.MemberModel;

import java.util.stream.Collectors;

public class ViewMembersController
{

	@FXML
	private TableView<MemberFx> tableView;
	@FXML
	private TableColumn<MemberFx, String> firstNameColumn;
	@FXML
	private TableColumn<MemberFx, String> lastNameColumn;
	@FXML
	private TableColumn<MemberFx, String> emailColumn;
	@FXML
	private TableColumn<MemberFx, String> phoneColumn;
	@FXML
	private Button deleteMemberButton;
	@FXML
	private Button returnButton;
	@FXML
	private TextField memberNameTextField;

	private MemberModel memberModel = new MemberModel();

	@FXML
	void closeStageAndReturn()
	{
		Stage stage = (Stage) returnButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	void deleteMember()
	{
		ObservableList<MemberFx> selectedItems = tableView.getSelectionModel().getSelectedItems();
		memberModel.deleteBooksInDataBase(selectedItems);
		tableView.getItems().removeAll(selectedItems);
	}

	@FXML
	void searchForMember()
	{
		String partialName = memberNameTextField.getText();
		if(partialName == null)
			return;

		tableView.getItems().clear();

		if(partialName.equals(""))
			tableView.getItems().addAll(MemberModel.getMembersFromDataBase());
		else
			tableView.getItems().addAll(MemberModel.getMembersFromDataBase().stream().filter(member -> member.getFirstName().concat(member.getLastName()).contains(partialName)).collect(Collectors.toList()));
	}

	@FXML
	private void initialize()
	{
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
		phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

		makeCellsWrap(firstNameColumn);
		makeCellsWrap(lastNameColumn);
		makeCellsWrap(emailColumn);
		makeCellsWrap(phoneColumn);

		tableView.getItems().clear();
		tableView.getItems().addAll(MemberModel.getMembersFromDataBase());
	}

	private void makeCellsWrap(TableColumn<MemberFx, String> column)
	{
		column.setCellFactory(param ->
		{
			TableCell<MemberFx, String> cell = new TableCell<>();
			Text text = new Text();
			cell.setGraphic(text);
			cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
			text.wrappingWidthProperty().bind(param.widthProperty());
			text.textProperty().bind(cell.itemProperty());
			return cell;
		});
	}
}
