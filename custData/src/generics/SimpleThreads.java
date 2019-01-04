package generics;

/*************************************************************
 * SimpleThreads.java
 * AB
 * 2018
 *
 * This class illustrates multi-threading, by creating
 * 	a thread for each of five search methods. It is 
 *  currently incomplete, with only two of the five
 *  search methods being coded.
 *  
 *  Upon completion, this class will allow you to easily 
 *   test the performance of various sorts against the data in
 *   cust.txt data file (this file is hardcoded in DataLoader).
 *************************************************************/

public class SimpleThreads {
	
	private static class SortThread  implements Runnable {
		String str; //I need a parameter, str, to specify the sort type for the thread
		SortThread(String s) { str = s; }  // This allows the SortThread method to receive
										   //   the sort type. Runnable methods cannot accept parameters,
										   //   so if they're necessary, as they are here, we have to use this method.
		public void run() {
			//local variables are thread safe, so each thread maintains
			//	its own version of all local variables.
			long sStartTime; 
			long sEndTime;
			DataLoader dl = new DataLoader();
			String threadName =	Thread.currentThread().getName();

			dl.openAndLoadFile();  //load file data
			
			if (str.equalsIgnoreCase("insertion"))
			{
				sStartTime = System.currentTimeMillis();  //get start time 
				MySorts.insertionSortList(dl.stateList);  // generic insertion sort on state list
				//dl.displayStateList();		// display sorted state list
				MySorts.insertionSortCustData(dl.fileData);  //generic insertion sort cust address data
				//dl.displayCustAddr();  //display sorted cust address data
				sEndTime = System.currentTimeMillis();  //get end time
				System.out.println("\n" + threadName + " Total Insertion Sort Time:  " + (sEndTime-sStartTime));
			}
			else if (str.equalsIgnoreCase("selection"))
			{
				sStartTime = System.currentTimeMillis();  //get start time
				MySorts.selectionSortList(dl.stateList);    // selection sort state list
				//dl.displayStateList();		// display sorted state list
				MySorts.selectionSortCustData(dl.fileData);  //sort cust address data
				//dl.displayCustAddr();  //display sorted cust address data
				sEndTime = System.currentTimeMillis();  //get end time
				System.out.println("\n" +threadName + " Total Selection Sort Time:  " + (sEndTime-sStartTime));
			}
			else
				System.out.println("Invalid sort type");
		}  // end run() 
	}  // end class SortRun
	

	// Display a message, preceded by
	// the name of the current thread
	static void threadMessage(String message) {
		String threadName =	Thread.currentThread().getName();
		System.out.format("%s: %s%n",  threadName,	message);
	}


	public static void main(String args[])
			throws InterruptedException {

		long sortStartTime = System.currentTimeMillis();
		long sortEndTime;
		Thread t1 = new Thread(new SortThread("selection"));  //instantiate new thread for selection sort
		t1.start();  //Start a selection sort in thread xx

		Thread t2 = new Thread(new SortThread("insertion"));  //instantiate another new thread for insertion sort
		t2.start();  //Start an insertion sort in thread yy

		sortEndTime = System.currentTimeMillis();  //Get time after both sorts started
		System.out.println("Sort Time (but not really):  " + (sortEndTime-sortStartTime)); //Sorts are still running 

		// loop while t1 thread exists keep the user posted 
		while (t1.isAlive() || t2.isAlive()) {
			threadMessage("Still waiting for sorts...");
			// Wait 1 second between output messages.
			Thread.sleep(1000);
			
		}
		// Now that both threads have finished their work, we can join (merge) back
		//	together.  Comment out the lines below and notice the difference in the
		//  occurrence of the two output statements below.
		t1.join();
		t2.join();
		
		threadMessage("\nFinally together and done!");
		System.out.println("Total Sort Time after joins:  " + (sortEndTime-sortStartTime));
	}

}  // end class SimpleThreads
