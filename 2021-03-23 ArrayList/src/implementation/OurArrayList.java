package implementation;

import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OurArrayList<T> implements Iterable<T>{
	
	private static final int DEFAULT_CAPACITY = 10;
	
	private Object[] arr;
	private int capacity;
	private int size = 0;
	
	// constructors
	
	public OurArrayList() {
		super();
		arr = new Object[DEFAULT_CAPACITY];
	}

	public OurArrayList(int capacity) {
		super();
		this.capacity = Math.max(capacity, DEFAULT_CAPACITY);
		arr = new Object[this.capacity];
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
	
	public Iterator<T> iterator(){
		return new OurArrayListIterator();
	}
	
	private class OurArrayListIterator implements Iterator<T>{
		
		int current = 0;

		public boolean hasNext() {
			return current < size;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			if (!hasNext()) throw new NoSuchElementException();
			return (T)arr[current++];
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
	
	public void ensureCapacity(int cap) {
		if (cap > DEFAULT_CAPACITY) {
			int newCapacity = Math.max(size,  cap);
			Object[] substitutor = new Object[newCapacity];
			System.arraycopy(arr, 0, substitutor, 0, size);
			arr = substitutor;
			capacity = newCapacity;
		}
	}
	
	public void trimToSize() {
		ensureCapacity(size);
	}
	
	// list iterators
	
	public ListIterator<T> listIterator(int index){
		return new OurListIterator(index);
	}
	
	public ListIterator<T> listIterator(){
		return listIterator(0);
	}
	
	private class OurListIterator implements ListIterator<T>{
		
		private boolean moved = false;
		private boolean changed = false;
		private boolean goingForward = true;
		private int cursor;
	
		@SuppressWarnings("unused")
		public OurListIterator() {
			super();
			this.cursor = 0;
		}

		public OurListIterator(int cursor) {
			super();
			this.cursor = cursor;
		}

		@Override
		public boolean hasNext() {
			return cursor == size;
		}

		@Override
		public boolean hasPrevious() {
			return cursor == 0;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			if (!hasNext()) throw new NoSuchElementException();
			moved = true;
			changed = false;
			goingForward = true;
			return (T) arr[cursor++];
		}

		@Override
		public int nextIndex() {
			return cursor;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T previous() {
			if (!hasPrevious()) throw new NoSuchElementException();
			moved = true;
			changed = false;
			goingForward = false;
			return (T) arr[--cursor];
		}

		@Override
		public int previousIndex() {
			return cursor;
		}

		@Override
		public void add(T data) {
			if (moved) {
				OurArrayList.this.add(goingForward ? cursor - 1 : cursor + 1, data);
				changed = true;
				if (goingForward) cursor++;
			}
			else throw new  IllegalStateException();
		}
		
		@Override
		public void remove() {
			if (moved && !changed) {
				OurArrayList.this.remove(goingForward ? cursor - 1 : cursor + 1);
				changed = true;
				if (goingForward) cursor--;
			}
			else throw new  IllegalStateException();
		}

		@Override
		public void set(T data) {
			if (moved && !changed) arr[goingForward ? cursor - 1 : cursor + 1] = data;
			else throw new  IllegalStateException();
		}
		
	
		
		
	}
}
