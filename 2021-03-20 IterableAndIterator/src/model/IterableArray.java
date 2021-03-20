package model;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IterableArray implements Iterable<Integer>{
	
	private int[] arr;
	
	private class ArrayIterator implements Iterator<Integer>{
		
		private int cursor = 0;

		@Override
		public boolean hasNext() {
			return cursor < arr.length;
		}

		@Override
		public Integer next() {
			if (!hasNext()) throw new NoSuchElementException();
			return arr[cursor++];
		}	
	}

	public IterableArray(int[] arr) {
		super();
		this.arr = arr;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new ArrayIterator();
	}
}
