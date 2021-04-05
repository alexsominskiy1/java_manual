package model;

import java.util.NoSuchElementException;

public class Stack<T> {
	
	private static final int DEFAULT_CAPACITY = 3;
	
	private Node head = null;
	private int size = 0;
	private int capacity = DEFAULT_CAPACITY;
	
	private class Node{
		private T data;
		private Node next;
		
		public Node(T data, Node next) {
			super();
			this.data = data;
			this.next = next;
		}	
	}
	
	public Stack() {
		super();
	}

	public Stack(int capacity) {
		super();
		this.capacity = Math.max(capacity,  DEFAULT_CAPACITY);
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public boolean isFull() {
		return size == capacity;
	}

	public boolean add(T data) {
		if (isFull()) throw new IllegalStateException("stack is full");
		head = new Node(data, head);
		size++;
		return true;
	}
	
	public boolean offer(T data) {
		try {
			add(data);
			return true;
		}catch (IllegalStateException e) {
			return false;
		}
	}
	
	public T element() {
		if (isEmpty()) throw new NoSuchElementException("stack is empty");
		return head.data;
	}
	
	public T peek() {
		try {
			return element();
		}catch (IllegalStateException e) {
			return null;
		}
	}
	
	public T remove() {
		if (isEmpty()) throw new NoSuchElementException("stack is empty");
		T pocket = head.data;
		head = head.next;
		size--;
		return pocket;
	}
	
	public T poll() {
		try {
			return remove();
		}catch(NoSuchElementException e) {
			return null;
		}
	}
}
