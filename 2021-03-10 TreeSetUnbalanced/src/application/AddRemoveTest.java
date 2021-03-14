package application;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class AddRemoveTest {

	private static final int NUM_ELEMENTS = 10;

	public static void main(String[] args) {
		
		TreeSetUnbalanced<Integer> tree = new TreeSetUnbalanced<>();
		
		ArrayList<Integer> toRemove = new ArrayList<>();
		int element = 0;
		for (int i = 0; i < NUM_ELEMENTS; i++) {
			element = ThreadLocalRandom.current().nextInt(100);
			if(tree.add(element)) {
				toRemove.add(element);
				System.out.println(element + " added");
			}
			else System.out.println(element +" not added");
		}
		
		System.out.println();
		for (Integer i : tree)System.out.print(i + " ");
		
		System.out.println();		
		System.out.println("height: " + tree.height());
		System.out.println("size: " + tree.size());
		
		System.out.println();
		toRemove.add(NUM_ELEMENTS/2, -1);
		for (Integer i : toRemove) {
			if(tree.remove(i))System.out.println(i + " removed");
			else System.out.println(i + " not removed");
		}
	
		System.out.println();
		System.out.println("height: " + tree.height());
		System.out.println("size: " + tree.size());	
	}
}
