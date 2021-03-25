package model;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OurArrayList<T> implements Iterable<T> {
	
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
	
	// ensure capacity
	
	public void ensureCapacity(int cap) {

		if (cap > DEFAULT_CAPACITY) {
			int newCapacity = Math.max(size,  cap);
			Object[] substitutor = new Object[newCapacity];
			System.arraycopy(arr, 0, substitutor, 0, size);
			arr = substitutor;
			capacity = newCapacity;
		}
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
	
	// iterator
	
	public Iterator<T> iterator(){
		return listIterator();
	}
	
	// list iterators
	
	public ListIterator<T> listIteratorImmutable(int index){
		return new OurListIterator(index, true, true);
	}
	
	public ListIterator<T> listIteratorUnmodifiable(int index){
		return new OurListIterator(index, true, false);
	}
	
	public ListIterator<T> listIterator(int index){
		return new OurListIterator(index, false, false);
	}
	
	public ListIterator<T> listIterator(){
		return new OurListIterator(0, false, false);
	}
	
	private class OurListIterator implements ListIterator<T>{
		
		private int start;
		private int cursor = -1;
		private int last = -1;
		
		private boolean unchangeable = true;
		private boolean unmodifiable = false;
		private boolean immutable = false;

		public OurListIterator(int start, boolean unmodifiable, boolean immutable) {
			super();
			this.start = start;
			this.unmodifiable = unmodifiable || immutable;
			this.immutable = immutable;
		}

		@Override
		public boolean hasNext() {
			return cursor < size;
		}

		@Override
		public boolean hasPrevious() {
			return cursor > 0;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			throwNoSuchElement(!hasNext(), "next");
			if (cursor < 0) cursor = start;
			last = cursor++;
			unchangeable = false;
			return (T) arr[last];
		}

		@Override
		public int nextIndex() {			
			return cursor;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T previous() {
			throwNoSuchElement(!hasPrevious(), "previous");
			if (cursor < 0) cursor = start;
			last = --cursor;
			unchangeable = false;
			return (T) arr[last];
		}

		@Override
		public int previousIndex() {
			return cursor - 1;
		}
		

		@Override
		public void add(T data) {
			throwUnsupportedOperation(unmodifiable, "add");
			OurArrayList.this.add(cursor++, data);
			unchangeable = true;
		}


		@Override
		public void remove() {
			throwUnsupportedOperation(unmodifiable, "remove");
			throwIllegalState(unchangeable, "remove");
			OurArrayList.this.remove(last);
			cursor--;
			unchangeable = true;
		}

		@Override
		public void set(T data) {
			throwUnsupportedOperation(immutable, "set");
			throwIllegalState(unchangeable, "set");
			arr[last] = data;		
		}
	}
	
	// exceptions

	private void throwOutOfBounds(String msg, int index) {
		throw new IndexOutOfBoundsException(msg + "; index: "+ index + "; size:" + size);
	}

	private void throwNoSuchElement(boolean noSuch, String msg) {
		if(noSuch) throw new NoSuchElementException("list iterator: " + msg);
	}

	private void throwIllegalState(boolean illegal, String msg) {
		if(illegal) throw new IllegalStateException("list iterator: " + msg);
	}

	private void throwUnsupportedOperation(boolean unsupported, String msg) {
		if(unsupported) throw new UnsupportedOperationException("list iterator: " + msg);
	}
		
}
