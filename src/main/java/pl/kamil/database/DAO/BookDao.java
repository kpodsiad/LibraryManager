package pl.kamil.database.DAO;

import pl.kamil.database.mapping.models.Book;

public class BookDao extends CommonDao<Book, Long>
{
	public BookDao()
	{
		super(Book.class);
	}
}
