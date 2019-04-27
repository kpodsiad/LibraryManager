package pl.kamil.models;

import javafx.beans.property.SimpleStringProperty;

public class BookFx
{
	private SimpleStringProperty name = new SimpleStringProperty();
	private SimpleStringProperty author = new SimpleStringProperty();
	private SimpleStringProperty publisher = new SimpleStringProperty();
	private SimpleStringProperty releaseDate = new SimpleStringProperty();

	public BookFx()
	{
	}

	public BookFx(String name, String author, String publisher, String releaseDate)
	{
		setName(name);
		setAuthor(author);
		setPublisher(publisher);
		setReleaseDate(releaseDate);
	}

	public BookFx(Book book)
	{
		setName(book.getBookName());
		setAuthor(book.getAuthor());
		setPublisher(book.getPublisher());
		setReleaseDate(book.getReleasedDate());
	}


	public String getName()
	{
		return name.get();
	}

	public SimpleStringProperty nameProperty()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name.set(name);
	}

	public String getAuthor()
	{
		return author.get();
	}

	public SimpleStringProperty authorProperty()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author.set(author);
	}

	public String getPublisher()
	{
		return publisher.get();
	}

	public SimpleStringProperty publisherProperty()
	{
		return publisher;
	}

	public void setPublisher(String publisher)
	{
		this.publisher.set(publisher);
	}

	public String getReleaseDate()
	{
		return releaseDate.get();
	}

	public SimpleStringProperty releaseDateProperty()
	{
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate)
	{
		this.releaseDate.set(releaseDate);
	}
}
