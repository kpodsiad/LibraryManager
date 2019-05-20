package pl.kamil.database.mapping.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "LOANS")
public class Loan implements MappingModel
{

	@DatabaseField(generatedId = true)
	private Long id;

	@DatabaseField(columnName = "BOOK", foreign = true, foreignAutoRefresh = true)
	private Book book;

	@DatabaseField(columnName = "MEMBER", foreign = true, foreignAutoRefresh = true)
	private Member member;


	public Loan()
	{
	}

	public Loan(Book book, Member member)
	{
		this.book = book;
		this.member = member;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Book getBook()
	{
		return book;
	}

	public void setBook(Book book)
	{
		this.book = book;
	}

	public Member getMember()
	{
		return member;
	}

	public void setMember(Member member)
	{
		this.member = member;
	}
}
