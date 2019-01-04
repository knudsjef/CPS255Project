/**
 * 
 */
package threading;

import java.io.*;
import java.util.*;

import org.joda.time.DateTime;

import sortExample.MySorts;

/**
 * @author AB
 *
 * 
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
	public String fileName;
	public String filePath;
	public String fileStatus;
	public String encodingType;
	public int numRecs;
	public List<CustData> fileData = new ArrayList<CustData>();
	public List<String> stateList;

	/*************************  private variables  ************************************************/

	private Scanner fileIn;

	/************************************** constructor  ******************************************/
	public DataLoader()
	{
		this.stateList = new ArrayList<String>();	
		this.numRecs = 0;	
	}


	/*************************************  public methods ********************************************/
	public boolean openAndLoadFile()
	{	      
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


	/*
	public static void main(String[] args)
	{
		DataLoader dl = new DataLoader();
		dl.openAndLoadFile();
		MySorts.selectionSort(dl.stateList);
		MySorts.insertionSort(dl.stateList);
		System.out.println("Records Processed:  " + dl.fileData.size() );

	} // end main
	 */
	public void display20()
	{
		int max = 0;
		if (fileData.size() >= 20)
			max = 20;
		else
			max = fileData.size();

		System.out.println("\n\n First 20 Customers");
		for (int i=0; i < max; i++)
		{
			System.out.println(fileData.get(i).lname + ", " + fileData.get(i).fname + ";     " + fileData.get(i).state);
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

	public static void main(String[] args)
	{
		DataLoader dl = new DataLoader();
		dl.openAndLoadFile();
		System.out.println("\n\nLoad #1 before selection sort");
		dl.displayStateList();
		MySorts.selectionSort(dl.fileData);
		System.out.println("\n\nLoad #1 after sel sort");
		dl.displayStateList();
		dl.openAndLoadFile();  //reload, so we start with unsorted data again
		System.out.println("\n\nLoad #2 before ins sort");
		dl.displayStateList();
		MySorts.insertionSort(dl.stateList);
		System.out.println("\n\nLoad #2 after ins sort");
		dl.displayStateList();
		System.out.println("Records Processed:  " + dl.fileData.size() );

	} // end main



}

