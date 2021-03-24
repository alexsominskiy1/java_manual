package application;

import java.util.ArrayList;
import java.util.ListIterator;

import model.OurArrayList;

public class ListIteratorApp {

	public static void main(String[] args) {
		
		OurArrayList<Integer> ourArrayList = new OurArrayList<>();
		//ArrayList<Integer> ourArrayList = new ArrayList<>();
		for (int i = 1; i < 10; i++)ourArrayList.add(i*10);
		
		for (int i=0; i<ourArrayList.size(); i++)System.out.print(ourArrayList.get(i) + "  ");
		System.out.println("\n");
		
		ListIterator<Integer> lit = ourArrayList.listIterator(3);
		
		System.out.println(lit.next());
		System.out.println(lit.next());
		
		System.out.println("set");
		lit.set(999);
		show(ourArrayList);
		
		System.out.println(lit.next());
	
		System.out.println("remove");
		lit.remove();
		show(ourArrayList);
		
		System.out.println(lit.next());
		
		System.out.println("add");
		lit.add(111);
		lit.add(222);
		show(ourArrayList);
		
		System.out.println(lit.next());
			
		System.out.println("\nreverse\n");
		System.out.println(lit.previous());
		System.out.println(lit.previous());
		
		System.out.println("set");
		lit.set(888);
		show(ourArrayList);
		
		System.out.println(lit.previous());
		
		System.out.println("remove");
		lit.remove();
		show(ourArrayList);
		
		System.out.println("add");
		lit.add(333);
		show(ourArrayList);

		System.out.println("\nreverse\n");
		System.out.println(lit.next());
		System.out.println(lit.next());
		
		
		System.out.println("set");
		lit.set(777);
		show(ourArrayList);
		
		System.out.println(lit.next());

	}
	
	private static void show(OurArrayList<?> ourArrayList) {
		for (int i=0; i<ourArrayList.size(); i++)System.out.print(ourArrayList.get(i) + "  ");
		System.out.println();
	}

}
