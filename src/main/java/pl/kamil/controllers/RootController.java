package pl.kamil.controllers;

import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
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

	private ViewLoansController viewLoansController;

	private SimpleBooleanProperty buttonsProperty = new SimpleBooleanProperty();

	@FXML
	private TextField memberIdTextField;
	@FXML
	private Button memberLoansButton;
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
	private Label bookAvailableLabel;

	@FXML
	private Label memberValidLabel;
	@FXML
	private Label bookValidLabel;


	@FXML
	private void initialize()
	{
		addIssueButton.disableProperty().bind(
				Bindings.isEmpty(memberNameLabel.textProperty())
						.or(Bindings.isEmpty(bookTitleLabel.textProperty())
								.or(Bindings.equal(bookAvailableLabel.textProperty(), "No"))));

		addBookButton.disableProperty().bind(buttonsProperty);
		addMemberButton.disableProperty().bind(buttonsProperty);
		viewBooksButton.disableProperty().bind(buttonsProperty);
		viewMembersButton.disableProperty().bind(buttonsProperty);
		viewLoansButton.disableProperty().bind(buttonsProperty);
		memberLoansButton.disableProperty().bind(Bindings.isEmpty(memberNameLabel.textProperty()));
	}

	@FXML
	private void addNewIssue()
	{
		long memberId = Long.parseLong(memberIdTextField.getText());
		long bookId = Long.parseLong(bookIdTextField.getText());
		Optional<MemberFx> optionalMemberFx = memberModel.searchForMemberById(memberId);
		Optional<BookFx> optionalBookFx = bookModel.searchForBookById(bookId);

		//if(optionalBookFx.isPresent() && optionalMemberFx.isPresent())
		{
			BookFx bookFx = optionalBookFx.get();
			Loan loan = new Loan(Converter.convertBookFxToBook(bookFx), Converter.convertMemberFxToMember(optionalMemberFx.get()));
			bookFx.setAvailable(false);
			bookModel.createOrUpdate(bookFx);
			loanModel.saveLoanInDataBase(loan);
			if(viewLoansButton.isDisabled() && viewLoansController != null)
				viewLoansController.refresh();
			searchForBook();
		}

	}

	@FXML
	void searchForMember()
	{
		if(!memberIdTextField.getText().equals(""))
		{
			try
			{
				long id = Long.parseLong(memberIdTextField.getText());
				memberValidLabel.setVisible(false);
				Optional<MemberFx> optionalMemberFx = memberModel.searchForMemberById(id);
				if(optionalMemberFx.isPresent())
				{
					setMemberFields(Converter.convertMemberFxToMember(optionalMemberFx.get()));
					memberValidLabel.setVisible(false);
				} else
				{
					clearMemberFields();
				}
			} catch(NumberFormatException e)
			{
				e.printStackTrace();
				clearMemberFields();
				memberValidLabel.setVisible(true);
			}

		} else
		{
			clearMemberFields();
			memberValidLabel.setVisible(false);
		}
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
			try
			{
				long id = Long.parseLong(bookIdTextField.getText());
				bookValidLabel.setVisible(false);
				Optional<BookFx> optionalBookFx = bookModel.searchForBookById(id);
				if(optionalBookFx.isPresent())
				{
					setBookFields(Converter.convertBookFxToBook(optionalBookFx.get()));
					bookValidLabel.setVisible(false);
				} else
				{
					clearBookFields();
				}
			} catch(NumberFormatException e)
			{
				e.printStackTrace();
				bookValidLabel.setVisible(true);
				clearBookFields();
			}

		} else
		{
			clearBookFields();
			bookValidLabel.setVisible(false);
		}
	}

	private void clearBookFields()
	{
		setBookFields(new Book("", "", "", "", false));
	}

	private void setBookFields(Book book)
	{
		bookTitleLabel.setText(book.getBookName());
		bookAuthorLabel.setText(book.getAuthor());
		bookPublisherLabel.setText(book.getPublisher());
		bookYearLabel.setText(book.getReleasedDate());
		if(book.isAvailable())
			bookAvailableLabel.setText("Yes");
		else if(!bookTitleLabel.getText().equals(""))
			bookAvailableLabel.setText("No");
		else
			bookAvailableLabel.setText("");
	}

	@FXML
	private void displayAddBookView()
	{
		try
		{
			FXMLLoader loader = getFxmlLoader(ADD_BOOK_FXML);
			Parent root = loader.load();
			AddBookController addBookController = loader.getController();
			addBookController.setModel(bookModel);
			addBookController.init();
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			makeStageWithModality(newStage);
			prepareStageAndShow(newScene, newStage, ADD_BOOK_GREETING);
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	private void displayAllBooksView()
	{
		try
		{
			FXMLLoader loader = getFxmlLoader(VIEW_BOOKS_FXML);
			Parent root = loader.load();
			ViewBooksController viewBooksController = loader.getController();
			viewBooksController.setModel(bookModel);
			viewBooksController.setLoanModel(loanModel);
			viewBooksController.refresh();
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			makeButtonsImpact(newStage);
			prepareStageAndShow(newScene, newStage, VIEW_BOOKS_GREETING);
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	private void displayAddMemberView()
	{
		try
		{
			FXMLLoader loader = getFxmlLoader(ADD_MEMBER_FXML);
			Parent root = loader.load();
			AddMemberController addMemberController = loader.getController();
			addMemberController.setModel(memberModel);
			addMemberController.init();
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			makeStageWithModality(newStage);
			prepareStageAndShow(newScene, newStage, ADD_MEMBER_GREETING);
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	private void displayAllMembersView()
	{
		try
		{
			FXMLLoader loader = getFxmlLoader(VIEW_MEMBERS_FXML);
			Parent root = loader.load();
			ViewMembersController viewMembersController = loader.getController();
			viewMembersController.setModel(memberModel);
			viewMembersController.setLoanModel(loanModel);
			viewMembersController.init();
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			makeButtonsImpact(newStage);
			prepareStageAndShow(newScene, newStage, VIEW_MEMBERS_GREETING);

		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	private void displayAllLoansView()
	{
		try
		{
			FXMLLoader loader = getFxmlLoader(VIEW_LOANS_FXML);
			Parent root = loader.load();
			viewLoansController = loader.getController();
			viewLoansController.setModel(loanModel);
			viewLoansController.setBookModel(bookModel);
			viewLoansController.init();
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			makeButtonsImpact(newStage);
			prepareStageAndShow(newScene, newStage, VIEW_LOANS_GREETING);
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	private void viewBooksIssuedToParticularMember()
	{
		try
		{
			FXMLLoader loader = getFxmlLoader(VIEW_PARTICULAR_LOANS_FXML);
			Parent root = loader.load();
			MemberLoansController memberLoansController = loader.getController();
			memberLoansController.setModel(loanModel);
			memberLoansController.setMemberId(Long.parseLong(memberIdTextField.getText()));
			memberLoansController.init();
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			makeButtonsImpact(newStage);
			prepareStageAndShow(newScene, newStage, VIEW_PARTICULAR_LOANS_GREETING);
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	private FXMLLoader getFxmlLoader(String fxmlFile)
	{
		return new FXMLLoader(getClass().getResource(fxmlFile));
	}

	private void prepareStageAndShow(Scene newScene, Stage newStage, String addBookGreeting)
	{
		newStage.setTitle(addBookGreeting);
		newStage.setScene(newScene);
		newStage.show();
	}

	private void makeStageWithModality(Stage newStage)
	{
		newStage.initModality(Modality.APPLICATION_MODAL);
		newStage.initOwner(primaryStage);
	}

	private void makeButtonsImpact(Stage newStage)
	{
		buttonsProperty.set(true);
		newStage.setOnCloseRequest(event1 -> buttonsProperty.set(false));
	}

	public void setPrimaryStage(Stage primaryStage)
	{
		this.primaryStage = primaryStage;
	}
}
