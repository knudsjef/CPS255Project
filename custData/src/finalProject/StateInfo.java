package finalProject;

/**********************************************************
 * StateInfo.java
 * Kaiming Xia
 * 01/15/2019
 * 
 * This class stores a state's population information.
 *********************************************************/

public class StateInfo 
{
	
	private String name;
	private int population = 0;
	private int popInAgeInterval1 = 0;	//number of people from 18-35
	private int popInAgeInterval2 = 0;	//number of people from 36-60
	private int popInAgeInterval3 = 0;	//number of people above 61(including 61)
	
	//*************************CONSTRUCTOR*****************************
	public StateInfo(String name)
	{
		this.name = name;
	}
	
	
	//***********************GETTERS***********************************
	public String getName()
	{
		return name;
	}
	
	public int getPopulation()
	{
		return population;
	}
	
	public int getPopInAgeInterval1()
	{
		return popInAgeInterval1;
	}
	
	public int getPopInAgeInterval2()
	{
		return popInAgeInterval2;
	}
	
	public int getPopInAgeInterval3()
	{
		return popInAgeInterval3;
	}
	
	
	//******************************SETTERS*****************************************
	public void updatePop()
	{
		this.population += 1;
	}
	
	public void setPopInAgeInterval1(int toSet)
	{
		if(toSet >= 0)
		{
			popInAgeInterval1 = toSet;
		}
	}
	
	public void setPopInAgeInterval2(int toSet)
	{
		if(toSet >= 0)
		{
			popInAgeInterval2 = toSet;
		}
	}
	
	public void setPopInAgeInterval3(int toSet)
	{
		if(toSet >= 0)
		{
			popInAgeInterval3 = toSet;
		}
	}
	
	/*******************************************************************
	 * This method calculates percentage of the state's one of the age 
	 * group's people among the total customers, and return the value.
	 * 
	 * @param ageIntervalPop index of age group 
	 * 							1: 18-35
	 * 							2: 36-60
	 * 							3: 61+
	 * @param totalCustomer number of total customers
	 * @return percentage of the state's age group
	 */
	public double caculPercent(int ageIntervalPop, double totalCustomer)
	{
		double percent = 0;
		
		if (ageIntervalPop == 1) //people from 18-35
			percent = this.popInAgeInterval1 / totalCustomer * 100;
		else if (ageIntervalPop == 2) //people from 36-60
			percent = this.popInAgeInterval2 / totalCustomer * 100;
		else if (ageIntervalPop == 3) //people above 61
			percent = this.popInAgeInterval3 / totalCustomer * 100;
		
		return percent;
	}
	
	/*************************************************
	 * output this state's name, population and 
	 * number of people within 3 age groups seperately
	 */
	@Override
	public String toString()
	{
		return name + "," + population + "," + popInAgeInterval1 +
				"," + popInAgeInterval2 + "," + popInAgeInterval3;
	}
}
