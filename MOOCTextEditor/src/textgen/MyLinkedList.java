package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	private int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		
		size = 0;
		// Assign sentinels - I like this name
		head = new LLNode<E>();
		tail = new LLNode<E>();
		// Initialize pointers
		head.next = tail;
		tail.prev = head;
	}
	
	/**
	 * Get node at specified index
	 * @param index An int index to search on
	 * @return An LLNode<E> node at index
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	private LLNode<E> getNode(int index) {
		// Initialize node to head
		LLNode<E> node = head;
		// Iterate through nodes
		for (int i = 0; i <= index; ++i) {
			// node search
			node = node.next;
		}
		return node;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		// Ensure element is not NULL
		if (element.equals(null)) {
			throw new NullPointerException("Adding Null to an Empty List");
		} else {
		
		// Get list-last node
		LLNode<E> lastNode = this.tail.prev;

		// Create new entry at list-end
		new LLNode<E>(this.tail, lastNode, element);

		// increase list-size
		++size;

		return true;
		}
		
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		
		// Ensure index is within bound
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		// Search for node
		LLNode<E> node = getNode(index);
			
		// return node-data
		return node.data;

	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		
		// Ensure index and element is not Null
		if(element == null) {
			throw new NullPointerException();
		}
		
		// Ensure index is within bound
		if(index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		
		// Search for node at index
		LLNode<E> indexNode = getNode(index);
		
		// Access node before index
		LLNode<E> beforeNode = indexNode.prev;
		
		// Make addition
		new LLNode<E>(indexNode,beforeNode,element);
		
		// Update size
		++size;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		
		// Disallow removal on empty list
		if(this.size() == 0) {
			throw new IndexOutOfBoundsException();
		}
		
		// Ensure index is within bound
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		// Search for index-node
		LLNode<E> indexNode = getNode(index);
		
		// Make removal
		LLNode<E> beforeNode = indexNode.prev;
		LLNode<E> afterNode = indexNode.next;
		// Reassign before-index next pointer
		beforeNode.next = afterNode;
		// Reassign after-index previous pointer
		afterNode.prev = beforeNode;
		
		// Update size
		--size;
		
		// return data at truncated index
		return indexNode.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		
		// Ensure element is not Null
		if(element == null) {
			throw new NullPointerException();
		}
		
		// Ensure index is within bound
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		// Get node at index
		LLNode<E> indexNode = getNode(index);
		// Update node-data
		E oldValue = indexNode.data;
		indexNode.data = element;
		
		return oldValue;
	}   
	
	@Override
	public String toString() {
		return "MyLinkedList [\nsize=" + size + "]";
	} 
	
	// Driver code for console debug
	public static void main(String[] args) {
		MyLinkedList<Integer> myLinkedList = new MyLinkedList<Integer>();
		myLinkedList.add(65);
		myLinkedList.add(21);
		myLinkedList.add(42);
		System.out.println(myLinkedList);
	}
}

class LLNode<E> 
{
	// Node attributes
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor
	
	/**
	 * Default Constructor - 
	 * Creates object with null pointers and null data
	 */
	public LLNode()
	{
		prev = null;
		next = null;
		data = null;
	}

	/**
	 * Generic Constructor - 
	 * Creates a node with data and null pointers
	 * @param e Data element E to be added in node
	 */
	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	
	/**
	 * Generic Constructor -
	 * Adds an element-node between nodes
	 * @param next A reference to this node's next LLNode<E> node 
	 * @param prev A reference to this nodes's previous LLNode<E> node
	 * @param e An object E for data
	 */
	public LLNode(LLNode<E> next, LLNode<E> prev, E e) {
		this(e); // this is beautiful
		// Assign new-node next to tail
		this.next = next;
		// Assign new-node previous to last-node
		this.prev = prev;
		// Reassign last-node next to new element
		prev.next = this;
		// Reassign tail previous to new node
		next.prev = this;
		
		// For addition at index
		// Arguments - indexNode,beforeNode,element
		// Create a new node from element
		// Point new-node next to index-node
		// Point new-node previous to before-index
		// Point before-index node next to new-node
		// Point index-node previous to new-index
	}
	
	@Override
	public String toString() {
		return "LLNode [prev=" + prev.data + ", next=" + next.data + ", data=" + data + "]";
	}

}
