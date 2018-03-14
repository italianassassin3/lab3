package indexList;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import linkedLists.LinkedList;
import linkedLists.Node;

public class LLIndexList<E> implements IndexList<E> {
	private LinkedList<E> internalLL;  

	/**
		Creates an empty instance of a list. 
	 **/ 
	public LLIndexList(LinkedList<E> theList) 
	{ 
		internalLL = theList;
	}

	/**
		Determines the size of the list. 
		@return size of the list – number of elements. 
	 **/
	public int size() { 
		return internalLL.length(); 
	} 

	/** 	
		Determines if the list is empty. 	
		@return true if empty, false if not. 
	 **/ 
	public boolean isEmpty() { 
		return this.size() == 0; 
	} 
	
	/**
	Private method to access the data node at the
    position given in the internal linked list. 
    If the list is not empty, he first data node 
    has position 0, the following data node (if any)
    has position 1, and so on. 
    @param posIndex the index of the position being 
           accessed. 
    @return reference to the data node in the given
           position of the internal linked list. 
	 **/
	private Node<E> getDataNodeAtPosition(int posIndex)
	{ 
		
		Node<E> target = internalLL.getFirstNode(); 
		for (int p=1; p<= posIndex; p++)
			target = internalLL.getNodeAfter(target); 
		return target; 
	} 

	/** 
		Adds a new element to the list. 
		@param i the index of the position where the 
			new element is to be inserted. 
		@param e the new element to insert. 
		@throws IndexOutOfBoundsException if the index
			i does not corresponds to the index 
			of a valid position to insert…
	 **/ 
	public void add(int index, E e) 
	throws IndexOutOfBoundsException 
	{
		if (index < 0  ||  index > internalLL.length()) 
			    throw new IndexOutOfBoundsException("add: " 
				+ "index=" + index + " is out of bounds. size = " + 
				internalLL.length());

     	Node<E> nuevoNodo = internalLL.createNewNode();
     	nuevoNodo.setElement(e); 
		if (index==0) 
       		internalLL.addFirstNode(nuevoNodo); 
		else { 
			Node<E> nodoPrevio = 
				getDataNodeAtPosition(index-1); 
			internalLL.addNodeAfter(nodoPrevio, 
				nuevoNodo); 
		}
	}

	public void add(E e) { 
     	Node<E> nuevoNodo = internalLL.createNewNode();
     	nuevoNodo.setElement(e); 
		Node<E> nodoPrevio = 
			internalLL.getLastNode(); 
		internalLL.addNodeAfter(nodoPrevio, 
			nuevoNodo); 
    	
	}
	
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0  ||  index > internalLL.length() -1) 
		    throw new IndexOutOfBoundsException("get: " 
			+ "index=" + index + " is out of bounds.");

		Node<E> targetINode = this.getDataNodeAtPosition(index);
		return targetINode.getElement(); 
	}

	public E remove(int index) throws IndexOutOfBoundsException {
		if (index < 0  ||  index > internalLL.length() -1) 
		    throw new IndexOutOfBoundsException("remove: " 
			+ "index=" + index + " is out of bounds.");

		Node<E> ntr = this.getDataNodeAtPosition(index); 
		E etr = ntr.getElement(); 
		this.internalLL.removeNode(ntr); 

		return etr;
	}

	public E set(int index, E e) throws IndexOutOfBoundsException {
		if (index < 0  ||  index > internalLL.length()-1) 
		    throw new IndexOutOfBoundsException("set: " 
			+ "index=" + index + " is out of bounds.");

		Node<E> ntc = this.getDataNodeAtPosition(index); 
		E etr = ntc.getElement(); 
		ntc.setElement(e); 
		return etr; 
	}
	
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] array) { 
		if (array.length <this.internalLL.length()) { 
			array = (T[]) Array.newInstance(array.getClass().getComponentType(), this.internalLL.length());
		} 
		else if (array.length > this.internalLL.length()){//There are more empty spaces on the array than there are elements
			for (int j=this.internalLL.length(); j< array.length; j++){
				array[j] = null;//We set the unused ones to null;
			}
		}
		
		for (int i=0; i < this.internalLL.length(); i++) {
			array[i] = (T) this.getDataNodeAtPosition(i).getElement();//The getDataNodeAtPosition(i) method WILL NOT!! return an element
																	  //it will return a NODE, to solve this, .getElement() after the find is needed
		}
		return array;	
	}

	public Object[] toArray() { 
		Object[] array = new Object[size()]; 
		for (int i=0; i < this.internalLL.length(); i++) {
			array[i] = this.getDataNodeAtPosition(i).getElement();//The getDataNodeAtPosition(i) method WILL NOT!! return an element
			  													  //it will return a NODE, to solve this, .getElement() after the find is needed
		}
		return array;	

	}

	
	
}
