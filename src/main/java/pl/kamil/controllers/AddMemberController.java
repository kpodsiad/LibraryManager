package pl.kamil.controllers;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.kamil.models.MemberModel;

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

	private MemberModel memberModel = new MemberModel();

	@FXML
	private void initialize()
	{
		saveButton.disableProperty().bind(
				Bindings.isEmpty(firstNameTextField.textProperty())
						.or(Bindings.isEmpty(lastNameTextField.textProperty())
								.or(Bindings.isEmpty(phoneNumberTextField.textProperty())
										.or(Bindings.isEmpty(emailTextField.textProperty())
										))));
		memberModel.getMemberFxObjectProperty().firstNameProperty().bind(firstNameTextField.textProperty());
		memberModel.getMemberFxObjectProperty().lastNameProperty().bind(lastNameTextField.textProperty());
		memberModel.getMemberFxObjectProperty().phoneNumberProperty().bind(phoneNumberTextField.textProperty());
		memberModel.getMemberFxObjectProperty().emailProperty().bind(emailTextField.textProperty());
	}

	@FXML
	void exitCurrentWindowAndReturn()
	{
		Stage stage = (Stage) returnButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	void saveMember()
	{
		memberModel.saveMemberInDataBase();
		exitCurrentWindowAndReturn();
	}

}
