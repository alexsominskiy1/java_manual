package application;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class AddRemoveTest {

	private static final int NUM_ELEMENTS = 10000000;
	private static final int NUM_ELEMENTS_TO_REMAIN = 1000;

	public static void main(String[] args) {
		
		TreeSetAVL<Integer> tree = new TreeSetAVL<>();
		
		ArrayList<Integer> toRemove = new ArrayList<>();
		int element = 0;
		for (int i = 0; i < NUM_ELEMENTS; i++) {
			element = ThreadLocalRandom.current().nextInt();
			if(tree.add(element)) toRemove.add(element);
		}
		
		System.out.println();		
		System.out.println("height: " + tree.countHeight());
		System.out.println("size: " + tree.size());
		
		for(int i = 0; i < toRemove.size()-NUM_ELEMENTS_TO_REMAIN; i++)tree.remove(toRemove.get(i));
	
		System.out.println();
		System.out.println("height: " + tree.countHeight());
		System.out.println("size: " + tree.size());	
	}
}
