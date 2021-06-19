package application;

import java.util.Arrays;
import java.util.Random;

import arrays.ArraysSort;

public class ArraysSortApp {
	
	private static final int SIZE = 30;
	private static final int BOUND = 10;
	private static Random gen = new Random();
	
	public static void main(String[] args) {
				
		System.out.println("Initial");
		int[] arr = new int[SIZE];
		for (int i = 0; i < SIZE; i++) arr[i] = gen.nextInt(BOUND);
		System.out.println(Arrays.toString(arr));
		
		System.out.println("Select");
		int[] arrSelect = Arrays.copyOf(arr, SIZE);
		ArraysSort.selectSort(arrSelect, 3, SIZE - 3);
		System.out.println(Arrays.toString(arrSelect));
		
		System.out.println("Bubble");
		int[] arrBubble = Arrays.copyOf(arr, SIZE);
		ArraysSort.bubbleSort(arrBubble, 5, SIZE - 5);
		System.out.println(Arrays.toString(arrBubble));
		
		System.out.println("Quick");
		int[] arrQuick = Arrays.copyOf(arr, SIZE);
		ArraysSort.quickSort(arrQuick, 7, SIZE - 7);
		System.out.println(Arrays.toString(arrQuick));		
	}
}
