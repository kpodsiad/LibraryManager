package pl.kamil.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "BOOKS")
public class Book
{
	public Book()
	{
	}

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

	@Override
	public String toString()
	{
		return "Book{" +
				"id=" + id +
				", author='" + author + '\'' +
				", bookName='" + bookName + '\'' +
				", publisher='" + publisher + '\'' +
				", releasedDate='" + releasedDate + '\'' +
				'}';
	}
}
