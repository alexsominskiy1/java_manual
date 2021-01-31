package dto;

public class Person {
	
	private String name;
	private int age;
	private double weight;
	private boolean married;
	public Address address;
	
	public Person() {
		super();
	}

	public Person(String name, int age, double weight, boolean married, Address address) {
		super();
		this.name = name;
		this.age = age;
		this.weight = weight;
		this.married = married;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", weight=" + weight + ", married=" + married + ", address="
				+ address + "]";
	}
}
