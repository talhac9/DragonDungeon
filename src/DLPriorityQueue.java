
/**
 * This class represents a double linked priority queue. It implements the ADT PriorityQueueADT<T>.
 * A priority queue is an ADT that stores a collection of data items, where each item has a value or priority. 
 * The priority queue stores items based off the priority in a non-descending order.
 * @author Talha Chaudhry (tchaud@uwo.ca)
 * @param <T>  Generic Type
 */
public class DLPriorityQueue<T> implements PriorityQueueADT<T>{
	
	private DLinkedNode<T> front;	// Stores the front of the queue
	private DLinkedNode<T> rear;	// Stores the rear of the queue
	private int count;				// Stores the total amount of items in queue
	
	/**
	 * Constructor method for an empty priority queue. 
	 * Sets front and real to null and count to 0.
	 */
	
	public DLPriorityQueue() {
		front = null;
		rear = null;
		count = 0;
	}

	/**
	 * Method that adds to the priority queue the given dataItem with its associated priority.
	 * When storing the data items in the priority queue the data items are sorted in nondecreasing order of priority. 
	 * The data item with smallest priority will be stored in the node at the front of the linked list.
	 * The data item with the largest priority will be stored in the node at the rear of the list. 
	 * @param dataItem 		data item to store in new node
	 * @param priority		priority of new node
	 */
	
	public void add (T dataItem, double priority) {
		
		DLinkedNode<T> node = new DLinkedNode<T>(dataItem, priority); // creates new node with the given parameters
		
		if (front == null && rear == null) {	// Adds node to the front of the queue if queue is empty
			front = node;
			rear = node;
			count++;
		}
			
		else {
			
			count++;	// increases count
			double findPrio[] = new double[count];
			DLinkedNode<T> current = front;
			
			int a = 0;
			while (current != null) {		// Stores the priority of each node into an array named findPrio
				findPrio[a] = current.getPriority();
				current = current.getNext();
				a++;
			}
			findPrio[count-1] = node.getPriority();		// adds the priority of the new node to add to findPrio
			
			double temp = 0;
			for (int i = 0; i < findPrio.length; i++) {     // Sorts the array of priorities into a non descending order
		           for (int j = i+1; j < findPrio.length; j++) {     
		              if (findPrio[i] > findPrio[j]) {    
		                  temp = findPrio[i];    
		                  findPrio[i] = findPrio[j];    
		                  findPrio[j] = temp;    
		               }     
		            }     
		        }
	
			int index = 0;
			for (int i = 0; i < count; i++) {	// finds the index of the priority of the new node to add from findPrio
				if (findPrio[i] == node.getPriority()) index = i;
			}
			
			if (index == 0) {		// If the index of the priority of the new node is at the front of the list, sets it to front of queue
				node.setNext(front);
				node.getNext().setPrev(node);
				front = node;
			}
			
			else if (index == count-1) { // If the index of the priority of the new node is at the rear of the list, sets it to rear of queue
				
				node.setPrev(rear);
				node.getPrev().setNext(node);
				rear = node;
			}
			
			else {
				
				if (index == 1) {	// if the index is 1, updates the front pointer and adds new node
					
					node.setNext(front.getNext());
					node.setPrev(front);
					front.setNext(node);
					
				}
				
				else {
					
					int b = 0;
					current = front;
					while (b < index-1) {	// Gets the node that is previous to where the new node must be stored
						current = current.getNext();
						b++;
					}
					
					node.setNext(current.getNext());	// Inserts new node into list
					node.setPrev(current);
					current.setNext(node);
					
					current = front;	// If the new node index is the second last node, updates the rear of previous
					while (current.getNext().getNext() != null) current = current.getNext();
					current.getNext().setPrev(current);
				}
				
			
			}
		}
	
	}
	
	/**
	 * Method that changes the priority of the node of the given dataItem to the new value.
	 * @param dataItem 	dataItem to search for in queue and update
	 * @param newPriority 	New priority for node
	 * @throws InvalidElementException
	 */
	
	public void updatePriority(T dataItem, double newPriority) throws InvalidElementException {
		
		DLinkedNode<T> current = front; 
		DLinkedNode<T> toRemove = null;
		boolean found = false;			
		while (current != null) {		// Searches through queue and finds if dataItem is in queue 
			if (dataItem == current.getDataItem()) {
				found = true;			// If dataItem is found, sets found to true and toRemove to the node to replace
				toRemove = current;
				break;
			}
			current = current.getNext();
		}
		
		// Throws InvalidElementException if dataItem is not found in queue
		if (!found) throw new InvalidElementException("Element was not found in list."); 
		
		add(dataItem, newPriority);		// adds new node to queue with the dataItem and new priority
		count--;
		current = front; 
		while (current != null) {	// removes the old node from queue
			if (current == toRemove) {
				
				if (current == front) {		// removes old node from front of queue
					front.getNext().setPrev(null);
					front = front.getNext();
					break;
				}
				
				else if (current == rear) {  // removes old node from rear of queue
					rear.getPrev().setNext(null);
					rear = rear.getPrev();
					break;
				}
				
				else {  // removes old node from middle of queue
					current.getNext().setPrev(current.getPrev());
					current.getPrev().setNext(current.getNext());
					break;
				}
				
			}
			current = current.getNext();
		}
		
	}
	
	/**
	 * Method that removes and returns the dataItem in the priority queue with smallest priority. 
	 * @throws EmptyPriorityQueueException
	 */
	
	public T removeMin() throws EmptyPriorityQueueException {
		
		// Throws EmptyPriorityQueueException if the queue is empty
		if (front == null) throw new EmptyPriorityQueueException("Priority Queue is empty.");
		DLinkedNode<T> node = front;
		
		if (count == 1) {  // Makes the queue empty if there is only one node in queue
			front = null;
			rear = null;
			count = 0;
			return node.getDataItem();
		}
		
		else if (count == 2) {
			front = front.getNext();
			rear = front;
			count = 1;
			return node.getDataItem();
		}
		else {
			front = front.getNext();  // Removes the first node from the queue and reassigns front
			front.setPrev(null);
			count--;
			return node.getDataItem();
		}
	}
	
	/**
	 * Method that returns a boolean value whether the queue is empty or not.
	 * @return True if queue is empty, False if queue is not empty
	 */
	
	public boolean isEmpty() {
		if (front == null) return true;
		else return false;
	}
	
	/**
	 * Method that returns the number of data items in the priority queue.
	 * @return count  size of queue
	 */
	
	public int size() {
		return count;
	}
	
	/**
	 * Method that returns a String representation of the priority queue.
	 * @return s  returns string representation of priority queue
	 */
	
	public String toString() {
		
		String s = "";
		DLinkedNode<T> node = front;
		
		while (node != null) {
			s += node.getDataItem().toString();
			node = node.getNext();
		}
		return s;
	}
	
	/**
	 * Method that returns the rear node of the queue.
	 * @return rear  Returns the rear (last) node of the queue
	 */
	
	public DLinkedNode<T> getRear() {
		return rear;
	}
	
	

}