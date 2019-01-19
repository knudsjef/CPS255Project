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
	public String name;
	public int population = 0;
	public int PopInAgeInterval1 = 0;	//number of people from 18-35
	public int PopInAgeInterval2 = 0;	//number of people from 36-60
	public int PopInAgeInterval3 = 0;	//number of people above 61(including 61)
	
	public StateInfo(String name)
	{
		this.name = name;
	}
	
	public void updatePop()
	{
		this.population += 1;
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
			percent = this.PopInAgeInterval1 / totalCustomer * 100;
		else if (ageIntervalPop == 2) //people from 36-60
			percent = this.PopInAgeInterval2 / totalCustomer * 100;
		else if (ageIntervalPop == 3) //people above 61
			percent = this.PopInAgeInterval3 / totalCustomer * 100;
		
		return percent;
	}
	
	/*************************************************
	 * output this state's name, population and 
	 * number of people within 3 age groups seperately
	 */
	@Override
	public String toString()
	{
		return name + "," + population + "," + PopInAgeInterval1 +
				"," + PopInAgeInterval2 + "," + PopInAgeInterval3;
	}
}
