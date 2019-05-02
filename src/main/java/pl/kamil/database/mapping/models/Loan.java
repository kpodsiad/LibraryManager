package pl.kamil.database.mapping.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "LOANS")
public class Loan implements BaseModel
{

	@DatabaseField(generatedId = true)
	private Long id;

	@DatabaseField(columnName = "BOOK_ID", canBeNull = false)
	private Long bookId;

	@DatabaseField(columnName = "MEMBER_ID", canBeNull = false)
	private Long memberId;

	public Loan()
	{
	}

	public Loan(Long bookId, Long memberId)
	{
		this.bookId = bookId;
		this.memberId = memberId;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getBookId()
	{
		return bookId;
	}

	public void setBookId(Long bookId)
	{
		this.bookId = bookId;
	}

	public Long getMemberId()
	{
		return memberId;
	}

	public void setMemberId(Long memberId)
	{
		this.memberId = memberId;
	}
}
