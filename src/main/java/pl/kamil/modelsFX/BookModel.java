package pl.kamil.modelsFX;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.kamil.database.DAO.BookDao;
import pl.kamil.database.mapping.models.Book;
import pl.kamil.utils.Converter;

public class BookModel
{
	private static ObservableList<BookFx> books = FXCollections.observableArrayList();
	private ObjectProperty<BookFx> bookFxObjectProperty = new SimpleObjectProperty<>();
	private ObjectProperty<BookFx> bookFxObjectPropertyEdit = new SimpleObjectProperty<>();

	public void init()
	{
		BookDao bookDao = new BookDao();
	}

	public void saveBookInDataBase()
	{
		BookFx bookFx = bookFxObjectProperty.get();
		Book book = Converter.convertBookFxToBook(bookFx);
		BookDao bookDao = new BookDao();
		bookDao.create(book);
		books.add(bookFx);
	}

	public void deleteBookInDataBase()
	{
		BookFx bookFx = bookFxObjectPropertyEdit.get();
		Book book = Converter.convertBookFxToBook(bookFx);
		BookDao bookDao = new BookDao();
		bookDao.delete(book);
		books.remove(bookFx);
	}

}
