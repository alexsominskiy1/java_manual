package model;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OurArrayListIterator<T> implements ListIterator<T>{
	
	private OurArrayList<T> list;
	
	private int start = 0;
	private int cursor = -1;
	private int last = -1;
	
	private boolean unchangeable = true;
	private boolean unmodifiable = false;
	private boolean immutable = false;

	public OurArrayListIterator(OurArrayList<T> list) {
		super();
		this.list = list;
	}
	
	public void setStart(int start) {
		this.start = start;
	}

	public void setUnmodifiable(boolean unmodifiable) {
		this.unmodifiable = unmodifiable;
	}
	
	public void setImmutable(boolean immutable) {
		this.immutable = immutable;
	}

	@Override
	public boolean hasNext() {
		return cursor < list.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T next() {
		throwNoSuchElement(!hasNext(), "next");
		if (cursor < 0) cursor = start;
		last = cursor++;
		unchangeable = false;
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

	@SuppressWarnings("unchecked")
	@Override
	public T previous() {
		throwNoSuchElement(!hasPrevious(), "previous");
		if (cursor < 0) cursor = start;
		last = --cursor;
		unchangeable = false;
		return (T) list.get(last);
	}

	@Override
	public int previousIndex() {
		return cursor - 1;
	}
	
	@Override
	public void add(T data) {
		throwUnsupportedOperation(unmodifiable, "add");
		list.add(cursor++, data);
		unchangeable = true;
	}

	@Override
	public void remove() {
		throwUnsupportedOperation(unmodifiable, "remove");
		throwIllegalState(unchangeable, "remove");
		list.remove(last);
		cursor--;
		unchangeable = true;
	}

	@Override
	public void set(T data) {
		throwUnsupportedOperation(immutable, "set");
		throwIllegalState(unchangeable, "set");
		list.set(last, data);		
	}

	// exceptions

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
