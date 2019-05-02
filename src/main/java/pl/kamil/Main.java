package pl.kamil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.kamil.database.DAO.BookDao;
import pl.kamil.database.DAO.LoanDao;
import pl.kamil.database.DAO.MemberDao;
import pl.kamil.database.DBManager;
import pl.kamil.database.mapping.models.Book;
import pl.kamil.database.mapping.models.Loan;
import pl.kamil.database.mapping.models.Member;

import java.io.IOException;

public class Main extends Application
{
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/fxml/Root.fxml"));
			Parent root = loader.load();
			primaryStage.setTitle("Library Manager");
			primaryStage.setScene(new Scene(root, 800, 600));
			primaryStage.setMinHeight(565);
			primaryStage.setMinWidth(800);
			primaryStage.show();

		} catch(IOException e)
		{
			e.printStackTrace();
		}
		DBManager.initDatabase();

		Book book = new Book("Tytul", "Autor", "Wydawnictwo Bardzo Fajne", "Rok");
		BookDao bookDao = new BookDao();
		bookDao.create(book);
		bookDao.create(book);
		bookDao.create(book);
		bookDao.create(book);
		bookDao.create(book);
		Member member = new Member("Krzysztof", "Jarzyna", "Gansta@szczecin.com", "666666666");
		MemberDao memberDao = new MemberDao();
		memberDao.create(member);
		LoanDao loanDao = new LoanDao();
		Loan loan = new Loan(36L, 10L);
		loanDao.create(loan);
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
