package pl.kamil.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.kamil.database.DAO.BookDao;
import pl.kamil.database.mapping.models.Book;
import pl.kamil.utils.Converter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class BookModel
{
	private static final ObservableList<BookFx> booksFromDataBase = FXCollections.observableArrayList();
	private static final BookDao bookDao = new BookDao();
	private final ObjectProperty<BookFx> bookFxObjectProperty = new SimpleObjectProperty<>(new BookFx());
	private final ObjectProperty<BookFx> bookFxObjectPropertyEdit = new SimpleObjectProperty<>(new BookFx());

	public BookModel()
	{
		booksFromDataBase.clear();
		booksFromDataBase.addAll(bookDao.queryForAll().stream().map(Converter::convertBookToBookFx).collect(Collectors.toList()));
	}

	public static void refresh()
	{
		booksFromDataBase.clear();
		booksFromDataBase.addAll(bookDao.queryForAll().stream().map(Converter::convertBookToBookFx).collect(Collectors.toList()));
	}

	public void saveBookInDataBase()
	{
		BookFx bookFx = bookFxObjectProperty.get();
		Book book = Converter.convertBookFxToBook(bookFx);
		//BookDao bookDao = new BookDao();
		bookDao.create(book);
		booksFromDataBase.add(bookFx);
	}

	public void deleteBookInDataBase()
	{
		BookFx bookFx = bookFxObjectPropertyEdit.get();
		Book book = Converter.convertBookFxToBook(bookFx);
		//BookDao bookDao = new BookDao();
		bookDao.delete(book);
		booksFromDataBase.remove(bookFx);
	}

	public void deleteBooksInDataBase(Collection<BookFx> books)
	{
		List<Book> booksToDelete = books.stream().map(Converter::convertBookFxToBook).collect(Collectors.toList());
		BookModel.booksFromDataBase.removeAll(books);
		bookDao.delete(booksToDelete);
	}

	public BookFx getBookFxObjectProperty()
	{
		return bookFxObjectProperty.get();
	}

	public ObjectProperty<BookFx> bookFxObjectPropertyProperty()
	{
		return bookFxObjectProperty;
	}

	public BookFx getBookFxObjectPropertyEdit()
	{
		return bookFxObjectPropertyEdit.get();
	}

	public ObjectProperty<BookFx> bookFxObjectPropertyEditProperty()
	{
		return bookFxObjectPropertyEdit;
	}

	public static ObservableList<BookFx> getBooksFromDataBase()
	{
		return booksFromDataBase;
	}

}
