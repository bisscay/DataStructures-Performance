package document;

/** 
 * A class that represents a text document
 * @author UC San Diego Intermediate Programming MOOC team
 */
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Document {

	private String text;
	
	/** Create a new document from the given text.
	 * Because this class is abstract, this is used only from subclasses.
	 * @param text The text of the document.
	 */
	protected Document(String text)
	{
		this.text = text;
	}
	
	/** Returns the tokens that match the regex pattern from the document 
	 * text string.
	 * @param pattern A regular expression string specifying the 
	 *   token pattern desired
	 * @return A List of tokens from the document text that match the regex 
	 *   pattern
	 */
	protected List<String> getTokens(String pattern)
	{
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);
		
		while (m.find()) {
			tokens.add(m.group());
		}
		
		return tokens;
	}
	
	/**
	 * Is character a vowel
	 * @param character The char to be evaluated
	 * @return A boolean true if the character is a vowel
	 */
	private boolean isVowel(char character) {
		
		if(character == 'a' || character == 'e' || character == 'i' ||
				character == 'o' || character == 'u' || character == 'y' ||
				character == 'A' || character == 'E' || character == 'I' ||
				character == '0' || character == 'U' || character == 'Y') {
			return true;
		}
		return false;
	}
	
	/**
	 * Track first vowel-index
	 * @param word A String of characters to be considered
	 * @return An int index of the first found vowel
	 */
	private int getFirstVowelIndex(String word) {
		int wordLength = word.length();
		
		for(int i = 0; i < wordLength; ++i) {
			char character = word.charAt(i);
			// check if vowel
			if(isVowel(character)) {
				// track first vowel-index
				return i;
			}
		}
		// no vowel found
		return -1;
	}
	
	/** This is a helper function that returns the number of syllables
	 * in a word.  You should write this and use it in your 
	 * BasicDocument class.
	 * 
	 * You will probably NOT need to add a countWords or a countSentences 
	 * method here.  The reason we put countSyllables here because we'll 
	 * use it again next week when we implement the EfficientDocument class.
	 * 
	 * For reasons of efficiency you should not create Matcher or Pattern 
	 * objects inside this method. Just use a loop to loop through the 
	 * characters in the string and write your own logic for counting 
	 * syllables.
	 * 
	 * @param word  The word to count the syllables in
	 * @return The number of syllables in the given word, according to 
	 * this rule: Each contiguous sequence of one or more vowels is a syllable, 
	 *       with the following exception: a lone "e" at the end of a word 
	 *       is not considered a syllable unless the word has no other syllables. 
	 *       You should consider y a vowel.
	 */
	protected int countSyllables(String word)
	{
		// TODO: Implement this method so that you can call it from the 
	    // getNumSyllables method in BasicDocument (module 2) and 
	    // EfficientDocument (module 3).
		
		// Get first vowel-index
		int vowelIndex = getFirstVowelIndex(word);
		int syllableCount = 1;
		
		// No syllables present
		if(vowelIndex == -1) 
			return 0;
		
		// Syllable is at the last character
		int stringLength = word.length();
		int lastIndex = stringLength - 1;
		if(vowelIndex == lastIndex)
			return syllableCount;
		
		// Potentially more syllables
		// starting from next index
		for(int i = vowelIndex + 1; i < stringLength; ++i) {
			char character = word.charAt(i);
			// check if character is a vowel
			if(isVowel(character)) {
				// check for skip in contiguous nature
				// check if new vowel-index is greater than previous by 2 or more
				if(i > (vowelIndex + 1)) {
					++syllableCount;
				}
				// track vowel-index
				vowelIndex = i;
			}
		}
		
		// Consider words ending in 'e' with more than one syllables
		// if the last letter is A LONE 'e' & syllable count > 0,
		if(syllableCount > 0 && word.charAt(lastIndex) == 'e' && 
				!isVowel(word.charAt(lastIndex-1))) {
			// subtract one from syllable count
			--syllableCount;
		}
		
		return syllableCount;
	}
	
	/** A method for testing
	 * 
	 * @param doc The Document object to test
	 * @param syllables The expected number of syllables
	 * @param words The expected number of words
	 * @param sentences The expected number of sentences
	 * @return true if the test case passed.  False otherwise.
	 */
	public static boolean testCase(Document doc, int syllables, int words, int sentences)
	{
		System.out.println("Testing text: ");
		System.out.print(doc.getText() + "\n....");
		boolean passed = true;
		int syllFound = doc.getNumSyllables();
		int wordsFound = doc.getNumWords();
		int sentFound = doc.getNumSentences();
		if (syllFound != syllables) {
			System.out.println("\nIncorrect number of syllables.  Found " + syllFound 
					+ ", expected " + syllables);
			passed = false;
		}
		if (wordsFound != words) {
			System.out.println("\nIncorrect number of words.  Found " + wordsFound 
					+ ", expected " + words);
			passed = false;
		}
		if (sentFound != sentences) {
			System.out.println("\nIncorrect number of sentences.  Found " + sentFound 
					+ ", expected " + sentences);
			passed = false;
		}
		
		if (passed) {
			System.out.println("passed.\n");
		}
		else {
			System.out.println("FAILED.\n");
		}
		return passed;
	}
	
	
	/** Return the number of words in this document */
	public abstract int getNumWords();
	
	/** Return the number of sentences in this document */
	public abstract int getNumSentences();
	
	/** Return the number of syllables in this document */
	public abstract int getNumSyllables();
	
	/** Return the entire text of this document */
	public String getText()
	{
		return this.text;
	}
	
	/** return the Flesch readability score of this document */
	public double getFleschScore()
	{
	    // TODO: You will play with this method in week 1, and 
		// then implement it in week 2
	    return text.length();
	}
	
	
	
}