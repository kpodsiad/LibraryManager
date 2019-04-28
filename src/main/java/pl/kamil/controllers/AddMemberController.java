package pl.kamil.controllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.kamil.database.DBManager;
import pl.kamil.database.mapping.models.Member;

import java.sql.SQLException;

public class AddMemberController
{

	@FXML
	private TextField firstNameTextField;

	@FXML
	private TextField lastNameTextField;

	@FXML
	private TextField phoneNumberTextField;

	@FXML
	private TextField emailTextField;

	@FXML
	private Button saveButton;

	@FXML
	private Button returnButton;

	@FXML
	private void initialize()
	{
		saveButton.disableProperty().bind(
				Bindings.isEmpty(firstNameTextField.textProperty())
						.or(Bindings.isEmpty(lastNameTextField.textProperty())
								.or(Bindings.isEmpty(phoneNumberTextField.textProperty())
										.or(Bindings.isEmpty(emailTextField.textProperty())
										))));
	}

	@FXML
	void exitCurrentWindowAndReturn(ActionEvent event)
	{
		Stage stage = (Stage) returnButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	void saveMember(ActionEvent event)
	{

		try
		{
			Dao<Member, Long> memberDao = DaoManager.createDao(DBManager.getConnectionSource(), Member.class);
			Member member = new Member();
			member.setFirstName(firstNameTextField.getText());
			member.setLastName(lastNameTextField.getText());
			member.setPhoneNumber(phoneNumberTextField.getText());
			member.setEmail(emailTextField.getText());
			memberDao.create(member);
		} catch(SQLException e)
		{
			e.printStackTrace();
		}

		Stage stage = (Stage) saveButton.getScene().getWindow();
		stage.close();
	}

}
