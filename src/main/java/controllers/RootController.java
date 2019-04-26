package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class RootController
{
	private static final String ADD_BOOK_FXML = "/view/fxml/AddBook.fxml";
	private static final String ADD_MEMBER_FXML = "/view/fxml/AddMember.fxml";
	private static final String VIEW_BOOKS_FXML = "/view/fxml/ViewBooks.fxml";
	private static final String VIEW_MEMBERS_FXML = "/view/fxml/ViewMembers.fxml";

	@FXML
	private Button addBookButton;

	@FXML
	private Button addMemberButton;

	@FXML
	private Button viewBooksButton;

	@FXML
	private Button viewMembersButton;

	private Stage primaryStage;

	@FXML
	private void initialize()
	{
	}

	@FXML
	private void displayAddBookView(ActionEvent event)
	{
		Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
		displayNewView(ADD_BOOK_FXML, stage);
	}

	@FXML
	private void displayAddMemberView(ActionEvent event)
	{
		Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
		displayNewView(ADD_MEMBER_FXML, stage);
	}

	@FXML
	private void displayAllBooksView(ActionEvent event)
	{

	}

	@FXML
	private void displayAllMembersView(ActionEvent event)
	{

	}

	private void displayNewView(String fxmlFile, Stage currentStage)
	{
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		primaryStage = (Stage) this.addBookButton.getScene().getWindow();
		stage.initOwner(primaryStage);
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(fxmlFile));
		Parent root = null;
		try
		{
			root = loader.load();
			stage.setTitle("Add Book!");
			stage.setScene(new Scene(root));
			stage.show();
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
