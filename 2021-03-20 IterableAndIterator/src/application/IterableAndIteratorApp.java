package application;

import java.util.Iterator;

import model.IterableArray;

public class IterableAndIteratorApp {

	public static void main(String[] args) {
		
		int[] arr = {10, -20, 30, -40, 50};
		
		IterableArray iterableArray = new IterableArray(arr);
		
		/*
		Iterator<Integer> it = iterableArray.iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + "  ");
		}
		*/
		
		for(Integer i : iterableArray) System.out.print(i + "  ");
		
		System.out.println();
	}
}
