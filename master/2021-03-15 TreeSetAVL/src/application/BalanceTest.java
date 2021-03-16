package application;

import java.util.concurrent.ThreadLocalRandom;

public class BalanceTest {

	private static final int NUM_ELEMENTS = 10000000;
	public static void main(String[] args) {

		TreeSetAVL<Integer> unbalanced = new TreeSetAVL<>();
		
		for (int i = 0; i < NUM_ELEMENTS; i++) unbalanced.add(ThreadLocalRandom.current().nextInt());
		
		System.out.println("Unbalanced:");
		System.out.println("height: " + unbalanced.countHeight());
		System.out.println("size: " + unbalanced.size());

		TreeSetAVL<Integer> balanced = unbalanced.bruteForceBalance();
				
		System.out.println("\nBalanced:");
		System.out.println("height: " + balanced.countHeight());
		System.out.println("size: " + balanced.size());
	}
}
