package finalProject;

import org.joda.time.DateTime;

/*************************************************************
 * CustData.java
 * AB
 * 2018
 *
 * This class stores a record from the cust.txt data file.
 *************************************************************/

public class CustData
{
	private String custId;
	private String lname;     
	private String fname;      
	private String address;
	private String city;
	private String state;
	private String zip;
	private DateTime birthDate;
	private String phone;
	private String email;
	private Boolean mailContact;
	private Boolean emailContact;
	private Boolean deceased;
	private String comments;
	private int age;
	
	//********************************GETTERS****************************
	
	public String getCustId()
	{
		return custId;
	}
	
	public String getLastName()
	{
		return lname;
	}
	
	public String getFirstName()
	{
		return fname;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public String getState()
	{
		return state;
	}
	
	public String getZip()
	{
		return zip;
	}
	
	public DateTime getBirthDate()
	{
		return birthDate;
	}
	
	public String getPhoneNumber()
	{
		return phone;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public boolean canMailContact()
	{
		return mailContact;
	}
	
	public boolean canEmailContact()
	{
		return emailContact;
	}
	
	public boolean isDeceased()
	{
		return deceased;
	}
	
	public String getComments()
	{
		return comments;
	}
	
	public int getAge()
	{
		return age;
	}
	
	//***********************SETTERS*****************************
	
	public void setCustomerId(String newId)
	{
		if(newId != null && !newId.isEmpty())
		this.custId = newId;
	}
	
	public void setLastName(String lastName)
	{
		if(lastName != null && !lastName.isEmpty())
		{
			this.lname = lastName;
		}
	}
	
	public void setFirstName(String firstName)
	{
		if(firstName != null && !firstName.isEmpty())
		{
			this.fname = firstName;
		}
	}
	
	public void setAddress(String addy)
	{
		if(addy != null && !addy.isEmpty())
		{
			this.address = addy;
		}
	}
	
	public void setCity(String newCity)
	{
		if(newCity != null && !newCity.isEmpty())
		{
			this.city = newCity;
		}
	}
	
	public void setState(String newState)
	{
		if(newState != null && !newState.isEmpty())
		{
			this.state = newState;
		}
	}
	
	public void setZip(String newZip)
	{
		if(newZip != null && !newZip.isEmpty())
		{
			this.zip = newZip;
		}
	}
	
	public void setBirthDate(DateTime newBDate)
	{
		if(newBDate != null)
		{
			this.birthDate = newBDate;
		}
	}
	
	public void setPhoneNumber(String newNumber)
	{
		if(newNumber != null && !newNumber.isEmpty())
		{
			this.phone = newNumber;
		}
	}
	
	public void setEmail(String newEmail)
	{
		if(newEmail != null && !newEmail.isEmpty())
		{
			this.email = newEmail;
		}
	}
	
	public void setMailContact(boolean canMail)
	{
		this.mailContact = canMail;
	}
	
	public void setEmailContact(boolean canEmail)
	{
		this.emailContact = canEmail;
	}
	
	public void setDeceased(boolean isDead)
	{
		this.deceased = isDead;
	}
	
	public void setComments(String newComments)
	{
		if(newComments != null)
		{
			this.comments = newComments;
		}
	}
	
	public void setAge(int newAge)
	{
		if(newAge > -1 && newAge < 130)
		{
			this.age = newAge;
		}
	}
	
/***********************************************************
	 * Output the customer's last name, first name, address,
	 * city, state, and zip.
	 * 
	 */
	@Override
	public String toString()
	{
		return this.lname + "," + this.fname + "," + 
				this.address + "," +
				this.city + "," + this.state + "," + this.zip;
	}
} // end class CustData
