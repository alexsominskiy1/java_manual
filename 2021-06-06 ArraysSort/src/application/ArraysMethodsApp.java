package application;

import java.util.Arrays;
import java.util.Random;

import arrays.ArraysMethods;
import arrays.ArraysSort;

public class ArraysMethodsApp {
	
	private static final int SIZE = 30;
	private static final int BOUND = 10;
	private static Random gen = new Random();
	
	public static void main(String[] args) {

		System.out.println("Search array");
		Integer[] arr = new Integer[SIZE];
		for (int i = 0; i < SIZE; i++) arr[i] = gen.nextInt(BOUND);
		Arrays.sort(arr, 0, SIZE);
		System.out.println(Arrays.toString(arr));
		
		Integer key = 1;
		
		int position = ArraysMethods.binarySearch(arr, key);
		System.out.println("key: "+key+"; position: "+position + 
				(position < 0 ? "; insert before: " + (~position) : ""));
		
		Integer[] arr1 = {1,2,3,4};
		Integer[] arr2 = {1,2,3,5};
		
		System.out.println();
		System.out.println("mismatch: "+ ArraysMethods.mismatch(arr1, arr2));
		System.out.println("compare: "+ ArraysMethods.compare(arr1, arr2));
		System.out.println("equals: "+ ArraysMethods.equals(arr1, arr2));
	}
}
