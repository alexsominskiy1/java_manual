package multyvaluemap;

import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class MultiValueTreeMap<K extends Comparable<? super K>, V extends Comparable<? super V>> extends AbstractMultiValueMap<K,V> {
	
	public MultiValueTreeMap() {
		super(new TreeMap<K, TreeSet<V>>());
	}

	@Override
	public Iterable<V> getInRange(K min, K max) {
		LinkedHashSet<V> result = new LinkedHashSet<>();
		SortedMap<K, TreeSet<V>> subMap = ((TreeMap<K,TreeSet<V>>)map).subMap(min, max);
		for (TreeSet<V> tree : subMap.values()) result.addAll(tree);
		return result;
	}

	@Override
	public Iterable<V> getSorted() {
		return getAll();
	}

	@Override
	public Iterable<V> getMin() {
		try {
			return ((TreeMap<K,TreeSet<V>>)map).firstEntry().getValue();
		} catch (NullPointerException e) {
			throw new NoSuchElementException();
		}
	}

	@Override
	public Iterable<V> getMax() {
		try {
			return ((TreeMap<K,TreeSet<V>>)map).lastEntry().getValue();
		} catch (NullPointerException e) {
			throw new NoSuchElementException();
		}
	}
}
