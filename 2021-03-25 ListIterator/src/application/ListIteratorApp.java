package application;

import java.util.ListIterator;

import model.OurArrayList;

public class ListIteratorApp {

	public static void main(String[] args) {
		
		OurArrayList<Integer> ourArrayList = new OurArrayList<>();
		for (int i = 1; i < 10; i++)ourArrayList.add(i*10);
		
		for (int i=0; i<ourArrayList.size(); i++)System.out.print(ourArrayList.get(i) + "  ");
		System.out.println("\n");
		
		ListIterator<Integer> lit = ourArrayList.listIterator(3);
		
		System.out.println(lit.next());
		System.out.println(lit.next());
		
		System.out.println("set 999");
		lit.set(999);
		System.out.println(ourArrayList);
		
		System.out.println(lit.next());
	
		System.out.println("remove");
		lit.remove();
		System.out.println(ourArrayList);
		
		System.out.println(lit.next());
		
		System.out.println("add 111, 222");
		lit.add(111);
		lit.add(222);
		System.out.println(ourArrayList);
		
		System.out.println(lit.next());
			
		System.out.println("\nreverse\n");
		System.out.println(lit.previous());
		System.out.println(lit.previous());
		
		System.out.println("set 888");
		lit.set(888);
		System.out.println(ourArrayList);
		
		System.out.println(lit.previous());
		
		System.out.println("remove");
		lit.remove();
		System.out.println(ourArrayList);
		
		System.out.println("add 333");
		lit.add(333);
		System.out.println(ourArrayList);

		System.out.println("\nreverse\n");
		System.out.println(lit.next());
		System.out.println(lit.next());
		
		
		System.out.println("set 777");
		lit.set(777);
		System.out.println(ourArrayList);
		
		System.out.println(lit.next());

	}
}
