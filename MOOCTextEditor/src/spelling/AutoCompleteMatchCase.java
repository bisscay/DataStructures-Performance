package spelling;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteMatchCase implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteMatchCase()
	{
		root = new TrieNode();
	}

    /**
     * findKeyNode 
     * @param key A String text to match in the tree
     * @return A TrieNode that holds closest matching text in the tree (stem)
     */
    private TrieNode findKeyNode(String key) {
    	// start from root
    	TrieNode curr = root;
    	// get the closest matching node to the key in  the trie
    	char letter;
    	for(int i = 0; i < key.length(); ++i) {
    		letter = key.charAt(i);
    		if(curr.getChild(letter) == null) return curr;
    		curr = curr.getChild(letter);
    	}
    	return curr;
    }
    
//    // Dead Code: find key - NeverUsed, left for reference
//    private int findLastMatch(String key) {
//    	// start from root
//    	TrieNode curr = root;
//    	// get the closest matching node to the key in  the trie
//    	char letter;
//    	for(int i = 0; i < key.length(); ++i) {
//    		letter = key.charAt(i);
//    		if(curr.getChild(letter) == null) return i;
//    		curr = curr.getChild(letter);
//    	}
//    	return 0;
//    }
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		// find key
		// if present return
		// else create transition
		
		word = word.toLowerCase();
		
		// if present  and marked as word-node no addition is made
		if(isWord(word)) {
			return false;
		}
		
		TrieNode node = findKeyNode(word);
		int nodeTextSize = node.getText().length();
		
		// if present but not yet marked as word-node
		if(nodeTextSize == word.length()) {
			node.setEndsWord(true);
			++size;
			return true;
		}

		// if absent 
		// start at unaccounted letters in word
		// nodeTextSize has time-complexity over findLastMatch(word)
		int lastWordMatchIndex = nodeTextSize;
		for(int i = lastWordMatchIndex; i < word.length(); ++i) {
			// create transition
			node = node.insert(word.charAt(i));
		}
		
		node.setEndsWord(true);
		
		++size;
		
	    return true;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		s = s.toLowerCase();
		
		TrieNode node = findKeyNode(s);
		
		return (node.endsWord()&& node.getText().equals(s));
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	List<String> result = new LinkedList<String>();
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 TrieNode stemNode = findKeyNode(prefix);
    	 if(!(stemNode.getText().equals(prefix))) return result;
    	 
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 Queue<TrieNode> breadthFirst = new LinkedList<>();
    	 breadthFirst.add(stemNode);
    	 
    	// hold entry temporarily
    	 TrieNode temp;
    	 String entry;
    	 while(breadthFirst.size() > 0 && numCompletions > 0) {
    		 temp = breadthFirst.remove();
    		 // if present make addition
    		 entry = temp.getText();
        	 if(isWord(entry)) {
    	    	 // place in list
    	    	 result.add(entry);
    	    	 --numCompletions;
        	 }
        	 // add children
        	 for(Character child : temp.getValidNextCharacters()) {
        		 breadthFirst.add(temp.getChild(child));
        	 }
    	 }
    	 
         return result;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	
 	public static void main(String[] args) {
 		AutoCompleteMatchCase emptyDict; 
 		AutoCompleteMatchCase smallDict;
 		AutoCompleteMatchCase largeDict;
 		
 		emptyDict = new AutoCompleteMatchCase();
		smallDict = new AutoCompleteMatchCase();
		largeDict = new AutoCompleteMatchCase();

		smallDict.addWord("Hello");
		smallDict.addWord("HElLo");
		smallDict.addWord("help");
		smallDict.addWord("he");
		smallDict.addWord("hem");
		smallDict.addWord("hot");
		smallDict.addWord("hey");
		smallDict.addWord("a");
		smallDict.addWord("subsequent");
		
		smallDict.printTree();
		
		List<String> completions;
		
		completions = smallDict.predictCompletions("", 10);
		
		System.out.println("---\n" +completions);
 	}
	
}