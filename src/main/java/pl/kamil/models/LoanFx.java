package pl.kamil.models;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class LoanFx
{
	private SimpleLongProperty id = new SimpleLongProperty();
	private SimpleStringProperty title = new SimpleStringProperty();
	private SimpleLongProperty bookId = new SimpleLongProperty();
	private SimpleLongProperty memberId = new SimpleLongProperty();


	public LoanFx()
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

	public long getBookId()
	{
		return bookId.get();
	}

	public SimpleLongProperty bookIdProperty()
	{
		return bookId;
	}

	public void setBookId(long bookId)
	{
		this.bookId.set(bookId);
	}

	public long getMemberId()
	{
		return memberId.get();
	}

	public SimpleLongProperty memberIdProperty()
	{
		return memberId;
	}

	public void setMemberId(long memberId)
	{
		this.memberId.set(memberId);
	}

	public String getTitle()
	{
		return title.get();
	}

	public SimpleStringProperty titleProperty()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title.set(title);
	}
}
