/**
 * 
 */
package threading;

import java.io.*;
import java.util.*;
/**
 * @author AB, 2018
 *
 * This class opens a file called "cust.txt", and loads the contents
 * into a fileData ArrayList, which is a collection of CustData objects. 
 *
 */
public class DataLoader {
	/*************************  public constants  *************************************************/
	final int CUST_ID=0;
	final int LNAME=1;     
	final int FNAME=2;      
	final int ADDRESS=3;
	final int CITY=4;
	final int STATE=5;
	final int ZIP=6;
	final int EMAIL_CONTACT=7;
	final int DECEASED=8;

	/**************************  public variables  ************************************************/
	public List<CustData> fileData;
	public List<String> stateList;

	/*************************  private variables  ************************************************/
	private Scanner fileIn;

	/*************************************  public methods ********************************************/
	public boolean openAndLoadFile()
	{	      
		fileData = new ArrayList<CustData>();
		stateList = new ArrayList<String>();	

		if (  openFile()  )
		{
			loadFileData();
			closeFile();
		}
		else
		{
			//JOptionPane.showMessageDialog(null, "File could not be opened.");
			return false;
		}
		return true;
	}

	public void closeFile()
	{	
		this.fileIn.close();
	}

	/****************************************************   private methods *******************************************/
	private boolean openFile()
	{	
		boolean success = false;
		try
		{
			//fileIn = new Scanner(new FileReader(fileDir.getPath()  )  );
			fileIn = new Scanner(new FileReader("cust.txt" )  );  
			if (fileIn.hasNextLine())
			{
				success = true;
			}
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e.getMessage());
		}
		return success;
	}

	private void loadFileData()
	{
		String[] dataLineArray; 
		String dataLine;
		int recNum = 0;
		while (fileIn.hasNextLine())
		{
			CustData cdl = new CustData();
			try
			{
				dataLine = fileIn.nextLine();

				dataLineArray = dataLine.split(";");
				for (int i=0; i<dataLineArray.length; i++) {			
					dataLineArray[i] = removeQuotes(dataLineArray[i]);
				}

				// load record into arrays
				cdl.custId = (dataLineArray[CUST_ID].isEmpty()) ? "" : dataLineArray[CUST_ID].trim(); 
				cdl.lname = (dataLineArray[LNAME].isEmpty()) ? "" : dataLineArray[LNAME].trim();
				cdl.fname = (dataLineArray[FNAME].isEmpty()) ? "" : dataLineArray[FNAME].trim();
				cdl.address = (dataLineArray[ADDRESS].isEmpty()) ? "" : dataLineArray[ADDRESS].trim();
				cdl.city = (dataLineArray[CITY].isEmpty()) ? "" : dataLineArray[CITY].trim();
				cdl.state = (dataLineArray[STATE].isEmpty()) ? "" : dataLineArray[STATE].trim().toUpperCase();
				cdl.zip = (dataLineArray[ZIP].isEmpty()) ? "" : dataLineArray[ZIP].trim();
				cdl.emailContact = dataLineArray[EMAIL_CONTACT].equals("1") ? true : false;
				cdl.deceased = dataLineArray[DECEASED].equals("1") ? true : false;
				//We're ignoring comments
				fileData.add(recNum, cdl);
				recNum++;
				if (isUniqueState(cdl.state) && cdl.state.length()==2)
					stateList.add( (dataLineArray[STATE].isEmpty()) ? "" : dataLineArray[STATE].trim() );
				//Collections.sort(stateList);

			}  // end try 
			catch (Exception e)
			{
				dataLine = "***Line Error***";
			}
		}// end while fileIn.hasNext...
	}

	private boolean isUniqueState(String newState)
	{
		boolean unique = true;

		// check for duplicate descriptions in file contents array
		for (int i=0; i< stateList.size(); i++)
		{
			if ( stateList.get(i).equalsIgnoreCase(newState) )
				unique = false ;
		}

		return unique;
	}

	private String removeQuotes(String str)
	{
		String newStr=str;

		try
		{
			if (str.charAt(0) == '\"' && str.charAt(str.length()-1) == '\"') 
				newStr = str.substring(1, str.length()-1);
		}
		catch (Exception e)
		{
			newStr = "";
		}
		return newStr;
	}

	public void displayCustAddr()
	{
		int max = 0;
		if (fileData.size() >= 20)
			max = 20;
		else
			max = fileData.size();

		System.out.println("\n\n First 20 Customers");
		for (int i=0; i < max; i++)
		{
			System.out.println(fileData.get(i).lname + ", " + fileData.get(i).fname + 
					fileData.get(i).address + "; " + fileData.get(i).city +
					", " + fileData.get(i).state + "  " + fileData.get(i).zip);
		}
	}

	public void displayStateList()
	{
		int max = 0;
		if (stateList.size() >= 20)
			max = 20;
		else
			max = stateList.size();

		System.out.println("\n\n First 20 States");
		for (int i=0; i < max; i++)
		{
			System.out.println(stateList.get(i));
		}
	}
	/*	
	public static void main(String[] args)
	{
		DataLoader dl = new DataLoader();

		dl.openAndLoadFile();
		System.out.println("\n\nLoad #1 FD before selection sort");
		dl.displayCustAddr();
		MySorts.selectionSortCustData(dl.fileData);
		System.out.println("\n\nLoad #1 FD after sel sort");
		dl.displayCustAddr();

		DataLoader dl2 = new DataLoader();  
		dl2.openAndLoadFile();  
		System.out.println("\n\nLoad #2 FD before ins sort");
		dl2.displayCustAddr();
		MySorts.insertionSortCustData(dl2.fileData);
		System.out.println("\n\nLoad #2 FD after ins sort");
		dl2.displayCustAddr();

		DataLoader dl3 = new DataLoader();  
		dl3.openAndLoadFile();  
		System.out.println("\n\nLoad #3 SL before selection sort");
		dl3.displayStateList();
		MySorts.selectionSortList(dl3.stateList);
		System.out.println("\n\nLoad #3 SL after sel sort");
		dl3.displayStateList();

		DataLoader dl4 = new DataLoader();  
		dl4.openAndLoadFile();  //reload, so we start with unsorted data again
		System.out.println("\n\nLoad #4 SL before ins sort");
		dl4.displayStateList();
		MySorts.insertionSortList(dl4.stateList);
		System.out.println("\n\nLoad #4 SL after ins sort");
		dl4.displayStateList();

		System.out.println("Records Processed:  " + dl.fileData.size() );

	} // end main
	 */
}  // end class DataLoader

