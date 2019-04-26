package models;

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

	@DatabaseField(columnName = "DATE", canBeNull = false)
	private String publishedDate;

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

	public String getPublishedDate()
	{
		return publishedDate;
	}

	public void setPublishedDate(String publishedDate)
	{
		this.publishedDate = publishedDate;
	}
}
