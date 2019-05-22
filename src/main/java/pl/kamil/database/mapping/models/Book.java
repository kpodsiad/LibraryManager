package pl.kamil.database.mapping.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "BOOKS")
public class Book implements MappingModel
{
	@DatabaseField(generatedId = true)
	private Long id;

	@DatabaseField(columnName = "AUTHOR", canBeNull = false)
	private String author;

	@DatabaseField(columnName = "NAME", canBeNull = false)
	private String bookName;

	@DatabaseField(columnName = "PUBLISHER", canBeNull = false)
	private String publisher;

	@DatabaseField(columnName = "RELEASED_DATE", canBeNull = false)
	private String releasedDate;

	@DatabaseField(columnName = "AVAILABLE", canBeNull = false)
	private boolean available = true;

	public Book()
	{
	}

	public Book(String author, String bookName, String publisher, String releasedDate, boolean available)
	{
		this.author = author;
		this.bookName = bookName;
		this.publisher = publisher;
		this.releasedDate = releasedDate;
		this.available = available;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public String getBookName()
	{
		return bookName;
	}

	public void setBookName(String bookName)
	{
		this.bookName = bookName;
	}

	public String getPublisher()
	{
		return publisher;
	}

	public void setPublisher(String publisher)
	{
		this.publisher = publisher;
	}

	public String getReleasedDate()
	{
		return releasedDate;
	}

	public void setReleasedDate(String releasedDate)
	{
		this.releasedDate = releasedDate;
	}

	public boolean isAvailable()
	{
		return available;
	}

	public void setAvailable(boolean available)
	{
		this.available = available;
	}
}
