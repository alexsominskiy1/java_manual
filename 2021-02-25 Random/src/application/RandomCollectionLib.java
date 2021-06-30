package application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class RandomCollectionLib {
	
	public static <T> List<T> randomList(int size, Supplier<T> supplier){
		ArrayList<T> arrayList = new ArrayList<>();
		for (int i=0; i<size; i++)arrayList.add(supplier.get());
		return arrayList;
	}
	
	public static <T> Set<T> randomSet(int size, Supplier<T> supplier){
		return randomSet(size, supplier, Integer.MAX_VALUE);
	}
	
	public static <T> Set<T> randomSet(int size, Supplier<T> supplier, int distinctInstancesNumber){
		HashSet<T> hashSet = new HashSet<>();
		for (int i=0; hashSet.size() < size && i < distinctInstancesNumber; i++)hashSet.add(supplier.get());
		return hashSet;
	}
	
	

}
