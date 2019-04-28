package pl.kamil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.kamil.database.DAO.BookDao;
import pl.kamil.database.DBManager;
import pl.kamil.database.mapping.models.Book;

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

		Book book = new Book("Tytul", "Autor", "Wydawnictwo", "Rok");
		BookDao bookDao = new BookDao();
		bookDao.create(book);
		bookDao.create(book);
		bookDao.create(book);
		bookDao.create(book);
		bookDao.create(book);
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
