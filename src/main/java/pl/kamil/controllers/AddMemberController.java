package pl.kamil.controllers;

import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.kamil.models.MemberModel;
import pl.kamil.utils.Regexes;

import java.util.regex.Pattern;

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
	private Label firstNameValidateLabel;
	@FXML
	private Label lastNameValidateLabel;
	@FXML
	private Label phoneNumberValidateLabel;
	@FXML
	private Label emailAddressValidateLabel;

	private MemberModel memberModel;

	@FXML
	private void initialize()
	{

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

	public void init()
	{
		BooleanBinding saveMemberDisableBinding =
				firstNameTextField.textProperty().isEmpty()
						.or(lastNameTextField.textProperty().isEmpty())
						.or(phoneNumberTextField.textProperty().isEmpty())
						.or(emailTextField.textProperty().isEmpty())
						.or(firstNameValidateLabel.visibleProperty())
						.or(lastNameValidateLabel.visibleProperty())
						.or(phoneNumberValidateLabel.visibleProperty())
						.or(emailAddressValidateLabel.visibleProperty());

		saveButton.disableProperty().bind(saveMemberDisableBinding);

		memberModel.getMemberFxObjectProperty().firstNameProperty().bind(firstNameTextField.textProperty());
		memberModel.getMemberFxObjectProperty().lastNameProperty().bind(lastNameTextField.textProperty());
		memberModel.getMemberFxObjectProperty().phoneNumberProperty().bind(phoneNumberTextField.textProperty());
		memberModel.getMemberFxObjectProperty().emailProperty().bind(emailTextField.textProperty());
	}

	@FXML
	void validateFirstName()
	{
		validate(firstNameTextField, Regexes.ONLY_LETTERS, firstNameValidateLabel);
	}

	@FXML
	void validateLastName()
	{
		validate(lastNameTextField, Regexes.ONLY_LETTERS, lastNameValidateLabel);
	}

	@FXML
	void validatePhoneNumber()
	{
		validate(phoneNumberTextField, Regexes.PHONE, phoneNumberValidateLabel);
	}

	@FXML
	void validateEmailAddress()
	{
		validate(emailTextField, Regexes.ALPHANUMERIC, emailAddressValidateLabel);
	}

	private void validate(TextField textField, Pattern pattern, Label label)
	{
		String textFromTextField = textField.getText();
		if(textFromTextField.equals("") || pattern.matcher(textFromTextField).matches())
			label.setVisible(false);
		else
			label.setVisible(true);
	}

	public void setModel(MemberModel memberModel)
	{
		this.memberModel = memberModel;
	}
}
