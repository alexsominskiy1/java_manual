package application;

import java.util.PriorityQueue;

import implementation.PriorityQueueStable;

public class PriorityQueueStableApp {
	
	public static void main(String[] args) {
		
		PriorityQueueStable<Node> priorityQueue = new PriorityQueueStable<>();
		//PriorityQueue<Pair> priorityQueue = new PriorityQueue<>();
		
		priorityQueue.offer(new Node("fred", 2));
		priorityQueue.offer(new Node("eugene", 2));
		priorityQueue.offer(new Node("david", 2));
		
		priorityQueue.offer(new Node("helen", 1));
		priorityQueue.offer(new Node("george", 1));
		
		priorityQueue.offer(new Node("camilla", 3));
		priorityQueue.offer(new Node("bob", 3));
		priorityQueue.offer(new Node("alice", 3));
		
		Node pair = null;
		while((pair = priorityQueue.poll()) != null) System.out.println(pair);
		
	}

}
