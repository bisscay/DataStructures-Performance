/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	// Clear-box utility
	LLNode<String> listNode;
	LLNode<Integer> intNode;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Note: setup @Before is called for each test
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
		
		// Check removal on empty list - high bound removal
		try {
			emptyList.remove(0);
			fail("Remove: from empty list, high bound check");
		} 
		catch(IndexOutOfBoundsException e) {
		}
		
		// Test low-out-of-bound removal
		try {
			shortList.remove(-1);
			fail("Remove: lower-bound check");
		} catch (IndexOutOfBoundsException e) {
		}
		
		// Clear-box tests
		// head holds reference to sentinel
		intNode = list1.head.next.next; // node2 - element 1
		assertEquals("Remove: check pointers ", intNode.prev.data, (Integer)21);
		
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		
		// test Null addition
		try {
			emptyList.add(null);
			fail("AddEnd: null addition");
		} catch(NullPointerException e) {
		}
		
		assertEquals("AddEnd: check first entry","A",shortList.get(0));
		if(shortList.add("C")) {
			assertEquals("AddEnd: check multiple entry & return value","C",shortList.get(2));
		}
		assertEquals("AddEnd: check size",3,shortList.size());
		assertEquals("AddEnd: check previous pointer ",shortList.tail.prev.data, "C");

	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		// Mostly tested in other tests
		// test addEnd, add-index, remove, set
		
		// test on empty list
		assertEquals("Size: check empty list",0, emptyList.size());
		
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		// test for Null addition, exception should be thrown
		try {
			shortList.add(0, null);
			fail("AddIndex: addition of null");
		} catch(NullPointerException e) {
		}
		
		// test out-of-bounds exception
		try {
			shortList.add(-1, "C");
			fail("AddIndex: addition at negative index");
		} catch(IndexOutOfBoundsException e) {
		}
		try {
			longerList.add(LONG_LIST_LENGTH+1, 12);
			fail("AddIndex: addition at size index");
		} catch(IndexOutOfBoundsException e) {
		}
		
		// test addition on empty list - test addition at list-size
		emptyList.add(0,1);
		assertEquals("AddIndex: check empty-list size", emptyList.size(), 1);
		assertEquals("AddIndex: addition at 0-index in empty list", emptyList.get(0), Integer.valueOf(1));
		// test addition on multiple-element list
		shortList.add(1, "Mid");
		assertEquals("AddIndex: addition at mid-index", shortList.get(1), "Mid");
		assertEquals("AddIndex: check list size", shortList.size(), 3);
		// test pointers - clear-box test
		listNode = shortList.head.next.next;
		assertEquals("AddIndex: check mid-index pointers",listNode.prev.data, "A");
		assertEquals("AddIndex: check pointers after added element", listNode.next.prev.data, "Mid");
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		 
	    shortList.set(0, "C");
		// check returned value
	    assertEquals("Set: check returned value", shortList.get(0),"C");
		// test null entry
	    try {
	    	shortList.set(0,null);
	    	fail("Set: null element");
	    } catch(NullPointerException e) {
	    }
		// test out of bound index
	    try {
	    	emptyList.set(0,1);
	    	fail("Set: out of bound index");
	    } catch(IndexOutOfBoundsException e) {
	    }
		// test edge-cases
	    // potentially redundant tests - due to sentinels
	    // set on single entry list
	    // set on first index of multi-entry list
	    // set on last index of multi-entry list
	    // set on middle index of multi-entry list
	    // set on null and out-of-bounds for each condition above
	     
	}
	
	// TODO: Optionally add more test methods.
	
}
