package application;

import java.util.concurrent.ThreadLocalRandom;

public class BalanceTest {

	private static final int NUM_ELEMENTS = 10000000;
	public static void main(String[] args) {

		TreeSetAVL<Integer> avl = new TreeSetAVL<>();
		
		for (int i = 0; i < NUM_ELEMENTS; i++) avl.add(ThreadLocalRandom.current().nextInt());
		
		System.out.println("size: " + avl.size());
		System.out.println("height: " + avl.countHeight()  + "; maxHeight: "+ maxHeight(avl.size()));		
	}
	
	public static double maxHeight(int n) {
		return  1.44*Math.log(n + 1.065)/Math.log(2.) - 0.328;
	}
}
