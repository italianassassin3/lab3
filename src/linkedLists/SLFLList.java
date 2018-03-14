package linkedLists;


import java.lang.reflect.Array;
import java.util.NoSuchElementException;

import linkedLists.LinkedList;
import linkedLists.AbstractSLList.SNode;

public class SLFLList<E> extends SLList<E>
{
	private SNode<E> first, last;   // reference to the first node and to the last node
	int length; 

	public SLFLList() {       // to create an empty list instance
		first = last = null; 
		length = 0; 
	}


	public void addFirstNode(Node<E> nuevo) {
		((SNode<E>) nuevo).setNext(first); 
		first = (SNode<E>) nuevo; 
		length++; 
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		((SNode<E>) nuevo).setNext(((SNode<E>) target).getNext()); 
		((SNode<E>) target).setNext((SNode<E>) nuevo); 
		length++; 
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		if (target == first)
			this.addFirstNode(nuevo); 
		else { 
			Node<E> prevNode = getNodeBefore(target);  
			this.addNodeAfter(prevNode, nuevo); 
		}

	}

	public Node<E> getFirstNode() throws NoSuchElementException {
		if (first == null)
			throw new NoSuchElementException("getFirstNode() : linked list is empty..."); 

		// the linked list is not empty....
		return first;
	}

	public Node<E> getLastNode() throws NoSuchElementException {
		if (first == null)
			throw new NoSuchElementException("getLastNode(): Empty list."); 
		else { 
			SNode<E> curr = first; 
			while (((SNode<E>) curr).getNext() != null)
				curr = curr.getNext(); 
			return curr; 
		}
	}

	public Node<E> getNodeAfter(Node<E> target) throws NoSuchElementException {
		SNode<E> aNode = ((SNode<E>) target).getNext(); 
		if (aNode == null)  
			throw new NoSuchElementException("getNextNode(...) : target is the last node."); 
		else 
			return aNode;
	}

	public Node<E> getNodeBefore(Node<E> target) throws NoSuchElementException {
		if (target == first) 
			return null; 
		else { 
			SNode<E> prev = first; 
			while (prev != null && prev.getNext() != target) 
				prev = prev.getNext();  
			return prev; 
		}
	}

	public int length() {
		return this.length;
	}

	public void removeNode(Node<E> target) {
		if (target == first) 
			first = first.getNext(); 
		else { 
			SNode<E> prevNode = (SNode<E>) this.getNodeBefore(target); 
			prevNode.setNext(((SNode<E>) target).getNext()); 
		}
		((SNode<E>) target).clean();   // clear all references from target
		length--; 

	}

	public Node<E> createNewNode() {
		return new SNode<E>();
	}
	
	
}