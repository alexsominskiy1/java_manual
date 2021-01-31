package dto;

public class Foo {
	
	public int publicField = 3;
	private int privateField = 5;
	
	public void publicMethod() {
		System.out.println("I'm public");
	}
 
	private void privateMethod() {
		System.out.println("I'm private");
	}

	@Override
	public String toString() {
		return "Foo [publicField=" + publicField + ", privateField=" + privateField + "]";
	}

	
}
