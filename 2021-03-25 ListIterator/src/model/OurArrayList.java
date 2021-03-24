package model;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OurArrayList<T> {
	
private static final int DEFAULT_CAPACITY = 10;
	
	private Object[] arr;
	private int capacity = DEFAULT_CAPACITY;
	private int size = 0;
	
	// constructors
	
	public OurArrayList() {
		super();
		this.arr = new Object[DEFAULT_CAPACITY];
	}

	public OurArrayList(int capacity) {
		super();
		this.capacity = Math.max(capacity, DEFAULT_CAPACITY);
		this.arr = new Object[this.capacity];
	}
	
	// utils
	
	private void throwOutOfBounds(String msg, int index) {
		throw new IndexOutOfBoundsException(msg + "; index: "+ index + "; size:" + size);
	}
	
	// modifications
	
	public void add(int index, T data) {

		if (index < 0 || index > size) throwOutOfBounds("add", index);	
		
		if (size >= capacity) enlargeArray();
		
		System.arraycopy(arr, index, arr, index + 1, size-index);
		arr[index] = data;
		size++;
	}

	private void enlargeArray() {
		ensureCapacity(2*capacity);
	}
	
	public void add(T data) {		
		add(size, data);
	}

	@SuppressWarnings("unchecked")
	public T remove(int index) {
		if (index < 0 || index >= size) throwOutOfBounds("remove", index);
		T removed = (T)arr[index];
		System.arraycopy(arr, index+1, arr, index, size - index - 1);
		arr[size - 1] = null;
		size--;
		return removed;
	}
	
	// elements
	
	@SuppressWarnings("unchecked")
	public T get(int index) {
		if (index < 0 || index >= size) throwOutOfBounds("get", index);
		return (T)arr[index];
	}
	
	public void set(int index, T data) {
		if (index < 0 || index >= size) throwOutOfBounds("set", index);
		arr[index] = data;
	}
	
	// queries
	
	public int size() {
		return size;
	}
		
	// toString 
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < size; i++) stringBuilder.append(arr[i] + ", ");
		return "[" + stringBuilder.toString().substring(0, stringBuilder.length() -2) + "]";
	}
	
	// additions
	
	public void ensureCapacity(int cap) {

		if (cap > DEFAULT_CAPACITY) {
			int newCapacity = Math.max(size,  cap);
			Object[] substitutor = new Object[newCapacity];
			System.arraycopy(arr, 0, substitutor, 0, size);
			arr = substitutor;
			capacity = newCapacity;
		}
	}
	
	// list iterators
	
	public ListIterator<T> listIterator(int index){
		return new OurListIterator(index);
	}
	
	public ListIterator<T> listIterator(){
		return listIterator(0);
	}
	
	private class OurListIterator implements ListIterator<T>{
		
		private int start;
		private int cursor = -1;
		private int last = -1;

		public OurListIterator(int start) {
			super();
			this.start = start;
		}

		@Override
		public boolean hasNext() {
			return cursor < size;
		}

		@Override
		public boolean hasPrevious() {
			return cursor > 0;
		}

		@Override
		public T next() {
			if (cursor < 0) cursor = start;
			last = cursor++;
			return (T) arr[last];
		}

		@Override
		public int nextIndex() {			
			return cursor;
		}

		@Override
		public T previous() {
			if (cursor < 0) cursor = start;
			last = --cursor;
			return (T) arr[last];
		}

		@Override
		public int previousIndex() {
			return cursor - 1;
		}
		

		@Override
		public void add(T data) {
			OurArrayList.this.add(cursor++, data);
		}


		@Override
		public void remove() {			
			OurArrayList.this.remove(last);
			cursor--;
		}

		@Override
		public void set(T data) {		
			arr[last] = data;		
		}
		
	}
}
