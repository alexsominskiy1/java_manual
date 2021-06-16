package application;

public class Node implements Comparable<Node>{
	
	private String name;
	private int value;
	
	public Node(String name, int value) {
		super();
		this.name = name;
		this.value = value;
	}

	@Override
	public int compareTo(Node other) {
		return value - other.value;
	}

	@Override
	public String toString() {
		return "Node [name=" + name + ", value=" + value + "]";
	}
}
