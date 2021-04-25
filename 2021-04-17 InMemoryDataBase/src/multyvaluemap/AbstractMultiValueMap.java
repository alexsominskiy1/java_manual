package multyvaluemap;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.TreeSet;

public abstract class AbstractMultiValueMap<K extends Comparable<? super K>, V extends Comparable<? super V>> {
	
	protected Map<K, TreeSet<V>> map;
	
	public AbstractMultiValueMap(Map<K, TreeSet<V>> map) {
		super();
		this.map = map;
	}

	public Iterable<V> get(K key) {
		return map.getOrDefault(key, new TreeSet<V>());
	}
	
	public boolean add(K key, V data) {
		return map.computeIfAbsent(key, foo ->new TreeSet<V>()).add(data);
	}
	
	public boolean remove(K key, V data) {
		TreeSet<V> victim = map.get(key);
		if (victim == null) return false;
		victim.remove(data);
		if (victim.size() == 0) map.remove(key);
		return true;
	}
	
	protected Iterable<V> getAll(){
		LinkedHashSet<V> result = new LinkedHashSet<>();
		for (TreeSet<V> treeSet : map.values()) result.addAll(treeSet);
		return result;
	}
		
	public Iterable<V> getByKeys(Iterable<K> keys){
		LinkedHashSet<V> result = new LinkedHashSet<>();
		for (K key : keys) {
			TreeSet<V> treeSet = map.get(key);
			if(treeSet != null) result.addAll(treeSet);
		}
		return result;
	}
	
	public TreeSet<K> getKeys(){
		return new TreeSet<K>(map.keySet());
	}
	
	public abstract Iterable<V> getInRange(K min, K max);
	public abstract Iterable<V> getSorted();
	public abstract Iterable<V> getMin();
	public abstract Iterable<V> getMax();

}
