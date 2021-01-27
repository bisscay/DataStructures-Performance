package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		// TODO: Implement this method
		
		// throw null-pointer-exception
//		if(sourceText == null) 
//			throw new NullPointerException("Null was passed as a sourceText."
//				+ "\nPlease pass in an actual string");
		
		if(wordList.size() > 0) return; // avoid running train on already trained text
		
		String[] textArray = sourceText.split("\\s+");
		
		starter = textArray[0];
		String prevWord = starter;
		
		ListNode newNode;
		int textSize = textArray.length;
		for(int i = 1; i < textSize; ++i) {
			String word = textArray[i];
			// Search for a node and update its word-list if found
			if(!containsWord(prevWord, word)) {
				// else, add new node to node-list
				newNode = new ListNode(prevWord);
				newNode.addNextWord(word);
				wordList.add(newNode);
			}
			prevWord = word;
		}
		
		// add first word to last-node word list
		// check if node-list already has last word as a node
		if(!containsWord(prevWord, starter)) {
			// else, add new node to node-list
			newNode = new ListNode(prevWord);
			newNode.addNextWord(starter);
			wordList.add(newNode);
		}
		
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
//		Pseudocode		
//		set "currWord" to be the starter word
//		set "output" to be ""
//		add "currWord" to output
//		while you need more words
//		   find the "node" corresponding to "currWord" in the list
//		   select a random word "w" from the "wordList" for "node"
//		   add "w" to the "output"
//		   set "currWord" to be "w" 
//		   increment number of words added to the list
		
		if(numWords == 0) return "";
		
		String currWord = starter;
		StringBuilder output = new StringBuilder(); // Use mutable string for good space complexity
		String randomWord = "";
		output.append(currWord); // first word added
		while(numWords != 1) {
			for(ListNode node : wordList) { // search
				if(currWord.equals(node.getWord())) {
					randomWord = node.getRandomNextWord(rnGenerator);
					output.append(" ");
					output.append(randomWord);
					break; // stop search once found
				}
			}
			currWord = randomWord;
			--numWords;
		}
		
		return output.toString();
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.
		
		// Reinitialize instance variables
		wordList.clear();
		starter = "";
		
		// retrain
		train(sourceText);
	}
	
	// TODO: Add any private helper methods you need here.
	
	/**
	 * Search for a node and update its word-list if found
	 * @param prevWord The String word to search for in node-list
	 * @param word The String word to be added to node word-list
	 * @return A boolean if not if found or not
	 */
	private boolean containsWord(String prevWord, String word) {
		for(ListNode node : wordList) {
			if(prevWord.equals(node.getWord())) {
				node.addNextWord(word);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		//textString = null; textString = ""; textString = "hi there hi leo"; textString = "hi hi hi hello";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		
		//int nextWordsSize = nextWords.size();
		
		// random numbers bounded from 0(inclusive)
		// to nextWordsSize(exclusive)
		//int index = generator.nextInt(nextWordsSize);
		
	    return nextWords.get(generator.nextInt(nextWords.size()));
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


