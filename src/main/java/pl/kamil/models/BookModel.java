package pl.kamil.models;

import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.kamil.database.DAO.BookDao;
import pl.kamil.database.mapping.models.Book;
import pl.kamil.utils.Converter;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookModel
{
	private final ObservableList<BookFx> booksFromDataBase = FXCollections.observableArrayList();
	private final BookDao bookDao = new BookDao();
	private final ObjectProperty<BookFx> bookFxObjectProperty = new SimpleObjectProperty<>(new BookFx());
	private final ObjectProperty<BookFx> bookFxObjectPropertyEdit = new SimpleObjectProperty<>(new BookFx());
	private static final Logger LOGGER = LoggerFactory.getLogger(BookModel.class);

	public BookModel()
	{
		refresh();
	}

	public void refresh()
	{
		booksFromDataBase.clear();
		booksFromDataBase.addAll(bookDao.queryForAll().stream().map(Converter::convertBookToBookFx).collect(Collectors.toList()));
	}

	public void saveBookInDataBase()
	{
		BookFx bookFx = bookFxObjectProperty.get();
		Book book = Converter.convertBookFxToBook(bookFx);
		bookDao.create(book);
		booksFromDataBase.add(Converter.convertBookToBookFx(book));
	}

	public void deleteBookInDataBase()
	{
		BookFx bookFx = bookFxObjectPropertyEdit.get();
		Book book = Converter.convertBookFxToBook(bookFx);
		bookDao.delete(book);
		booksFromDataBase.remove(bookFx);
	}

	public void createOrUpdate(BookFx bookFx)
	{
		Book book = Converter.convertBookFxToBook(bookFx);
		bookDao.createOrUpdate(book);
		booksFromDataBase.forEach(bookFx1 ->
		{
			if(bookFx1.getId() == bookFx.getId())
				bookFx1 = bookFx;
		});
	}

	public Collection<BookFx> searchForBook(String partialName)
	{
		if(partialName.equals(""))
			return booksFromDataBase;
		else
			return booksFromDataBase.stream().filter(book -> book.getName().contains(partialName)).collect(Collectors.toList());
	}

	public Optional<BookFx> searchForBookById(Long id)
	{
		return booksFromDataBase.stream().filter(bookFx -> bookFx.getId() == id).findAny();
	}

	public void deleteBooksInDataBase(Collection<BookFx> books)
	{
		List<Book> booksToDelete = books.stream().map(Converter::convertBookFxToBook).collect(Collectors.toList());
		booksFromDataBase.removeAll(books);
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

	public ObservableList<BookFx> getBooksFromDataBase()
	{
		return booksFromDataBase;
	}

}
