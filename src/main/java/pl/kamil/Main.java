package pl.kamil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.kamil.controllers.RootController;
import pl.kamil.database.DAO.BookDao;
import pl.kamil.database.DAO.MemberDao;
import pl.kamil.database.DBManager;
import pl.kamil.database.mapping.models.Book;
import pl.kamil.database.mapping.models.Member;

import java.io.IOException;

public class Main extends Application
{
	@Override
	public void start(Stage primaryStage)
	{
		DBManager.initDatabase();
		Book book = new Book("Autor", "Tytul", "Wydawnictwo Bardzo Fajne", "Rok", true);
		BookDao bookDao = new BookDao();
		bookDao.create(book);
		bookDao.create(book);
		Member member = new Member("Krzysztof", "Jarzyna", "KrzysiuJarzyna@interia.com", "666666666");
		MemberDao memberDao = new MemberDao();
		memberDao.create(member);
		memberDao.create(member);
//		Loan loan = new Loan(book, member);
//		LoanDao loanDao = new LoanDao();
//		loanDao.create(loan);

		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/fxml/Root.fxml"));
			Parent root = loader.load();
			RootController rootController = loader.getController();
			rootController.setPrimaryStage(primaryStage);
			primaryStage.setTitle("Library Manager");
			primaryStage.setScene(new Scene(root, 800, 600));
			primaryStage.setMinHeight(565);
			primaryStage.setMinWidth(800);
			primaryStage.show();

		} catch(IOException e)
		{
			e.printStackTrace();
		}

	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
