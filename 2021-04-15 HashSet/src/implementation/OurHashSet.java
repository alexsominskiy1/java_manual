package implementation;

import java.util.ArrayList;
import java.util.Iterator;

public class OurHashSet<T> implements Iterable<T>{
	
	private static final int DEFAULT_CAPACITY = 16;
	private static double DEFAULT_LOADFACTOR = 0.75;
	
	private ArrayList<ArrayList<T>> hashSet = new ArrayList<>();

	private int capacity = DEFAULT_CAPACITY;
	private double loadFactor = DEFAULT_LOADFACTOR;
	private int mask = capacity - 1;
	private int size = 0;
	
	public OurHashSet() {
		super();
		setInitialState();
	}
	
	public OurHashSet(int capacity) {
		super();
		this.capacity = capacity;
		setInitialState();
	}
	
	public OurHashSet(double loadFactor) {
		super();
		this.loadFactor = loadFactor;
		setInitialState();
	}
	
	// utilities
	
	private void setInitialState() {
		for (int i = 0; i < capacity; i++) hashSet.add(new ArrayList<T>());
	}
	
	private ArrayList<T> getBasket(T data) {
		return hashSet.get(data.hashCode() & mask);
	}
	
	private void enlarge() {
		
		ArrayList<T> basket;
		ArrayList<T> newBasket;
		
		for (int i = 0; i < capacity; i++) {
			basket = hashSet.get(i);
			newBasket = new ArrayList<T>();
			hashSet.add(newBasket);
			for (int j = 0; j < basket.size(); j++) {
				if ((basket.get(j).hashCode() & capacity) != 0) newBasket.add(basket.remove(j));
			}
		}
		
		capacity = capacity << 1;
		mask = capacity - 1;
	}
	
	// methods
	
	public boolean add(T data) {
		
		if (data == null) return false;
		
		ArrayList<T> basket = getBasket(data);
		if (!basket.contains(data)) {
			basket.add(data);	
			if (loadFactor*capacity < ++size) enlarge();
			return true;
		}
		else return false;
	}
	
	public boolean contains(T data) {
		if (data == null) return false;
		return getBasket(data).contains(data);
	}
	
	public boolean remove(T data) {
		if (data == null) return false;
		boolean result = getBasket(data).remove(data);
		if (result) size--;
		return result;
	}
	
	public int size() {
		return size;
	}

	public void display() {
		for (int i=0;i < hashSet.size(); i++) {
			System.out.println(i+":"+hashSet.get(i));
		}
	}

	public Iterator<T> iterator(){
		return new OurHashSetIterator();
	}
	
	private class OurHashSetIterator implements Iterator<T>{
		
		Iterator<ArrayList<T>> outerIterator = hashSet.iterator();
		Iterator<T> innerIterator = outerIterator.next().iterator();
		T current;
		
		public OurHashSetIterator() {
			super();
			current = goToNext();
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			T result = current;
			current = goToNext();
			return result;
		}

		private T goToNext() {
			while(outerIterator.hasNext() || innerIterator.hasNext()) {
				if(innerIterator.hasNext()) return innerIterator.next();
				else innerIterator = outerIterator.next().iterator();
			}
			return null;
		}
	}
}
