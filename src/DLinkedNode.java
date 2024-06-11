/**
 * This class represents a double linked node for a priority queue. The node stores the next and previous node in the queue 
 * and also stores its own priority and data of type T.
 * @author Talha Chaudhry (tchaud@uwo.ca)
 * @param <T> Generic type
 */

public class DLinkedNode<T> {
	
	private T dataItem;				  // Stores data of node
	private double priority;	     //  Stores priority of node
	private DLinkedNode<T> next;	//   Stores next node 
	private DLinkedNode<T> prev;	// 	 Stores previous node
	
	/**
	 * Constructor method for a double linked node. 
	 * @param data  Stores data of node
	 * @param prio	Stores priority of node
	 */
	
	public DLinkedNode (T data, double prio) {
		dataItem = data;
		priority = prio;
	}
	
	/**
	 * Constructor method for a double linked node. 
	 * Sets dataItem to null and priority to 0.
	 */
	
	public DLinkedNode() {
		dataItem = null;
		priority = 0;
	}
	
	/**
	 * Getter method that returns the priority of the node in queue.
	 * @return	priority of node as a double.
	 */
	
	public double getPriority() {
		return priority;
	}
	
	/**
	 * Getter method that returns the dataItem of node.
	 * @return dataItem of node as type T.
	 */
	
	public T getDataItem() {
		return dataItem;
	}
	
	/**
	 * Getter method that returns the next node of implicit node.
	 * @return	Next node in queue of implicit node.
	 */
	
	public DLinkedNode<T> getNext() {
		return next;
	}
	
	/**
	 * Getter method that returns the previous node of implicit node.
	 * @return	Previous node in queue of implicit node.
	 */
	
	public DLinkedNode<T> getPrev() {
		return prev;
	}
	
	/**
	 * Setter method that sets the data of implicit node.
	 * @param data 	New value of data.
	 */
	
	public void setData (T data) {
		dataItem = data;
	}
	
	/**
	 * Setter method that sets the next node of implicit node.
	 * @param node  sets new next node in queue of implicit node.
	 */
	
	public void setNext(DLinkedNode<T> node) {
		next = node;
	}
	
	/**
	 * Setter method that sets the previous node of implicit node.
	 * @param node  sets new prev node in queue of implicit node.
	 */
	
	public void setPrev(DLinkedNode<T> node) {
		prev = node;
	}
	
	/**
	 * Setter method that sets the new priority of implicit node.
	 * @param prio 	New value of priority.
	 */
	
	public void setPriority(double prio) {
		priority = prio;
	}
	
	
}
