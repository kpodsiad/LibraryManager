package pl.kamil.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "MEMBERS")
public class Member
{
	@DatabaseField(generatedId = true)
	private Long id;

	@DatabaseField(columnName = "FIRST_NAME", canBeNull = false)
	private String firstName;

	@DatabaseField(columnName = "LAST_NAME", canBeNull = false)
	private String lastName;

	@DatabaseField(columnName = "EMAIL", canBeNull = false)
	private String email;

	@DatabaseField(columnName = "PHONE", canBeNull = false)
	private String phoneNumber;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString()
	{
		return "Member{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				'}';
	}
}
