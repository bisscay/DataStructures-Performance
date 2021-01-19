package document;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/** A class for timing the EfficientDocument and BasicDocument classes
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 */

public class DocumentBenchmarking {

	
	public static void main(String [] args) {
		// Reference keys
//		double n1, n, efficientReference = 6.52163451E8; // 4.18E9 3.6E9
//		double basicReference = 3.417878149E9;

	    // Run each test more than once to get bigger numbers and less noise.
	    // You can try playing around with this number.
	    int trials = 100;//100 // create object and call flesh score the amount of time

	    // The text to test on
	    String textfile = "data/warAndPeace.txt";
	    
	    // The amount of characters to increment each step
	    // You can play around with this
		int increment = 20000;//20000 // scale input by this amount of increase

		// The number of steps to run.  
		// You can play around with this.
		int numSteps = 20; // 20; // print out this number of time
		
		// The number of characters to start with. 
		// You can play around with this.
		int start = 50000;//50000;
		
		// TODO: Fill in the rest of this method so that it runs two loops
		// and prints out timing results as described in the assignment 
		// instructions and following the pseudocode below.
		for (int numToCheck = start; numToCheck < numSteps*increment + start; 
				numToCheck += increment)
		{
			// numToCheck holds the number of characters that you should read from the 
			// file to create both a BasicDocument and an EfficientDocument.  
			
			/* Each time through this loop you should:
			 * 1. Print out numToCheck followed by a tab (\t) (NOT a newline)
			 */
			System.out.print(numToCheck +"\t");
			
			 /* 2. Read numToCheck characters from the file into a String
			 *     Hint: use the helper method below.
			 */
			String characterQuery = getStringFromFile(textfile, numToCheck);
			 
			 /* 3. Time a loop that runs trials times (trials is the variable above) that:
			 *     a. Creates a BasicDocument 
			 *     b. Calls fleshScore on this document
			 */
			long startTime = System.nanoTime();
			for(int i = 0; i < trials; ++i) {
				Document basic = new BasicDocument(characterQuery);
				basic.getFleschScore();
			}
			long endTime = System.nanoTime();
			
			 /* 4. Print out the time it took to complete the loop in step 3 
			 *      (on the same line as the first print statement) followed by a tab (\t)
			 */
			double duration = endTime - startTime;
			System.out.print(duration +"\t");
			
			 /* 5. Time a loop that runs trials times (trials is the variable above) that:
			 *     a. Creates an EfficientDocument 
			 *     b. Calls fleshScore on this document
			 */
			startTime = System.nanoTime();
			for(int i = 0; i < trials; ++i) {
				Document efficient = new EfficientDocument(characterQuery);
				efficient.getFleschScore();
			}
			endTime = System.nanoTime();
			
			 /* 6. Print out the time it took to complete the loop in step 5 
			 *      (on the same line as the first print statement) followed by a newline (\n) 
			 */
			duration = endTime - startTime;
			System.out.print(duration +"\n");
			
			// Reference plots for n (linear), n^2 (quadratic) and n^3 (polynomial/cube)
//			n1 = (efficientReference + (efficientReference * numToCheck / start));
//			n = (efficientReference + (efficientReference * numToCheck / start));
//			n = n * n;
//			System.out.print(n1 +"\t");
//			System.out.print(n +"\n");
			 
		}
	
	}
	
	/** Get a specified number of characters from a text file
	 * 
	 * @param filename The file to read from
	 * @param numChars The number of characters to read
	 * @return The text string from the file with the appropriate number of characters
	 */
	public static String getStringFromFile(String filename, int numChars) {
		
		StringBuffer s = new StringBuffer();
		try {
			FileInputStream inputFile= new FileInputStream(filename);
			InputStreamReader inputStream = new InputStreamReader(inputFile);
			BufferedReader bis = new BufferedReader(inputStream);
			int val;
			int count = 0;
			while ((val = bis.read()) != -1 && count < numChars) {
				s.append((char)val);
				count++;
			}
			if (count < numChars) {
				System.out.println("Warning: End of file reached at " + count + " characters.");
			}
			bis.close();
		}
		catch(Exception e)
		{
		  System.out.println(e);
		  System.exit(0);
		}
		
		
		return s.toString();
	}
	
}
