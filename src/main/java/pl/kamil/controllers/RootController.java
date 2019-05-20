package pl.kamil.controllers;

import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.kamil.database.mapping.models.Book;
import pl.kamil.database.mapping.models.Loan;
import pl.kamil.database.mapping.models.Member;
import pl.kamil.models.*;
import pl.kamil.utils.Converter;

import java.io.IOException;
import java.util.Optional;

public class RootController
{
	private static final String ADD_BOOK_FXML = "/view/fxml/AddBook.fxml";
	private static final String ADD_MEMBER_FXML = "/view/fxml/AddMember.fxml";
	private static final String VIEW_BOOKS_FXML = "/view/fxml/ViewBooks.fxml";
	private static final String VIEW_MEMBERS_FXML = "/view/fxml/ViewMembers.fxml";
	private static final String VIEW_LOANS_FXML = "/view/fxml/ViewLoans.fxml";
	private static final String VIEW_PARTICULAR_LOANS_FXML = "/view/fxml/MemberLoans.fxml";

	private static final String ADD_BOOK_GREETING = "Add Book";
	private static final String ADD_MEMBER_GREETING = "Add Member";
	private static final String VIEW_BOOKS_GREETING = "View all books";
	private static final String VIEW_MEMBERS_GREETING = "View all members";
	private static final String VIEW_LOANS_GREETING = "View all loans";
	private static final String VIEW_PARTICULAR_LOANS_GREETING = "Member loans";

	private static final Logger LOGGER = LoggerFactory.getLogger(RootController.class);

	private Stage primaryStage;

	private BookModel bookModel = new BookModel();
	private MemberModel memberModel = new MemberModel();
	private LoanModel loanModel = new LoanModel();

	private SimpleBooleanProperty buttonsProperty = new SimpleBooleanProperty();

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
	private Button viewLoansButton;

	@FXML
	private Label memberNameLabel;
	@FXML
	private Label memberPhoneLabel;
	@FXML
	private Label memberEmailLabel;
	@FXML
	private Label bookTitleLabel;
	@FXML
	private Label bookAuthorLabel;
	@FXML
	private Label bookPublisherLabel;
	@FXML
	private Label bookYearLabel;


	@FXML
	private void initialize()
	{
		memberIssuedBooksButton.disableProperty().bind(Bindings.isEmpty(memberIdTextField.textProperty()));
		addIssueButton.disableProperty().bind(Bindings.isEmpty(memberIdTextField.textProperty()).or(Bindings.isEmpty(bookIdTextField.textProperty())));
		addBookButton.disableProperty().bind(buttonsProperty);
		addMemberButton.disableProperty().bind(buttonsProperty);
		viewBooksButton.disableProperty().bind(buttonsProperty);
		viewMembersButton.disableProperty().bind(buttonsProperty);
		viewLoansButton.disableProperty().bind(buttonsProperty);
	}

	@FXML
	private void addNewIssue()
	{
		long memberId = Long.parseLong(memberIdTextField.getText());
		long bookId = Long.parseLong(bookIdTextField.getText());
		Optional<MemberFx> optionalMemberFx = memberModel.searchForMemberById(memberId);
		Optional<BookFx> optionalBookFx = bookModel.searchForBookById(bookId);
		if(optionalBookFx.isPresent() && optionalMemberFx.isPresent())
		{
			Loan loan = new Loan(Converter.convertBookFxToBook(optionalBookFx.get()), Converter.convertMemberFxToMember(optionalMemberFx.get()));
			loanModel.saveLoanInDataBase(loan);
		}

	}

	@FXML
	void searchForMember()
	{
		if(!memberIdTextField.getText().equals(""))
		{
			long id = Long.parseLong(memberIdTextField.getText());
			Optional<MemberFx> optionalMemberFx = memberModel.searchForMemberById(id);
			if(optionalMemberFx.isPresent())
				setMemberFields(Converter.convertMemberFxToMember(optionalMemberFx.get()));
			else
				clearMemberFields();
		} else
			clearMemberFields();
	}

