package pl.kamil;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.kamil.DB.DBManager;
import pl.kamil.models.Book;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application
{
	@Override
	public void start(Stage primaryStage)
	{
		Parent root = null;
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/fxml/Root.fxml"));
			root = loader.load();
			primaryStage.setTitle("Library Manager");
			primaryStage.setScene(new Scene(root, 800, 600));
			//primaryStage.setMinHeight(565);
			//primaryStage.setMinWidth(800);
			primaryStage.show();

		} catch(IOException e)
		{
			e.printStackTrace();
		}
		DBManager.initDatabase();
		try
		{
			Dao<Book, Long> bookDao = DaoManager.createDao(DBManager.getConnectionSource(), Book.class);
			Book book = new Book("Tytul", "Autor", "Wydawnictwo", "Rok");
			bookDao.create(book);
			bookDao.create(book);
			bookDao.create(book);
			bookDao.create(book);
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
