package implementation;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OurArrayList<T> implements Iterable<T>{
	
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
	
	public void ensureCapacity(int cap) {
		if (cap > DEFAULT_CAPACITY) {
			int newCapacity = Math.max(size,  cap);
			Object[] substitutor = new Object[newCapacity];
			System.arraycopy(arr, 0, substitutor, 0, size);
			arr = substitutor;
			capacity = newCapacity;
		}
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
	
	public int capacity() {
		return capacity;
	}
	
	public int indexOf(T data) {
		if (data == null) {
			for (int i=0; i < size; i++) {
				if (arr[i] == null) return i;
			}
		}
		else {
			for (int i=0; i < size; i++) {
				if (data.equals(arr[i])) return i;
			}
		}
		return -1;
	}
	
	public boolean contains(T data) {
		return indexOf(data) >= 0;
	}
	
	// iterator
	
		public Iterator<T> iterator(){
			return listIterator();
		}
		
		// list iterators
		
		public ListIterator<T> listIterator(int index){
			if (index < 0 || index > size) throwOutOfBounds("list iterator", index);
			return new OurListIterator(index);
		}
		
		public ListIterator<T> listIterator(){
			return new OurListIterator();
		}

	
	private class OurListIterator implements ListIterator<T>{
		
		private int cursor = 0;
		private int last;
		
		private boolean illegalState = true;

		private OurListIterator() {
			super();
		}
		
		private OurListIterator(int cursor) {
			super();
			this.cursor = cursor;
		}

		@Override
		public boolean hasNext() {
			return cursor < OurArrayList.this.size();
		}

		@Override
		public T next() {
			if(!hasNext()) throw new NoSuchElementException("list iterator: next");
			last = cursor++;
			illegalState = false;
			return OurArrayList.this.get(last);
		}

		@Override
		public int nextIndex() {			
			return cursor;
		}
		
		@Override
		public boolean hasPrevious() {
			return cursor > 0;
		}

		@Override
		public T previous() {
			if(!hasPrevious()) throw new NoSuchElementException("list iterator: previous");
			last = --cursor;
			illegalState = false;
			return OurArrayList.this.get(last);
		}

		@Override
		public int previousIndex() {
			return cursor - 1;
		}
		
		@Override
		public void add(T data) {
			OurArrayList.this.add(cursor++, data);
			illegalState = true;
		}

		@Override
		public void remove() {
			if (illegalState) throw new IllegalStateException("list iterator: remove");
			OurArrayList.this.remove(last);
			if(cursor > last) cursor--;
			illegalState = true;
		}

		@Override
		public void set(T data) {
			if (illegalState) throw new IllegalStateException("list iterator: set");
			OurArrayList.this.set(last, data);		
		}
	}
	
	// toString 
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < size; i++) stringBuilder.append(arr[i] + ", ");
		return "[" + stringBuilder.toString().substring(0, stringBuilder.length() -2) + "]";
	}
	
	// additions
	
	public void trimToSize() {
		ensureCapacity(size);
	}
}
