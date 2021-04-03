package application;

import model.LinkedListOperations;
import model.OurLinkedListTrick;

public class LinkedListApp {

	public static void main(String[] args) {
		
		LinkedListOperations<Integer> ourLinkedList = new LinkedListOperations<>();
		
		ourLinkedList.addLast(30);
		ourLinkedList.addLast(40);
		ourLinkedList.addFirst(20);
		ourLinkedList.addFirst(10);
		ourLinkedList.addFirst(0);
		
		for (Integer i : ourLinkedList)System.out.print(i + "  ");
		System.out.println();
		
		ourLinkedList.addBefore(2, 666);
		
		for (Integer i : ourLinkedList)System.out.print(i + "  ");
		System.out.println();
		
		ourLinkedList.set(2, 777);
		
		System.out.println(ourLinkedList.indexOf(20));
		System.out.println(ourLinkedList.contains(25));

		for (Integer i : ourLinkedList)System.out.print(i + "  ");
		System.out.println();
		
		System.out.println(ourLinkedList.pollFirst());
		System.out.println(ourLinkedList.pollLast());
		System.out.println(ourLinkedList.remove(1));
		
		for (Integer i : ourLinkedList)System.out.print(i + "  ");
		System.out.println();
		
		System.out.println(ourLinkedList.peekFirst());
		System.out.println(ourLinkedList.peekLast());

	}

}
