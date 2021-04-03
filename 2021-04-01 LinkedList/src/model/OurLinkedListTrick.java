package model;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class OurLinkedListTrick<T> implements Iterable<T> {
	
	private class Node {
		
		private T data;
		private Node next;
		private Node prev;
		
		private Node(T data, Node next, Node prev) {
			super();
			this.data = data;
			this.next = next;
			this.prev = prev;
		}		
	}
	
	private final Node ZERO = new Node(null, null, null);
	private int size = 0;
	
	public OurLinkedListTrick() {
		super();
		ZERO.next = ZERO;
		ZERO.prev = ZERO;
	}
	
	private Node getNode(int index) {
		Node cursor = ZERO;
		if (size !=  0) {
			if (index <= size/2) {
				for (int i = 0; i <= index; i++) cursor = cursor.next;
			}
			else {
				for (int i = size; i > index; i--) cursor = cursor.prev;
			}
		}
		return cursor;
	}
	
	private void addBefore(Node node, T data) {
		Node guest = new Node(data, node, node.prev);
		node.prev.next = guest;
		node.prev = guest;
		size++;
	}
	
	public void addBefore(int index, T data) {
		if (index < 0 || index > size) throw new IndexOutOfBoundsException();
		addBefore(getNode(index), data);
	}
	
	public void addFirst(T data) {
		addBefore(ZERO.next, data);
	}
		
	public void addLast(T data) {
		addBefore(ZERO, data);
	}

	private T remove(Node node) {
		node.next.prev = node.prev;
		node.prev.next = node.next;
		size--;
		return node.data;
	}
	
	public T remove(int index) {
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
		return remove(getNode(index));
	}
	
	public T pollFirst() {
		return size == 0 ? null : remove(ZERO.next);
	}
	
	public T pollLast() {
		return size == 0 ? null : remove(ZERO.prev);
	}
	
	public T peekFirst() {
		return size == 0 ? null : ZERO.next.data;
	}
	
	public T peekLast() {
		return size == 0 ? null : ZERO.prev.data;
	}
	
	public T get(int index) {
		return getNode(index).data;
	}
	
	public void set(int index, T data) {
		getNode(index).data = data;
	}
	
	public int size() {
		return size;
	}
	
	public Iterator<T> iterator(){
		return new OurIterator();
	}
	
	private class OurIterator implements Iterator<T>{
		
		Node cursor = ZERO;

		@Override
		public boolean hasNext() {
			return cursor.next != ZERO;
		}

		@Override
		public T next() {
			if (!hasNext()) throw new NoSuchElementException();
			cursor = cursor.next;
			return cursor.data;
		}
	}
}
