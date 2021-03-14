package application;

import java.util.concurrent.ThreadLocalRandom;

public class BalanceTest {

	private static final int NUM_ELEMENTS = 10000000;

	public static void main(String[] args) {

		TreeSetRecursiveUnbalanced<Integer> unbalanced = new TreeSetRecursiveUnbalanced<>();
		
		for (int i = 0; i < NUM_ELEMENTS; i++) unbalanced.add(ThreadLocalRandom.current().nextInt());
		
		System.out.println("Unbalanced:");
		System.out.println("height: " + unbalanced.height());
		System.out.println("size: " + unbalanced.size());

		TreeSetRecursiveUnbalanced<Integer> balanced = unbalanced.balance();
				
		System.out.println("\nBalanced:");
		System.out.println("height: " + balanced.height());
		System.out.println("size: " + balanced.size());
			}
}
