package application;

import implementation.OurArrayList;

public class ArrayListApp {
	
	public static void main(String[] args) {
				
		OurArrayList<Integer> ourArrayList = new OurArrayList<>(15);
		
		for (int i = 0; i < 20; i++) ourArrayList.add(i*10);	
		System.out.println(ourArrayList);
		
		System.out.println("get(12): "+ourArrayList.get(12));
		System.out.println("size: "+ourArrayList.size());
		System.out.println("capacity: "+ourArrayList.capacity());
		System.out.println("index of 90: "+ourArrayList.indexOf(90));
		System.out.println("contains 95: "+ourArrayList.contains(95));
		
		System.out.println("set(5, 444)");
		ourArrayList.set(5, 444);
		System.out.println("add(10, 999");
		ourArrayList.add(10, 999);
		System.out.println("remove(13)");
		ourArrayList.remove(13);
		 
		for(Integer i : ourArrayList)System.out.print(i+ "  ");
		System.out.println();
		
		System.out.println("capacity: "+ourArrayList.capacity());
		System.out.println("trim to size()");
		ourArrayList.trimToSize();
		System.out.println("capacity: "+ourArrayList.capacity());
		
	}

}
