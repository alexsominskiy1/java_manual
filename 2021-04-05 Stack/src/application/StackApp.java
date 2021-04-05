package application;

import model.Stack;

public class StackApp {

	public static void main(String[] args) {
		
		Stack<Integer> stack = new Stack<>(4);
		
		System.out.println(stack.offer(10));
		System.out.println(stack.offer(20));
		System.out.println(stack.offer(30));
		System.out.println(stack.offer(40));
		try {
			System.out.println(stack.add(50));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("peek: " + stack.peek());
		
		System.out.println("poll: " + stack.poll());
		System.out.println("poll: " + stack.poll());
		System.out.println("poll: " + stack.poll());
		System.out.println("poll: " + stack.poll());
		try {
			System.out.println("poll: " + stack.remove());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("finished");

	}

}
