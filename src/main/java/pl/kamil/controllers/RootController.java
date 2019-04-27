package pl.kamil.controllers;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class RootController
{
	private static final String ADD_BOOK_FXML = "/view/fxml/AddBook.fxml";
	private static final String ADD_MEMBER_FXML = "/view/fxml/AddMember.fxml";
	private static final String VIEW_BOOKS_FXML = "/view/fxml/ViewBooks.fxml";
	private static final String VIEW_MEMBERS_FXML = "/view/fxml/ViewMembers.fxml";

	private static final String ADD_BOOK_GREETING = "Add Book";
	private static final String ADD_MEMBER_GREETING = "Add Member";
	private static final String VIEW_BOOKS_GREETING = "View all books";
	private static final String VIEW_MEMBERS_GREETING = "View all members";

	@FXML
	private TextField memberIdTextField;
	@FXML
	private Button memberIssuedBooksButton;
	@FXML
	private TextField bookIdTextField;
	@FXML
	private Button addIssueButton;
	@FXML
	private Button addBookButton;
	@FXML
	private Button addMemberButton;
	@FXML
	private Button viewBooksButton;
	@FXML
	private Button viewMembersButton;

	@FXML
	private void initialize()
	{
		memberIssuedBooksButton.disableProperty().bind(Bindings.isEmpty(memberIdTextField.textProperty()));
		addIssueButton.disableProperty().bind(Bindings.isEmpty(memberIdTextField.textProperty()).or(Bindings.isEmpty(bookIdTextField.textProperty())));
	}

	@FXML
	private void addNewIssue(ActionEvent event)
	{

	}

	@FXML
	private void displayAddBookView(ActionEvent event)
	{
		Stage stage = getCurrentStage(event);
		displayNewView(ADD_BOOK_FXML, ADD_BOOK_GREETING, stage);
	}

	private Stage getCurrentStage(ActionEvent event)
	{
		return (Stage) ((Button) event.getSource()).getScene().getWindow();
	}

	@FXML
	private void displayAddMemberView(ActionEvent event)
	{
		Stage stage = getCurrentStage(event);
		displayNewView(ADD_MEMBER_FXML, ADD_MEMBER_GREETING, stage);
	}

	@FXML
	private void displayAllBooksView(ActionEvent event)
	{
		Stage stage = getCurrentStage(event);
		displayNewView(VIEW_BOOKS_FXML, VIEW_BOOKS_GREETING, stage);
	}

	@FXML
	private void displayAllMembersView(ActionEvent event)
	{
		Stage stage = getCurrentStage(event);
		displayNewView(VIEW_MEMBERS_FXML, VIEW_MEMBERS_GREETING, stage);
	}

	private void displayNewView(String fxmlFile, String greeting, Stage primaryStage)
	{
		Stage stage = getNewStageAsChildOfCurrentPrimaryStage(primaryStage);
		FXMLLoader loader = getFxmlLoader(fxmlFile);
		try
		{
			Parent root = loader.load();
			stage.setTitle(greeting);
			stage.setScene(new Scene(root));
			stage.show();
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	private FXMLLoader getFxmlLoader(String fxmlFile)
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(fxmlFile));
		return loader;
	}

	private Stage getNewStageAsChildOfCurrentPrimaryStage(Stage primaryStage)
	{
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(primaryStage);
		return stage;
	}

	@FXML
	private void viewBooksIssuedToParticularMember(ActionEvent actionEvent)
	{
		//TODO
		System.out.println("BOOKS issued by member");
	}
}
