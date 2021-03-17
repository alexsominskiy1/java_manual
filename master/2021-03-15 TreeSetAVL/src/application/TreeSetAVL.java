package application;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeSetAVL<T> implements Iterable<T>{

	// Node

	private class Node{

		T data;
		Node left;
		Node right;
		
		byte height = 1;

		Node(T data){this.data = data;}
	}
	
	// data members

	private Node root = null;
	private int size = 0;
	private Comparator<T> comparator;

	// constructors

	@SuppressWarnings("unchecked")
	public TreeSetAVL() {
		try {
			this.comparator = (T t1, T t2) -> ((Comparable<T>)t1).compareTo(t2);
		} catch (ClassCastException e) {
			throw new TypeNotComparableTreeSetException();
		}
	}
	
	public TreeSetAVL(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
	// Exceptions

	@SuppressWarnings("serial")
	private static class DuplicateDataTreeSetException extends RuntimeException{}
	@SuppressWarnings("serial")
	private static class ElementNotFoundTreeSetException extends RuntimeException{}
	@SuppressWarnings("serial")
	private static class TypeNotComparableTreeSetException extends RuntimeException{}
	@SuppressWarnings("serial")
	private static class IterationFailedTreeSetException extends RuntimeException{}
	@SuppressWarnings("serial")
	private static class NullNodeHeightTreeSetException extends RuntimeException{}
	@SuppressWarnings("serial")
	private static class BalanceStateTreeSetException extends RuntimeException{}
	@SuppressWarnings("serial")
	private static class AVLBalanceFailException extends RuntimeException{}
		
	// and and remove

	public boolean add(T data) {
		//System.out.println("to add: "+data);
		try {
			root = subTreeAdd(root, data);
			size++;
			return true;
		}catch(DuplicateDataTreeSetException e) {
			return false;
		}
	}

	private Node subTreeAdd(Node rootNode, T data) {
		if (rootNode == null) return new Node(data);

		int comparision = comparator.compare(data, rootNode.data);
		if (comparision > 0) rootNode.right = subTreeAdd(rootNode.right, data);
		else if (comparision < 0) rootNode.left = subTreeAdd(rootNode.left, data);
		else throw new DuplicateDataTreeSetException();
		
		Node result = balanceNode(rootNode);
		
		if (Math.abs(getBalance(result)) > 1) System.out.println("alert");;
		return result;
	}

	public boolean remove(T data) {
		try {
			root = subTreeRemove(root, data, false);
			size--;
			return true;
		}catch(ElementNotFoundTreeSetException e) {
			return false;
		}
	}

	private Node subTreeRemove(Node rootNode, T data, boolean alreadyFound) {

		if (rootNode == null) {
			if (alreadyFound) return null;
			else throw new ElementNotFoundTreeSetException(); 
		} 

		Node tmp = rootNode;
		
		int comparision = comparator.compare(data, rootNode.data);
		if (comparision > 0) rootNode.right = subTreeRemove(rootNode.right, data, alreadyFound);
		else if (comparision < 0) rootNode.left = subTreeRemove(rootNode.left, data, alreadyFound);
		
		// found !
		
		else
		{  
			// node with only one child or no child  
			if ((rootNode.left == null) || (rootNode.right == null)) {  
				tmp = rootNode.left != null ? rootNode.left : rootNode.right;   
			} 

			// two children
			else
			{  
				tmp = rootNode;  
				rootNode.data = getLeftmost(rootNode.right).data;  
				rootNode.right = subTreeRemove(rootNode.right, rootNode.data, true);  
			}
		}
		
		return balanceNode(tmp);
	}
	
	private Node getLeftmost(Node node) {
		while(node.left != null) node = node.left;
		return node;
	}

	// contains

	public boolean contains(T data) {
		return subTreeContains(root, data);
	}

	private boolean subTreeContains(Node rootNode, T data) {
		if (rootNode == null) return false;
		
		int comparision = comparator.compare(data, rootNode.data);
		if (comparision > 0) return subTreeContains(rootNode.right, data);
		else if (comparision < 0) return subTreeContains(rootNode.left, data);
		else return true;
	}

	// iterator

	public Iterator<T> iterator(){
		return new SubTreeIterator(root);
	}

	private class SubTreeIterator implements Iterator<T>{

		Node rootNode;
		SubTreeIterator leftIterator;
		SubTreeIterator rightIterator;

		boolean leftIteratorFinished = true;
		boolean rootNodeReturned = true;
		boolean rightIteratorFinished = true;

		private SubTreeIterator (Node rootNode){

			if (rootNode != null) {
				this.rootNode = rootNode;
				this.rootNodeReturned = false;
				if (rootNode.left != null) {
					this.leftIterator = new SubTreeIterator(rootNode.left);
					this.leftIteratorFinished = false;
				}
				if (rootNode.right != null) {
					this.rightIterator = new SubTreeIterator(rootNode.right);
					this.rightIteratorFinished = false;
				}
			}
		}

		@Override
		public boolean hasNext() {
			return !(leftIteratorFinished && rootNodeReturned && rightIteratorFinished);
		}

		@Override
		public T next() {
			if (!hasNext()) throw new NoSuchElementException();

			if(!leftIteratorFinished) {
				T result = leftIterator.next();
				if (!leftIterator.hasNext()) leftIteratorFinished = true;
				return result;
			}

			if(!rootNodeReturned) {
				T result = rootNode.data;
				rootNodeReturned = true;
				return result;
			}

			if(!rightIteratorFinished) {
				T result = rightIterator.next();
				if (!rightIterator.hasNext()) rightIteratorFinished = true;
				return result;
			}

			throw new IterationFailedTreeSetException();
		}

	}
	
	// AVL balance
	
	private enum State {
		BALANCED, LEFT_LEFT, LEFT_RIGHT, RIGHT_LEFT, RIGHT_RIGHT
	}
	
	private byte getBalance(Node node) {
		return (byte) (node == null ? 0 : getHeight(node.left) - getHeight(node.right));
	}
	
	private State getState(Node node) {
		if (Math.abs(getBalance(node)) <= 1) return State.BALANCED;
		
		else if (getBalance(node) > 1 && getBalance(node.left) >= 0) return State.LEFT_LEFT;
		else if (getBalance(node) > 1 && getBalance(node.left) < 0) return State.LEFT_RIGHT;
		else if (getBalance(node) < -1 && getBalance(node.right) >= 0) return State.RIGHT_LEFT;
		else if (getBalance(node) < -1 && getBalance(node.right) < 0) return State.RIGHT_RIGHT;
		
		else throw new BalanceStateTreeSetException();	
	}
	
	private Node rightRotate(Node baseNode) { 
        Node leftSon = baseNode.left; 
        Node grandSon = leftSon.right; 
  
        // Perform rotation 
        leftSon.right = baseNode; 
        baseNode.left = grandSon; 
  
        // Update heights 
        setHeight(baseNode); 
        setHeight(leftSon); 
  
        // Return new baseNode 
        return leftSon; 
    }
	
	private Node leftRotate(Node baseNode) { 
        Node rightSon = baseNode.right; 
        Node grandSon = rightSon.left; 
  
        // Perform rotation 
        rightSon.left = baseNode; 
        baseNode.right = grandSon; 
  
        //  Update heights 
        setHeight(baseNode); 
        setHeight(rightSon); 
  
        // Return new baseNode 
        return rightSon; 
    } 
	
	private Node balanceNode(Node node) {
		
		if (node == null) return null;
	
		State state = getState(node);
		
		if (state == State.BALANCED) {
			setHeight(node);
			return node;
		}
		
		else if (state == State.LEFT_LEFT)return rightRotate(node);
		else if (state == State.LEFT_RIGHT) { 
            node.left = leftRotate(node.left); 
            return rightRotate(node); 
        }
		else if (state == State.RIGHT_LEFT) { 
            node.right = rightRotate(node.right); 
            return leftRotate(node); 
        }
		else if (state == State.RIGHT_RIGHT)return leftRotate(node);
     
		else throw new AVLBalanceFailException();	
	}
	
	// brute force balance
	
	public TreeSetAVL<T> bruteForceBalance(){
		
		ArrayList<T> alt = new ArrayList<>();
		for(T element : this) alt.add(element);
		
		TreeSetAVL<T> balanced = new TreeSetAVL<>(comparator);
		
		putArrayToTree(balanced, alt, 0, alt.size()-1);
		return balanced;
	}

	private void putArrayToTree(TreeSetAVL<T> balanced, ArrayList<T> alt, int start, int end) {
		if (end - start < 0) return;
		if (end == start) {
			balanced.add(alt.get(start));
			return;
		}
		
		int index = start + (end - start)/2;
		balanced.add(alt.get(index));
		putArrayToTree(balanced, alt, start, index-1);
		putArrayToTree(balanced, alt, index+1, end);
	}
	
	// height
	
	private byte getHeight(Node node) {
		return node == null ? 0 : node.height;
	}
	
	private void setHeight(Node node) {
		 if (node == null) throw new NullNodeHeightTreeSetException();
		 node.height = (byte) (1 + Math.max(getHeight(node.left), getHeight(node.right)));
	}

	public int countHeight() {
		return subTreeHeight(root);
	}

	private int subTreeHeight(Node node) {
		return node == null ? 0 : 1 + Math.max(subTreeHeight(node.left), subTreeHeight(node.right));
	}

	public int size() { return size; }
	
}
