package model;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OurListIterator<T> implements ListIterator<T>{
	
	private OurArrayList<T> list;
	
	private int cursor = 0;
	private int last;
	
	private boolean unmodifiable = false;
	private boolean immutable = false;
	
	private boolean illegalState = true;

	public OurListIterator(OurArrayList<T> list) {
		super();
		this.list = list;
	}
	
	public OurListIterator(OurArrayList<T> list, int cursor) {
		super();
		this.list = list;
		this.cursor = cursor;
	}

	public OurListIterator(OurArrayList<T> list, int cursor, ListIteratorRestrictions restrictions) {
		super();
		this.list = list;
		this.cursor = cursor;
		this.unmodifiable = true;
		this.immutable = restrictions == ListIteratorRestrictions.IMMUTABLE;
	}

	@Override
	public boolean hasNext() {
		return cursor < list.size();
	}

	@Override
	public T next() {
		if(!hasNext()) throw new NoSuchElementException("list iterator: next");
		last = cursor++;
		illegalState = false;
		return (T) list.get(last);
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
		return (T) list.get(last);
	}

	@Override
	public int previousIndex() {
		return cursor - 1;
	}
	
	@Override
	public void add(T data) {
		if (unmodifiable) throw new UnsupportedOperationException("list iterator: add");
		list.add(cursor++, data);
		illegalState = true;
	}

	@Override
	public void remove() {
		checkRestrictions(unmodifiable, "remove");
		list.remove(last);
		if(cursor > last) cursor--;
		illegalState = true;
	}

	@Override
	public void set(T data) {
		checkRestrictions(immutable, "set");
		list.set(last, data);		
	}

	// exceptions to throw

	private void checkRestrictions(boolean restriction, String msg) {
		if(restriction) throw new UnsupportedOperationException("list iterator: " + msg);
		if (illegalState) throw new IllegalStateException("list iterator: " + msg);
	}
}
