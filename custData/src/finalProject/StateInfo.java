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
	
	public StateInfo(String name)
	{
		this.name = name;
	}
	
	public void updatePop()
	{
		this.population += 1;
	}
	
	/*************************************************
	 * output this state's name and population
	 */
	@Override
	public String toString()
	{
		return name + "," + population;
	}
}
