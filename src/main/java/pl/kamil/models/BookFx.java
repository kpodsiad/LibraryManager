package pl.kamil.models;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class BookFx
{
	private SimpleLongProperty id = new SimpleLongProperty();
	private SimpleStringProperty name = new SimpleStringProperty();
	private SimpleStringProperty author = new SimpleStringProperty();
	private SimpleStringProperty publisher = new SimpleStringProperty();
	private SimpleStringProperty releaseDate = new SimpleStringProperty();
	private SimpleBooleanProperty available = new SimpleBooleanProperty();

	public BookFx()
	{
	}

	public long getId()
	{
		return id.get();
	}

	public SimpleLongProperty idProperty()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id.set(id);
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

	public boolean isAvailable()
	{
		return available.get();
	}

	public SimpleBooleanProperty availableProperty()
	{
		return available;
	}

	public void setAvailable(boolean available)
	{
		this.available.set(available);
	}

	public String isAvailableInString()
	{
		return available.get() ? "Yes" : "No";
	}
}
