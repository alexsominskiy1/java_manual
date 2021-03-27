package model;

import java.util.Iterator;
import java.util.ListIterator;

public class OurArrayList<T> implements Iterable<T> {
	
private static final int DEFAULT_CAPACITY = 10;
	
	private Object[] arr;
	private int capacity = DEFAULT_CAPACITY;
	private int size = 0;
	
	// exceptions

	private void throwOutOfBounds(String msg, int index) {
		throw new IndexOutOfBoundsException(msg + "; index: "+ index + "; size:" + size);
	}
	
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
		return new OurListIterator<T>(this, index, ListIteratorRestrictions.IMMUTABLE);
	}
	
	public ListIterator<T> listIteratorUnmodifiable(int index){
		return new OurListIterator<T>(this, index, ListIteratorRestrictions.UNMODIFIABLE);
	}
	
	public ListIterator<T> listIterator(int index){
		if (index < 0 || index > size) throwOutOfBounds("list iterator", index);
		return new OurListIterator<T>(this, index);
	}
	
	public ListIterator<T> listIterator(){
		return new OurListIterator<T>(this);
	}
}
