/**
 * 
 */
package sortExample;

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

	public static boolean bubbleSortList(List<String> al)
	{
		//iterate through the whole list backwards
		for(int i = al.size() - 1; i > 0; i--)
		{
			//loop from index 0 to i - 1
			for(int j = 0; j < i; j++)
			{
				//if the index at j is greater than the index at j + 1
				if(al.get(j).compareToIgnoreCase(al.get(j + 1)) > 0)
				{
					//switch the two numbers
					String toSwitch = al.get(j+1);
					al.set(j+1, al.get(j));
					al.set(j, toSwitch);
				}
			}
		}
		
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
			{
				if (al.get(j).compareToIgnoreCase( al.get(index)) < 0 ) 
				{
					index = j;
				}
			}
			
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
	public static boolean selectionSortCustData(List<sortExample.CustData> fileData)
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
				if(al.get(j).compareToIgnoreCase( al.get(j-1) ) < 0)
				{
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
	public static void quickSortCustData(List<sortExample.CustData> fileData, int first, int last)
	{
		if (fileData == null || fileData.size() == 0)
			return;

		if (first >= last)
			return;

		// pick the pivot(middle index element)
		int middle = first + (last - first) / 2;
		String pivot = fileData.get(middle).state;

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
			while (fileData.get(i).state.compareToIgnoreCase(pivot) < 0)
			{
				i++;
			}

			while (fileData.get(j).state.compareToIgnoreCase(pivot) > 0)
			{
				j--;
			}

			if (i <= j) 
			{
				sortExample.CustData temp = fileData.get(i);
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
	
	
	
	
	public boolean mergeSortList(ArrayList<String> al)
	{
		//copy, paste and modify!
		return true;
	}
	
	
	
	
	/*********************************************************************************************
	*	                                                                                    *
	*	                         mergeSortList                                              *
	*	                                                                                    *
        *********************************************************************************************/
	  public static boolean mergeSortList(List<String> al)
    {
        String[] arrays = new String[al.size()];
        for (int i = 0; i < al.size(); i++)
        {
            arrays[i] = al.get(i);
        }

        MySorts newSort = new MySorts();
        newSort.sort(arrays, null, null, 0);
        for (int i = 0; i < al.size(); i++)
        {
            al.set(i, arrays[i]);
        }
        return true;
    }

    public void sort(String[] inputArr, String[] array, String[] tempMergArr, int length)
    {
        array = inputArr;
        length = inputArr.length;
        tempMergArr = new String[length];
        doMergeSort(0, length - 1, tempMergArr, array);
    }

    private void doMergeSort(int lowerIndex, int higherIndex, String[] tempMergArr, String[] array)
    {
        if (lowerIndex < higherIndex)
        {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            doMergeSort(lowerIndex, middle, tempMergArr, array);
            // Below step sorts the right side of the array
            doMergeSort(middle + 1, higherIndex, tempMergArr, array);
            // Now merge both sides
            mergeParts(lowerIndex, middle, higherIndex, tempMergArr, array);
        }
    }

    private void mergeParts(int lowerIndex, int middle, int higherIndex, String[] tempMergArr, String[] array)
    {

        for (int i = lowerIndex; i <= higherIndex; i++)
        {
            tempMergArr[i] = array[i];
        }
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex)
        {
            if (tempMergArr[i].compareToIgnoreCase(tempMergArr[j]) < 0)
            {
                array[k] = tempMergArr[i];
                i++;
            }
            else
            {
                array[k] = tempMergArr[j];
                j++;
            }
            k++;
        }
        while (i <= middle)
        {
            array[k] = tempMergArr[i];
            k++;
            i++;
        }
    }

	
	/*********************************************************************************************
	*	                                                                                    *
	*	                         mergeSortCustData                                          *
	*	                                                                                    *
        *********************************************************************************************/
    public static boolean mergeSortCustData(List<CustData> cd)
    {
        CustData[] arrays = new CustData[cd.size()];
        for (int i = 0; i < cd.size(); i++)
        {
            arrays[i] = cd.get(i);
        }

        MySorts newSort = new MySorts();
        newSort.sort2(arrays, null, null, 0);
        for (int i = 0; i < cd.size(); i++)
        {
            cd.set(i, arrays[i]);
        }
        return true;
    }

    public void sort2(CustData[] inputArr, CustData[] array, CustData[] tempMergArr, int length)
    {
        array = inputArr;
        length = inputArr.length;
        tempMergArr = new CustData[length];
        doMergeSort2(0, length - 1, tempMergArr, array);
    }

    private void doMergeSort2(int lowerIndex, int higherIndex, CustData[] tempMergArr, CustData[] array)
    {
        if (lowerIndex < higherIndex)
        {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            doMergeSort2(lowerIndex, middle, tempMergArr, array);
            // Below step sorts the right side of the array
            doMergeSort2(middle + 1, higherIndex, tempMergArr, array);
            // Now merge both sides
            mergeParts2(lowerIndex, middle, higherIndex, tempMergArr, array);
        }
    }

    private void mergeParts2(int lowerIndex, int middle, int higherIndex, CustData[] tempMergArr, CustData[] array)
    {

        for (int i = lowerIndex; i <= higherIndex; i++)
        {
            tempMergArr[i] = array[i];
        }
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex)
        {
            if (tempMergArr[i].state.compareToIgnoreCase(tempMergArr[j].state) < 0)
            {
                array[k] = tempMergArr[i];
                i++;
            }
            else
            {
                array[k] = tempMergArr[j];
                j++;
            }
            k++;
        }
        while (i <= middle)
        {
            array[k] = tempMergArr[i];
            k++;
            i++;
        }
    }

}  // end class MySorts
