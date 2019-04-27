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
import pl.kamil.models.Member;
import pl.kamil.models.MemberFx;

import java.sql.SQLException;
import java.util.List;
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
	void closeStageAndReturn(ActionEvent event)
	{
		Stage stage = (Stage) returnButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	void deleteMember(ActionEvent event)
	{
		try
		{
			MemberFx selectedItem = tableView.getSelectionModel().getSelectedItem();
			tableView.getItems().remove(selectedItem);
			Dao<Member, Long> memberDao = DaoManager.createDao(DBManager.getConnectionSource(), Member.class);
			memberDao.delete(Converter.convertMemberFxToMember(selectedItem));
		} catch(SQLException e)
		{
			e.printStackTrace();
		}


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

		try
		{
			Dao<Member, Long> memberDao = DaoManager.createDao(DBManager.getConnectionSource(), Member.class);
			List<MemberFx> members = memberDao.queryForAll().stream().map(MemberFx::new).collect(Collectors.toList());
			tableView.getItems().addAll(members);
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
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
