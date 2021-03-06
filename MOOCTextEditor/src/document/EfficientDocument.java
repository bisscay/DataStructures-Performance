package document;

import java.util.List;
import java.util.ListIterator;

/** 
 * A class that represents a text document
 * It does one pass through the document to count the number of syllables, words, 
 * and sentences and then stores those values.
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class EfficientDocument extends Document {

	private int numWords;  // The number of words in the document
	private int numSentences;  // The number of sentences in the document
	private int numSyllables;  // The number of syllables in the document
	
	public EfficientDocument(String text)
	{
		super(text);
		// all text processed once and not in each method call
		processText(); // O(n)
	}
	
	
	/** 
	 * Take a string that either contains only alphabetic characters,
	 * or only sentence-ending punctuation.  Return true if the string
	 * contains only alphabetic characters, and false if it contains
	 * end of sentence punctuation.  
	 * 
	 * @param tok The string to check
	 * @return true if tok is a word, false if it is punctuation. 
	 */
	private boolean isWord(String tok) // O(1) : linear search, but word size referenced
	{
	    // Note: This is a fast way of checking whether a string is a word
	    // You probably don't want to change it.
		return !(tok.indexOf("!") >=0 || tok.indexOf(".") >=0 || tok.indexOf("?")>=0);
	}
	
	
    /** Passes through the text one time to count the number of words, syllables 
     * and sentences, and set the member variables appropriately.
     * Words, sentences and syllables are defined as described below. 
     */
	private void processText() // O(n)
	{
		// Call getTokens on the text to preserve separate strings that are 
		// either words or sentence-ending punctuation.  Ignore everything
		// That is not a word or a sentence-ending punctuation.
		// MAKE SURE YOU UNDERSTAND THIS LINE BEFORE YOU CODE THE REST
		// OF THIS METHOD.
		
		// Hold just Words or just contiguous-Punctuation
		List<String> tokens = getTokens("[!?.]+|[a-zA-Z]+"); // O(n): tokens computed once in scope
		
		String query;
		// Check each string in tokens for words
		int tokensSize = tokens.size();

		ListIterator<String> lit = tokens.listIterator();
		while(lit.hasNext()) {
			query = lit.next(); // list walk through using iterator has space-complexity over get's search behavior
			// strings with punctuation are ignored
			if(isWord(query)) { // O(1): word comparison constant for a text
				// increment word count
				++this.numWords;
				// compute syllable count for found word and update total count
				this.numSyllables += countSyllables(query); // O(1): word analysis
			}
		}
		
		// Everything else in tokens-length will be a sentence-punctuation
		this.numSentences = tokensSize - this.numWords;
		
		// Account for single line sentences without punctuation
		// if the last entry in tokens is not empty and a word
		if(tokensSize != 0) {
			if(isWord(tokens.get(tokensSize-1))) { // O(1): computation on a word
				this.numSentences += 1;
			}
		}
		// TODO: Finish this method.  Remember the countSyllables method from 
		// Document.  That will come in handy here.  isWord defined above will also help.
	}

	
	/**
	 * Get the number of sentences in the document.
	 * Sentences are defined as contiguous strings of characters ending in an 
	 * end of sentence punctuation (. ! or ?) or the last contiguous set of 
	 * characters in the document, even if they don't end with a punctuation mark.
	 * 
	 * Check the examples in the main method below for more information. 
	 *  
	 * This method does NOT process the whole text each time it is called.  
	 * It returns information already stored in the EfficientDocument object.
	 * 
	 * @return The number of sentences in the document.
	 */
	@Override
	public int getNumSentences() { // O(1) after object creation
		//TODO: write this method.  Hint: It's simple
		return this.numSentences;
	}

	
	/**
	 * Get the number of words in the document.
	 * A "word" is defined as a contiguous string of alphabetic characters
	 * i.e. any upper or lower case characters a-z or A-Z.  This method completely 
	 * ignores numbers when you count words, and assumes that the document does not have 
	 * any strings that combine numbers and letters. 
	 * 
	 * Check the examples in the main method below for more information.
	 * 
	 * This method does NOT process the whole text each time it is called.  
	 * It returns information already stored in the EfficientDocument object.
	 * 
	 * @return The number of words in the document.
	 */
	@Override
	public int getNumWords() { // O(1) after object creation
		//TODO: write this method.  Hint: It's simple
	    return this.numWords;
	}


	/**
	 * Get the total number of syllables in the document (the stored text). 
	 * To calculate the the number of syllables in a word, it uses the following rules:
	 *       Each contiguous sequence of one or more vowels is a syllable, 
	 *       with the following exception: a lone "e" at the end of a word 
	 *       is not considered a syllable unless the word has no other syllables. 
	 *       You should consider y a vowel.
	 *       
	 * Check the examples in the main method below for more information.
	 * 
	 * This method does NOT process the whole text each time it is called.  
	 * It returns information already stored in the EfficientDocument object.
	 * 
	 * @return The number of syllables in the document.
	 */
	@Override
	public int getNumSyllables() { // O(1) after object creation
        //TODO: write this method.  Hint: It's simple
        return this.numSyllables;
	}
	
	/**
	 * Can be used for testing
	 * We encourage you to add your own tests here.
	 * 
	 * Each of the test cases below uses the method testCase.  The first 
	 * argument to testCase is a Document object, created with the string shown.
	 * 
	 * The next three arguments are the number of syllables, words and sentences 
	 * in the string, respectively.
	 */
	public static void main(String[] args)
	{
	    testCase(new EfficientDocument("This is a test.  How many???  "
                + "Senteeeeeeeeeences are here... there should be 5!  Right?"),
                16, 13, 5);
        testCase(new EfficientDocument(""), 0, 0, 0);
        testCase(new EfficientDocument("sentence, with, lots, of, commas.!  "
                + "(And some poaren)).  The output is: 7.5."), 15, 11, 4);
        testCase(new EfficientDocument("many???  Senteeeeeeeeeences are"), 6, 3, 2); 
        testCase(new EfficientDocument("Here is a series of test sentences. Your program should "
				+ "find 3 sentences, 33 words, and 49 syllables. Not every word will have "
				+ "the correct amount of syllables (example, for example), "
				+ "but most of them will."), 49, 33, 3);
		testCase(new EfficientDocument("Segue"), 2, 1, 1);
		testCase(new EfficientDocument("Sentence"), 2, 1, 1);
		testCase(new EfficientDocument("Sentences?!"), 3, 1, 1);
		testCase(new EfficientDocument("Lorem ipsum dolor sit amet, qui ex choro quodsi moderatius, nam dolores explicari forensibus ad."),
		         32, 15, 1);
		testCase(new EfficientDocument("12345"),0,0,0); // NO words by definition, equivalent to empty string - ""
		testCase(new EfficientDocument("1?23.!45"),0,0,2); // Three empty strings but two sentence termination  
		
	}
	

}
