package model;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListOperations<T> implements Iterable<T>{
	
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
	
	private Node head;
	private Node tail;
	private int size = 0;
	
	// queue
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void addFirst(T data) {
		Node node = new Node(data, head, null);
		
		if (isEmpty()) tail = node;
		else head.prev = node;
		
		head = node;
		size++;
	}
	
	public void addLast(T data) {
		
		Node node = new Node(data, null, tail);
		if (isEmpty()) head = node;
		else tail.next = node;
		
		tail = node;
		size++;
	}
	
	public T peekFirst() {
		if (isEmpty()) throw new NoSuchElementException();
		return head.data;
	}
	
	public T peekLast() {
		if (isEmpty()) throw new NoSuchElementException();
		return tail.data;
	}
	
	public T pollFirst() {
		if (isEmpty()) throw new IllegalStateException();
		
		T pocket = head.data;
		
		if (tail == head) tail = null;
		else head.next.prev = null;
		head = head.next;
		
		size--;
		return pocket;
	}
	
	public T pollLast() {
		if (isEmpty()) throw new IllegalStateException();
		
		T pocket = tail.data;
		if (tail == head) head = null;
		else tail.prev.next = null;
		
		tail = tail.prev;
		size--;
		return pocket;
	}
	
	// list
	
	private Node getNode(int index) {
		
		Node cursor;
		if (index <= size/2) {
			cursor = head;
			for (int i = 0; i < index; i++) cursor = cursor.next;
		}
		else {
			cursor = tail;
			for (int i = size -1; i > index; i--) cursor = cursor.prev;
		}
	
		return cursor;
	}
	
	public int indexOf(T data) {
		int cursor = 0;	
		for (Node current = head; current != null; current = current.next) {
			if (data == null ? current.data == null : data.equals(current.data)) return cursor;
			cursor++;
		}
		return - 1;
	}
	
	public boolean contains(T data) {
		return indexOf(data) >= 0;
	}
	
	private void addBefore(Node node, T data) {
		Node guest = new Node(data, node, node.prev);
		node.prev.next = guest;
		node.prev = guest;
		size++;
	}
	
	public void addBefore(int index, T data) {
		if (index < 0 || index > size) new IndexOutOfBoundsException();
		if (index == 0) addFirst(data);
		else if (index == size) addLast(data);
		else addBefore(getNode(index), data);
	}
	
	private T removeNode(Node node) {
		node.next.prev = node.prev;
		node.prev.next = node.next;
		size--;
		return node.data;
	}
	
	public T remove(int index) {
		if (index < 0 || index >= size) new IndexOutOfBoundsException();
		if (index == 0) return pollFirst();
		else if (index == size - 1) return pollLast();
		else return removeNode(getNode(index));
	}
	
	public T get (int index) {		
		return getNode(index).data;
	}
	
	public T set(int index, T data) {
		if (index < 0 || index >= size) new IndexOutOfBoundsException();
		Node node = getNode(index);
		T pocket = node.data;
		node.data = data;
		return pocket;
	}
	
	public Iterator<T> iterator(){
		return new OurIterator();
	}
	
	private class OurIterator implements Iterator<T>{
		
		Node cursor = head;

		@Override
		public boolean hasNext() {
			return cursor != null;
		}

		@Override
		public T next() {
			if (!hasNext()) throw new NoSuchElementException();
			T pocket = cursor.data;
			cursor = cursor.next;
			return pocket;
		}
	}
}
