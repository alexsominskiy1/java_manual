package dto;

public class Employee extends Person{
	private double salary;

	public Employee() {
		super();
	}

	public Employee(String name, int age, double weight, boolean married, Address address, double salary) {
		super(name, age, weight, married, address);
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [salary=" + salary + "]"+super.toString();
	}
	
	
	
}
