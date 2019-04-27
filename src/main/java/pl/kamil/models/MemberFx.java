package pl.kamil.models;

import javafx.beans.property.SimpleStringProperty;

public class MemberFx
{
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
		setFirstName(member.getFirstName());
		setLastName(member.getLastName());
		setEmail(member.getEmail());
		setPhoneNumber(member.getPhoneNumber());
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
