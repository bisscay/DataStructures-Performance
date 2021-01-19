package document;

/**
 * An extension of the BasicDocument abstract class
 * @author bAe
 */
public class ImprovedDocument extends BasicDocument {
	
	/**
	 * Create a new ImprovedDocument object
	 * 
	 * @param text The full text of the document
	 */
	ImprovedDocument(String text) {
		// Call BasicDocument's parameterized constructor
		super(text);
	}
	
	/**
	 * Get the amount of dots within a number
	 * 
	 * @return An int defining the dot count among contiguous digits
	 */
	private int getDotDigitCount() {
		int dotDigitCount = 0;
		String[] dotSplitArray = getText().split("[.]");
		
		int arraySize = dotSplitArray.length;
		for(int i = 0; i < arraySize; ++i) {
			if(dotSplitArray[i].matches("^\\d+$"))
				++dotDigitCount;
		}
		return dotDigitCount;
	}
	
	/**
	 * Get the number of sentences in the document.
	 * Sentences are defined as contiguous strings of characters ending in an 
	 * end of sentence punctuation (. ! or ?) or the last contiguous set of 
	 * characters in the document, even if they don't end with a punctuation mark.
	 * 
	 * Note: Dot between digits does not terminate a sentence. 
	 * Assuming: A sentence has a subject and predicate;
	 * A line with just a number is not considered a sentence;
	 * A clause or phrase can pass a sentence, so long as a punctuation terminates. 
	 * 
	 * @return The number of sentences in the document.
	 */
	public int getNumSentences()
	{
		// Psuedocode
		// Hold interned copy of text
		// Split interned-copy at '.'
		// Determine string-array entries with just-numbers
		// Store digit-dot-count
		// Split text at punctuation (.!?)
		// Determine length of split text-array
		// Subtract just-numbers count from text-array length
		// return result
		
		// Dot between digits does not terminate a sentence
		// Store digit-dot-count
		int digitDotCount = getDotDigitCount();
		
		// No need recreating basic-document properties - DRY
		Document basicDocument = new BasicDocument(getText());
		
		// Determine length of split text-array
		int splitTextSize = basicDocument.getNumSentences();
		
		// Any entry with just a number will not add to the sentence count
		// Subtract just-numbers count from text-array length
		// return result
		return splitTextSize - digitDotCount;
	}
	
	// Driver code to test this class
	public static void main(String[] args) {
		/* Each of the test cases below uses the method testCase.  The first 
		 * argument to testCase is a Document object, created with the string shown.
		 * The next three arguments are the number of syllables, words and sentences 
		 * in the string, respectively.
		 */
		BasicDocument test = new ImprovedDocument("sentence, with, lots, of, commas.!  "
		        + "(And some poaren)).  The output is: 7.5.");
		testCase(test, 15, 11, 3);
	}
}
