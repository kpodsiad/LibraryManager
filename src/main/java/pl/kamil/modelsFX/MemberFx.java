package pl.kamil.modelsFX;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import pl.kamil.database.mapping.models.Member;

public class MemberFx
{
	private SimpleLongProperty id = new SimpleLongProperty();
	private SimpleStringProperty firstName = new SimpleStringProperty();
	private SimpleStringProperty lastName = new SimpleStringProperty();
	private SimpleStringProperty email = new SimpleStringProperty();
	private SimpleStringProperty phoneNumber = new SimpleStringProperty();

	public MemberFx()
	{
	}

	public MemberFx(String firstName, String lastName, String email, String phoneNumber)
	{
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setPhoneNumber(phoneNumber);
	}

	public MemberFx(Member member)
	{
		setId(member.getId());
		setFirstName(member.getFirstName());
		setLastName(member.getLastName());
		setEmail(member.getEmail());
		setPhoneNumber(member.getPhoneNumber());
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

	public String getFirstName()
	{
		return firstName.get();
	}

	public SimpleStringProperty firstNameProperty()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName.set(firstName);
	}

	public String getLastName()
	{
		return lastName.get();
	}

	public SimpleStringProperty lastNameProperty()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName.set(lastName);
	}

	public String getEmail()
	{
		return email.get();
	}

	public SimpleStringProperty emailProperty()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email.set(email);
	}

	public String getPhoneNumber()
	{
		return phoneNumber.get();
	}

	public SimpleStringProperty phoneNumberProperty()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber.set(phoneNumber);
	}
}
