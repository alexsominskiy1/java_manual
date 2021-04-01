package application;

import model.OurLinkedList;

public class LinkedListApp {

	public static void main(String[] args) {
		
		OurLinkedList<Integer> ourLinkedList = new OurLinkedList<>();
		
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

		for (Integer i : ourLinkedList)System.out.print(i + "  ");
		System.out.println();
		
		System.out.println(ourLinkedList.removeFirst());
		System.out.println(ourLinkedList.removeLast());
		System.out.println(ourLinkedList.remove(1));
		
		for (Integer i : ourLinkedList)System.out.print(i + "  ");
		System.out.println();
		
		System.out.println(ourLinkedList.peekFirst());
		System.out.println(ourLinkedList.peekLast());

	}

}
