/**
 * 
 */
package finalProject;

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
	public static boolean selectionSortCustData(List<finalProject.CustData> fileData)
	{         
		for (int i = 0; i < fileData.size() - 1; i++)
		{
			int index = i;
			for (int j = i + 1; j < fileData.size(); j++)
				if (fileData.get(j).getState().compareToIgnoreCase( fileData.get(index).getState()) < 0 ) 
					index = j;

			finalProject.CustData smallerValue = fileData.get(index); 
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
	public static boolean insertionSortCustData(List<sortExample.CustData> cd)
	{         
		sortExample.CustData temp;
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
	/*****************************************************************
	 * 
	 * @param al 	List to be sorted
	 * @param first	begin index of the element
	 * @param last	end index of the element
	 * 
	 * This method provides a quicksort on the list data.
	 */
	public static void quickSortList(List<String> al, int first, int last)
	{
		if (al == null || al.size() == 0)
			return;

		if (first >= last)
			return;

		// pick the pivot(middle index element)
		int middle = first + (last - first) / 2;
		String pivot = al.get(middle);

		// make left < pivot and right > pivot
		int i = first, j = last;
		while (i <= j) 
		{
			/**
             * In each iteration, we will identify a number from left side which 
             * is greater than the pivot value, and also we will identify a number 
             * from right side which is less then the pivot value. Once the search 
             * is done, we exchange both numbers.
             */
			while (al.get(i).compareToIgnoreCase(pivot) < 0)
			{
				i++;
			}

			while (al.get(j).compareToIgnoreCase(pivot) > 0)
			{
				j--;
			}

			if (i <= j) 
			{
				String temp = al.get(i);
				al.set(i, al.get(j));
				al.set(j, temp);
				//move index to next position on both sides
				i++;
				j--;
			}
			
		}		

		// recursively sort two sub parts
		if (first < j)
			quickSortList(al, first, j);

		if (last > i)
			quickSortList(al, i, last);
	}
	
	/********************************************************************
	 * 
	 * @param fileData 	List of CustData objects to be sorted.
	 * @param first	the beginning index of the object
	 * @param last	the ending index of the object
	 * 
	 * This method provides a first-level quick sort on Customer data 
	 * by state. Upon completion, array list will be sorted by state,
	 * then by zip.
	 */
	public static void quickSortCustData(List<CustData> fileData, int first, int last)
	{
		if (fileData == null || fileData.size() == 0)
			return;

		if (first >= last)
			return;

		// pick the pivot(middle index element)
		int middle = first + (last - first) / 2;
		String pivot = fileData.get(middle).getState() + fileData.get(middle).getZip();

		// make left < pivot and right > pivot
		int i = first, j = last;
		while (i <= j) 
		{
			/**
             * In each iteration, we will identify a number from left side which 
             * is greater than the pivot value, and also we will identify a number 
             * from right side which is less then the pivot value. Once the search 
             * is done, we exchange both numbers.
             */
			while ((fileData.get(i).getState() + fileData.get(i).getZip()).compareToIgnoreCase(pivot) < 0)
			{
				i++;
			}

			while ((fileData.get(j).getState() + fileData.get(j).getZip()).compareToIgnoreCase(pivot) > 0)
			{
				j--;
			}

			if (i <= j) 
			{
				CustData temp = fileData.get(i);
				fileData.set(i, fileData.get(j));
				fileData.set(j, temp);
				//move index to next position on both sides
				i++;
				j--;
			}
			
		}		

		// recursively sort two sub parts
		if (first < j)
			quickSortCustData(fileData, first, j);

		if (last > i)
			quickSortCustData(fileData, i, last);
	}
	
	/*****************************************************************
	 * 
	 * @param al 	List to be sorted
	 * @param first	begin index of the element
	 * @param last	end index of the element
	 * 
	 * This method provides a quicksort on the list data.
	 */
	public static void quickSortStateInfo(List<StateInfo> al, int first, int last)
	{
		if (al == null || al.size() == 0)
			return;

		if (first >= last)
			return;

		// pick the pivot(middle index element)
		int middle = first + (last - first) / 2;
		double pivot = al.get(middle).getPopulation();

		// make left < pivot and right > pivot
		int i = first, j = last;
		while (i <= j) 
		{
			/**
             * In each iteration, we will identify a number from left side which 
             * is greater than the pivot value, and also we will identify a number 
             * from right side which is less then the pivot value. Once the search 
             * is done, we exchange both numbers.
             */
			while (al.get(i).getPopulation() > pivot)
			{
				i++;
			}

			while (al.get(j).getPopulation() < pivot)
			{
				j--;
			}

			if (i <= j) 
			{
				StateInfo temp = al.get(i);
				al.set(i, al.get(j));
				al.set(j, temp);
				//move index to next position on both sides
				i++;
				j--;
			}
			
		}		

		// recursively sort two sub parts
		if (first < j)
			quickSortStateInfo(al, first, j);

		if (last > i)
			quickSortStateInfo(al, i, last);
	}
	
	public boolean mergeSortList(ArrayList<String> al)
	{
		//copy, paste and modify!
		return true;
	}

}  // end class MySorts
