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
	public String custId;
	public String lname;     
	public String fname;      
	public String address;
	public String city;
	public String state;
	public String zip;
	public DateTime	birthDate;
	public String phone;
	public String email;
	public Boolean mailContact;
	public Boolean emailContact;
	public Boolean deceased;
	public String comments;
	public int age;
	
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