	private void setMemberFields(Member member)
	{
		memberNameLabel.setText(member.getFirstName().concat(member.getLastName()));
		memberPhoneLabel.setText(member.getPhoneNumber());
		memberEmailLabel.setText(member.getEmail());
	}

	private void clearMemberFields()
	{
		setMemberFields(new Member("", "", "", ""));
	}

	@FXML
	void searchForBook()
	{
		if(!bookIdTextField.getText().equals(""))
		{
			long id = Long.parseLong(bookIdTextField.getText());
			Optional<BookFx> optionalBookFx = bookModel.searchForBookById(id);
			if(optionalBookFx.isPresent())
				setBookFields(Converter.convertBookFxToBook(optionalBookFx.get()));
			else
				clearBookFields();
		} else
			clearBookFields();
	}

	private void clearBookFields()
	{
		setBookFields(new Book("", "", "", ""));
	}

	private void setBookFields(Book book)
	{
		bookTitleLabel.setText(book.getBookName());
		bookAuthorLabel.setText(book.getAuthor());
		bookPublisherLabel.setText(book.getPublisher());
		bookYearLabel.setText(book.getReleasedDate());
	}

	@FXML
	private void displayAddBookView(ActionEvent event)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource(ADD_BOOK_FXML));
			Parent root = loader.load();
			AddBookController addBookController = loader.getController();
			addBookController.setModel(bookModel);
			addBookController.init();
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.initOwner(primaryStage);
			newStage.setTitle(ADD_BOOK_GREETING);
			newStage.setScene(newScene);
			newStage.show();

		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	private void displayAllBooksView(ActionEvent event)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource(VIEW_BOOKS_FXML));
			Parent root = loader.load();
			ViewBooksController viewBooksController = loader.getController();
			viewBooksController.setModel(bookModel);
			viewBooksController.init();
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			buttonsProperty.set(true);
			newStage.setOnCloseRequest(event1 -> buttonsProperty.set(false));
			newStage.setTitle(VIEW_BOOKS_GREETING);
			newStage.setScene(newScene);
			//newStage.setX();
			newStage.show();
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	private void displayAddMemberView(ActionEvent event)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource(ADD_MEMBER_FXML));
			Parent root = loader.load();
			AddMemberController addMemberController = loader.getController();
			addMemberController.setModel(memberModel);
			addMemberController.init();
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.initOwner(primaryStage);
			newStage.setTitle(ADD_MEMBER_GREETING);
			newStage.setScene(newScene);
			newStage.show();
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	private void displayAllMembersView(ActionEvent event)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource(VIEW_MEMBERS_FXML));
			Parent root = loader.load();
			ViewMembersController viewMembersController = loader.getController();
			viewMembersController.setModel(memberModel);
			viewMembersController.init();
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			buttonsProperty.set(true);
			newStage.setOnCloseRequest(event1 -> buttonsProperty.set(false));
			newStage.setTitle(VIEW_MEMBERS_GREETING);
			newStage.setScene(newScene);
			newStage.show();
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	private void displayAllLoansView(ActionEvent event)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource(VIEW_LOANS_FXML));
			Parent root = loader.load();
			ViewLoansController viewLoansController = loader.getController();
			viewLoansController.setModel(loanModel);
			viewLoansController.init();
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			buttonsProperty.set(true);
			newStage.setOnCloseRequest(event1 -> buttonsProperty.set(false));
			newStage.setTitle(VIEW_LOANS_GREETING);
			newStage.setScene(newScene);
			newStage.show();
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	private void viewBooksIssuedToParticularMember(ActionEvent actionEvent)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource(VIEW_PARTICULAR_LOANS_FXML));
			Parent root = loader.load();
			ViewLoansController viewLoansController = loader.getController();
			viewLoansController.setModel(loanModel);
			viewLoansController.init();
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			buttonsProperty.set(true);
			newStage.setOnCloseRequest(event1 -> buttonsProperty.set(false));
			newStage.setTitle(VIEW_PARTICULAR_LOANS_GREETING);
			newStage.setScene(newScene);
			newStage.show();
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public void setPrimaryStage(Stage primaryStage)
	{
		this.primaryStage = primaryStage;
	}
}
