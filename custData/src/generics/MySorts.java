/**
 * 
 */
package generics;

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
 *   
 *   GENERICS:  This version of the class includes two generic 
 *   methods:  sortAndDisplayList and displayList. Each accepts
 *   a list of any type, comprised of items any data type or 
 *   object type. The list will be converted to an array list
 *   sorted (only in the sortAndDisplay method), and displayed
 *   to the console. It could easily be altered to accept a 
 *   Scanner object and output to console, file, etc., as well.
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

	/******************************************************************************************
	 * 
	 * @param list<?>, String
	 * @return  boolean (indicates success)
	 * 
	 * This generic method will accept a list of any kind (array, arraylist, linked list, etc),
	 *   comprised of any data type or object type, and perform a standard (system) sort on the 
	 *   list and display the sorted list to console.
	 *   
	 *   Note that the underlying list is not altered.
	 */
	public static <T extends Comparable<? super T>> boolean sortAndDisplayList(List<?> list, String title) {

		List<T> a = new ArrayList<>();
		for (int i = 0; i < list.size(); i++)
		{
			a.add(i, (T) list.get(i).toString());
			//System.out.println(a.get(i));
		}

		try {
			//  Applying the Collections.sort operation.
			Collections.sort(a);

			System.out.println("\n\n" + title);
			// Setting the values from the sorted array to the list of type T. 
			ListIterator<T> i = a.listIterator();
			for (int j=0; j<a.size(); j++) {
				i.next();
				i.set((T)a.get(j));
				System.out.println(a.get(j));
			} // end for (int j=0; ...
		}// end try
		catch (Exception e)
		{
			//Skip anything we can't sort.
			return false;
		}
		return true;
	}

	/******************************************************************************************
	 * 
	 * @param list<?>, String
	 * @return  boolean (indicates success)
	 * 
	 * This generic method will accept a list of any kind (array, arraylist, linked list, etc),
	 *   comprised of any data type or object type, and display the list to console.
	 */
	public static <T extends Comparable<? super T>> boolean displayList(List<?> list, String title) {

		List<T> a = new ArrayList<>();
		System.out.println("\n\n" + title);
		try 
		{
			for (int i = 0; i < list.size(); i++)
			{
				a.add(i, (T) list.get(i).toString());
				System.out.println(a.get(i) );
			}
		}
		catch (Exception e)
		{
			System.out.println("***Error - displayList***");
		}
		return true;
	}  // end displayList

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
