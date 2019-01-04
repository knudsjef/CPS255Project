/**
 * 
 */
package threading;

import java.util.*;

/*************************************************************
 * MySorts.java
 * AB
 * 2018
 *
 * This class contains a method to sort a (one-column) list for each
 *  of the following sort types:  bubble, insertion, selection,
 *  merge, and quick.  This will work well for sorting your state list.
 *  
 * It is currently incomplete, with only the insertion and
 *  selection sort methods complete. Upon completion, this can
 *  be used to assess the performance of each sort on the 
 *  cust.txt file.  These sorts can also be used to sort your
 *  file output data for the state/age and cust address output 
 *  file data.  
 *  
 *  Additionally, it includes a method with the first-level sort
 *   for the custData arraylist completed. I completed the insertion
 *   selection sort to give you a template. You will need to modify
 *   this to use the most efficient sort type, and to sort by state, 
 *   then zip.
 *************************************************************/
public class MySorts { 


	public static boolean bubbleSortList(ArrayList<String> al)
	{
		//copy, paste and modify!
		return true;
	}

	/*************************************************************************
	 * 
	 * @param al  List to be sorted
	 * @return true upon successful completion
	 * 
	 * This method provides a selection sort on the list data.
	 */
	public static boolean selectionSortList(List<String> al)
	{         
		for (int i = 0; i < al.size() - 1; i++)
		{
			int index = i;
			for (int j = i + 1; j < al.size(); j++)
				if (al.get(j).compareToIgnoreCase( al.get(index)) < 0 ) 
					index = j;

			String smallerValue = al.get(index);  
			al.set(index, al.get(i));
			al.set(i, smallerValue);
		}
		return true;
	}

	/*************************************************************************
	 * 
	 * @param fileData  List of CustData objects to be sorted
	 * @return true upon successful completion
	 * 
	 * This method provides a first-level selection sort on Customer data
	 *  by state.  Upon completion, array list will be sorted by state, then 
	 *  by zip.
	 */
	public static boolean selectionSortCustData(List<CustData> fileData)
	{         
		for (int i = 0; i < fileData.size() - 1; i++)
		{
			int index = i;
			for (int j = i + 1; j < fileData.size(); j++)
				if (fileData.get(j).state.compareToIgnoreCase( fileData.get(index).state) < 0 ) 
					index = j;

			CustData smallerValue = fileData.get(index); 
			fileData.set(index, fileData.get(i));
			fileData.set(i, smallerValue);
		}
		return true;
	}

	/*************************************************************************
	 * 
	 * @param al  List to be sorted
	 * @return true upon successful completion
	 * 
	 * This method provides a insertion sort on the list data.
	 */
	public static boolean insertionSortList(List<String> al)
	{
		String temp;
		for (int i = 1; i < al.size(); i++) {
			for(int j = i ; j > 0 ; j--){
				if(al.get(j).compareToIgnoreCase( al.get(j-1) ) < 0){
					temp = al.get(j);
					al.set(j, al.get(j-1));
					al.set(j-1, temp);
				}
			}
		}     
		return true;
	}

	/*************************************************************************
	 * 
	 * @param cd  List of CustData objects to be sorted
	 * @return true upon successful completion
	 * 
	 * This method provides a first-level insertion sort on Customer data
	 *  by state.  Upon completion, array list will be sorted by state, then 
	 *  by zip.
	 */
	public static boolean insertionSortCustData(List<CustData> cd)
	{         
		CustData temp;
		for (int i = 1; i < cd.size(); i++) {
			for(int j = i ; j > 0 ; j--){
				if(cd.get(j).state.compareToIgnoreCase( cd.get(j-1).state ) < 0){
					temp = cd.get(j);
					cd.set(j, cd.get(j-1));
					cd.set((j-1), temp);
				}
			}
		}     
		return true;
	}
	public boolean quickSortList(ArrayList<String> al)
	{
		//copy, paste and modify!
		return true;
	}
	public boolean mergeSortList(ArrayList<String> al)
	{
		//copy, paste and modify!
		return true;
	}

}  // end class MySorts
