package threading;

import sortExample.MySorts;
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
 *   test the performance of various sorts against the
 *   cust.txt data file (this file hardcoded in DataLoader).
 *************************************************************/

public class SimpleThreads {
	public static DataLoader dl = new DataLoader(); 

	private static class SortThread  implements Runnable {
		String str; //I need a parameter, str, to specify the sort type for the thread
		SortThread(String s) { str = s; }  // This allowsthe SortThread method to receive
										   //   the sort type. Runnable methods cannot accept parameters,
										   //   so if they're necessary, as they are here, we have to use this method.
		public void run() {
			long sStartTime = System.currentTimeMillis();
			long sEndTime;

			String threadName =	Thread.currentThread().getName();

			if (str.equalsIgnoreCase("insertion"))
			{
				sStartTime = System.currentTimeMillis();
				MySorts.insertionSort(dl.stateList);
				sEndTime = System.currentTimeMillis();
				System.out.println(threadName + " Total Insertion Sort Time:  " + (sEndTime-sStartTime));
			}
			else if (str.equalsIgnoreCase("selection"))
			{
				sStartTime = System.currentTimeMillis();
				MySorts.selectionSort(dl.stateList);
				sEndTime = System.currentTimeMillis();
				System.out.println(threadName + " Total Selection Sort Time:  " + (sEndTime-sStartTime));
			}
			else
				System.out.println("Invalid sort type");
		}  // end run() 
	}  // end class SortRun

	public static void main(String args[])
			throws InterruptedException {

		dl.openAndLoadFile(); //load cust.txt into dl ArrayList

		long sortStartTime = System.currentTimeMillis();
		long sortEndTime;
		Thread t1 = new Thread(new SortThread("selection"));  //instantiate new thread for selection sort
		t1.start();  //Start a selection sort in thread xx

		Thread t2 = new Thread(new SortThread("insertion"));  //instantiate another new thread for insertion sort
		t2.start();  //Start an insertion sort in thread yy

		sortEndTime = System.currentTimeMillis();  //Get time after both sorts started
		System.out.println("Sort Time (but not really):  " + (sortEndTime-sortStartTime)); //Sorts are still running 

		// loop while t1 thread exists - not necessary for thread functionality; I'm just showing another feature of threading.
		while (t1.isAlive()) {
			threadMessage("Still waiting for selection...");
			// Wait maximum of 1 second for MessageLoop thread
			// to finish.
			t1.join(1000);
		}
		// loop while t2 thread exists - not necessary for thread functionality; I'm just showing another feature of threading.
		while (t2.isAlive()) {
			threadMessage("Still waiting for insertion...");
			// Wait maximum of 1 second for MessageLoop thread
			// to finish.
			t2.join(1000);
		}
		threadMessage("Finally together and done!");
		System.out.println("Total Sort Time after joins:  " + (sortEndTime-sortStartTime));
	}

	// Display a message, preceded by
	// the name of the current thread
	static void threadMessage(String message) {
		String threadName =	Thread.currentThread().getName();
		System.out.format("%s: %s%n",  threadName,	message);
	}

}  // end class SimpleThreads
