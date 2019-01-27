/**
 * 
 */
package finalProject;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
/**
 * @author AB, 2018
 *
 * This class opens a file called "cust.txt", and loads the contents
 * into a fileData ArrayList, which is a collection of CustData objects. 
 *
 */
public class DataLoader {
	/*************************  public constants  *************************************************/
	final int CUST_ID = 0;
    final int LNAME = 1;
    final int FNAME = 2;
    final int ADDRESS = 3;
    final int CITY = 4;
    final int STATE = 5;
    final int ZIP = 6;
    final int BIRTHDATE = 7;
    final int PHONE = 8;
    final int EMAIL = 9;
    final int MAIL_CONTENT = 10;
    final int EMAIL_CONTACT = 11;
    final int DECEASED = 12;
    
    /**************************  formats  *********************************************************/
    DateTimeFormatter TimeFormat = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss");
    DecimalFormat df = new DecimalFormat("0.00");

	/**************************  public variables  ************************************************/
	public List<CustData> fileData;
	public List<String> stateList;
	public List<StateInfo> stateInfoList;
	public List<CustData> mailingList;

	/*************************  private variables  ************************************************/
	private Scanner fileIn;

	/*************************************  public methods ********************************************/
	public boolean openAndLoadFile()
	{	      
		fileData = new ArrayList<CustData>();
		stateList = new ArrayList<String>();	
		stateInfoList = new ArrayList<StateInfo>();
		mailingList = new ArrayList<CustData>();

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
		String birthDate;
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
				cdl.setCustomerId((dataLineArray[CUST_ID].isEmpty()) ? "" : dataLineArray[CUST_ID].trim()); 
				cdl.setLastName((dataLineArray[LNAME].isEmpty()) ? "" : dataLineArray[LNAME].trim());
				cdl.setFirstName((dataLineArray[FNAME].isEmpty()) ? "" : dataLineArray[FNAME].trim());
				cdl.setAddress((dataLineArray[ADDRESS].isEmpty()) ? "" : dataLineArray[ADDRESS].trim());
				cdl.setCity((dataLineArray[CITY].isEmpty()) ? "" : dataLineArray[CITY].trim());
				cdl.setState((dataLineArray[STATE].isEmpty()) ? "" : dataLineArray[STATE].trim().toUpperCase());
				cdl.setZip((dataLineArray[ZIP].isEmpty()) ? "" : dataLineArray[ZIP].trim());
				cdl.setMailContact(dataLineArray[MAIL_CONTENT].equals("1") ? true : false);
				cdl.setEmailContact(dataLineArray[EMAIL_CONTACT].equals("1") ? true : false);
				cdl.setDeceased(dataLineArray[DECEASED].equals("1") ? true : false);
				addMailingList(cdl);
//				birthDate = (dataLineArray[BIRTHDATE].isEmpty())? new DateTime().toString(TimeFormat) : dataLineArray[BIRTHDATE].trim();
				birthDate = (dataLineArray[BIRTHDATE].isEmpty())? "" : dataLineArray[BIRTHDATE].trim(); //those without BD won't be counted
				cdl.setBirthDate(DateTime.parse(birthDate, TimeFormat));
				cdl.setAge(calculateAge(cdl.getBirthDate()));
				//We're ignoring comments
				fileData.add(recNum, cdl);
				recNum++;
				if (isUniqueState(cdl.getState()) && cdl.getState().length()==2)
					stateList.add( (dataLineArray[STATE].isEmpty()) ? "" : dataLineArray[STATE].trim() );
				//Collections.sort(stateList);
				updateStatePopulation(cdl.getState(), cdl.getAge());
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

	/****************************************************************
	 * This method updates the input state's population, both total
	 * and specified. It first checks with stateInfoList to see 
	 * if the state already exists. If it exists, then it updates 
	 * existing state's population. If not, it creates a new state 
	 * object and increase the population.
	 * 
	 * @param State input state
	 * @param age person's age
	 * @author Kaiming
	 */
	private void updateStatePopulation(String State, int age)
	{			
		boolean unique=true;
		int stateIndex=0;
		// check for duplicate descriptions in state array
		for (int i=0; i<stateInfoList.size(); i++)
		{
			if (stateInfoList.get(i).getName().equalsIgnoreCase(State)) {
				unique=false;
				stateIndex = i;
			}
		}
		
		// create a new state object
		if (unique) {
			StateInfo state = new StateInfo(State);
			state.updatePop(); //update state's total population
			updateStateSpecificPop(state, age); //update state's age group population
			stateInfoList.add(state);
		}
		// update existing state's population
		else {
			stateInfoList.get(stateIndex).updatePop(); //update state's total population
			updateStateSpecificPop(stateInfoList.get(stateIndex), age); //update state's age group population
		}
	}
	
	/***************************************************************
	 * This method updates number of people of a state's age group
	 * 
	 * @param state current state
	 * @param age the person's age
	 */
	private void updateStateSpecificPop(StateInfo state, int age)
	{
		if (age >= 18 && age <=35) // between 18 and 35
			state.setPopInAgeInterval1(state.getPopInAgeInterval1() + 1);
		else if (age >= 36 && age <= 60) // between 36 and 60
			state.setPopInAgeInterval2(state.getPopInAgeInterval2() + 1);
		else if (age >= 61) // above 61(including 61)
			state.setPopInAgeInterval3(state.getPopInAgeInterval3() + 1);
	}
	
	private int calculateAge(DateTime birthDay)
    {
        int age = 0;

        /****** it will be much easier to calculate the age with Date rather
         than DateTime so that i transfer the parameter to Date tyoe ******/
        Date date = birthDay.toDate();

        /****** get the current date ******/
        Calendar cal = Calendar.getInstance();
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        /****** set the calendar date to a given date then get date info ******/
        cal.setTime(date);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        /****** calculate the age ******/
        age = yearNow - yearBirth;

        if (monthNow <= monthBirth)
        {
            /****** current month is same as the birth month but current day is less the birth day
             which means there are still several days before the birthday******/
            if (monthNow == monthBirth)
            {
                if (dayOfMonthNow < dayOfMonthBirth)
                {
                    age--;
                }
            }

            /******* current month is less than birth month ******/
            else
            {
                age--;
            }
        }
        return age;
    }

	/*********************************************************
	 * This method adds a customer who is non-deceased
	 * and wants to receive a mail to a List
	 * 
	 * @param custData customer
	 */
	private void addMailingList(CustData custData)
	{
		if (!(custData.isDeceased()) && custData.canEmailContact())
			mailingList.add(custData);
	}
	
	/**************************************************
	 * This method prints each state's age group 
	 * percentage among all customers to a txt file.
	 * 
	 */
	private void printOutputFile1()
	{
		PrintWriter output;
		
		double p1;
		double p2;
		double p3;
		double totalCust = fileData.size();
		
		try 
		{
			output = new PrintWriter(new FileWriter("src/Output File1.txt")); //instantiate printwriter object with specified path
			output.println("All states' percentage"); //write title
			for (int i=0; i < stateInfoList.size(); i++)
			{
				p1 = stateInfoList.get(i).caculPercent(1, totalCust); // % of people btw 18-35 in the state in all cust
				p2 = stateInfoList.get(i).caculPercent(2, totalCust); // % of people btw 36-60 in the state in all cust
				p3 = stateInfoList.get(i).caculPercent(3, totalCust); // % of people above 61 in the state in all cust
				
				output.println(stateInfoList.get(i).getName() + "," + 
							df.format(p1) + "," + df.format(p2) + "," + 
							df.format(p3)); //write info
			}
			output.close();
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}		
	}
	
	/*******************************************************
	 * This method prints every customer who's non-deceased,
	 * wants to receive a mail to a txt file.
	 * 
	 */
	private void printOutputFile2()
	{
		PrintWriter output;
		
		try
		{
			output = new PrintWriter(new FileWriter("src/Output File2.txt")); //instantiate printwriter object with specified path
			for (int i=0; i < mailingList.size(); i++)
			{
				output.println(mailingList.get(i).toString());
			}
			output.close();
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	/******************************************** display methods *****************************************************/
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
			System.out.println(fileData.get(i).toString());
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
	
	/********************************************
	 * This method displays each state's total
	 * population, plus number of people in
	 * each of the three age group.
	 * 
	 */
	public void displayStateInfoList() 
	{
		System.out.println("\n\nAll states' population");
		for (int i=0; i < stateInfoList.size(); i++)
		{
			System.out.println(stateInfoList.get(i).toString());
		}
	}
	
	/*********************************************************
	 * This method displays each state's age group percentage/
	 * 
	 */
	public void displayPercentage()
	{
		double p1;
		double p2;
		double p3;
		double totalCust = fileData.size();
		System.out.println("\n\nAll states' percentage");
		for (int i=0; i < stateInfoList.size(); i++)
		{
			p1 = stateInfoList.get(i).caculPercent(1, totalCust); // % of people btw 18-35 in the state in all cust
			p2 = stateInfoList.get(i).caculPercent(2, totalCust); // % of people btw 36-60 in the state in all cust
			p3 = stateInfoList.get(i).caculPercent(3, totalCust); // % of people above 61 in the state in all cust
			System.out.println(stateInfoList.get(i).getName() + "," + 
						df.format(p1) + "," + df.format(p2) + "," + 
						df.format(p3));
		}
	}

	public static void main(String[] args)
	{
		DataLoader dl = new DataLoader();
		
		dl.openAndLoadFile();
//		System.out.println("\n\nLoad #1 StateInfo before quick sort");
//		dl.displayStateInfoList();
		MySorts.quickSortStateInfo(dl.stateInfoList, 0, dl.stateInfoList.size()-1);
		MySorts.quickSortCustData(dl.mailingList, 0, dl.mailingList.size()-1);
//		System.out.println("\n\nLoad #1 StateInfo after quick sort");
		dl.displayStateInfoList();
		dl.displayPercentage();
		dl.printOutputFile1();
		dl.printOutputFile2();

//		dl.openAndLoadFile();
//		System.out.println("\n\nLoad #1 FD before selection sort");
//		dl.displayCustAddr();
//		MySorts.selectionSortCustData(dl.fileData);
//		System.out.println("\n\nLoad #1 FD after sel sort");
//		dl.displayCustAddr();
//
//		DataLoader dl2 = new DataLoader();  
//		dl2.openAndLoadFile();  
//		System.out.println("\n\nLoad #2 FD before ins sort");
//		dl2.displayCustAddr();
//		MySorts.insertionSortCustData(dl2.fileData);
//		System.out.println("\n\nLoad #2 FD after ins sort");
//		dl2.displayCustAddr();
//
//		DataLoader dl3 = new DataLoader();  
//		dl3.openAndLoadFile();  
//		System.out.println("\n\nLoad #3 SL before selection sort");
//		dl3.displayStateList();
//		MySorts.selectionSortList(dl3.stateList);
//		System.out.println("\n\nLoad #3 SL after sel sort");
//		dl3.displayStateList();
//
//		DataLoader dl4 = new DataLoader();  
//		dl4.openAndLoadFile();  //reload, so we start with unsorted data again
//		System.out.println("\n\nLoad #4 SL before ins sort");
//		dl4.displayStateList();
//		MySorts.insertionSortList(dl4.stateList);
//		System.out.println("\n\nLoad #4 SL after ins sort");
//		dl4.displayStateList();
//
//		System.out.println("Records Processed:  " + dl.fileData.size() );

	} // end main
	
}  // end class DataLoader

