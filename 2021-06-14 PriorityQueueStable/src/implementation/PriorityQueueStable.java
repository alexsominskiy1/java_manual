package implementation;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class PriorityQueueStable<T> {
	
	private class Wrap implements Comparable<Wrap>{
		private T data;
		private boolean inserted = false;
		
		public Wrap(T data) {
			super();
			this.data = data;
		}
		
		@Override
		public int compareTo(Wrap other) {
			int firstComparision = comparator.compare(other.data, data);
			return firstComparision == 0 ? Boolean.compare(inserted, other.inserted) : firstComparision;
		}
	}
	
	
	private static final int DEFAULT_CAPACITY = 3;
	
	private int capacity = DEFAULT_CAPACITY;
	@SuppressWarnings("unchecked")
	private Wrap[] queue = (Wrap[]) Array.newInstance(Wrap.class, DEFAULT_CAPACITY);
	private int size = 0;
	private Comparator<T> comparator;
	
	public PriorityQueueStable() {
		super();
		this.comparator = (a, b) -> ((Comparable<T>)a).compareTo(b);
	}
	
	public PriorityQueueStable(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}
	
	public void offer(T data) {
		
		if (size >= capacity) enlargeArray();
		
		Wrap newcomer = new Wrap(data);
		int insertionIndex = ~Arrays.binarySearch(queue, 0, size, newcomer);
		newcomer.inserted = true;
		System.arraycopy(queue, insertionIndex, queue, insertionIndex+1, size-insertionIndex);
		queue[insertionIndex] = newcomer;
		size++;
	}
	
	@SuppressWarnings("unchecked")
	private void enlargeArray() {
		int newCapacity = 2*capacity;
		Wrap[] newQueue = (Wrap[]) Array.newInstance(Wrap.class, newCapacity);
		System.arraycopy(queue, 0, newQueue, 0, size);
		capacity = newCapacity;
		queue = newQueue;
	}
	
	public T poll() {
		
		if (size == 0) return null;
		Wrap result = queue[size-1];
		queue[size-1] = null;
		size--;
		return result.data;
	}
	
	public int size() {
		return size();
	}
}
