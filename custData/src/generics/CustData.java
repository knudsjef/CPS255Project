package generics;

import org.joda.time.DateTime;
import java.util.Comparator;

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
	
	/**************************************************************************
	 *  @param CustData object
	 *  
	 *  This comparator is used by the generic's Collections.sort statement.
	 *  If we want to sort objects, we have to provide the rules by which they
	 *  should be sorted. 
	 *  
	 *  Note that this will not currently be used in the generic standardSort.
	 *  We would need to do some fairly complicated mapping to make that work, 
	 *  and we don't have the time to get into that can of worms.
	 */
	public static Comparator<CustData> cdComparator = new Comparator<CustData>() 
	{
		public int compare(CustData cd1, CustData cd2) 
		{
			String CD1 = cd1.state + cd1.zip; // Sort by state, then zip
			String CD2 = cd2.state + cd2.zip; // Sort by state, then zip

			//ascending order
			return CD1.compareTo(CD2);

			//descending order
			//return M2.compareTo(M1);
		}};

	/*******************************************************************************************
	 * @param
	 * 
	 * This toString method is vital to the generic sort's function. If we comment this out and
	 *   run the code, you'll see that the output for cust address data is simply a list of the
	 *   object reference (name and memory location).  This overwrites that, so we can see the
	 *   data in testing/output.
	 */
	public String toString()
	{
		return this.lname + ", " + this.fname + "; " + 
				this.address + "; " +
				this.city + ", " + this.state + "  " + this.zip;
	}
} // end class CustData
