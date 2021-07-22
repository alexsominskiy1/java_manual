package application;

import java.util.Random;

import implementation.OurHashSet;

public class HashSetApp {

	private static final int NUM_NUM = 20;
	private static final int MAX_NUM = 1000;
	static Random gen = new Random();
	
	public static void main(String[] args) {
		
		OurHashSet<Integer> hashSet = new OurHashSet<>();
			
		int num = 0;
		for(int i = 0; i < NUM_NUM; i++) {
			num = gen.nextInt(MAX_NUM);
			System.out.print(num + "  ");
			hashSet.add(num);
		}
		System.out.println();
		
		hashSet.add(-1);
		System.out.println(hashSet.contains(-1));
		System.out.println(hashSet.remove(-1));
		System.out.println(hashSet.size());
		System.out.println();
			
		hashSet.display();
		
		for (Integer i : hashSet)System.out.print(i + "  ");
		System.out.println();
	}

}
