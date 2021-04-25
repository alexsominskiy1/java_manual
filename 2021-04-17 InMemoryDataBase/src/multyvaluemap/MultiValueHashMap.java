package multyvaluemap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class MultiValueHashMap <K extends Comparable<? super K>, V extends Comparable<? super V>> extends AbstractMultiValueMap<K,V>{

	public MultiValueHashMap() {
		super(new HashMap<K, TreeSet<V>>());
	}

	protected Iterable<V> getSortedByKey(List<K> keys){
		Collections.sort(keys);
		return getByKeys(keys);
	}
	
	@Override
	public Iterable<V> getInRange(K min, K max) {
		ArrayList<K> keys = new ArrayList<>();
		for(K key : map.keySet()) if (key.compareTo(min) >= 0 && key.compareTo(max) < 0)keys.add(key);
		return getSortedByKey(keys);
	}

	@Override
	public Iterable<V> getSorted() {
		ArrayList<K> keys = new ArrayList<>(map.keySet());
		return getSortedByKey(keys);
	}

	@Override
	public Iterable<V> getMin() {
		return map.get(Collections.min(map.keySet()));
	}

	@Override
	public Iterable<V> getMax() {
		return map.get(Collections.max(map.keySet()));
	}

}
