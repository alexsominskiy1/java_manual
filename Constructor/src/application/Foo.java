package application;

public class Foo {
	
	private int intField;
	private String stringField;
	
	public Foo() {
		super();
	}	
	public Foo(int intField) {
		super();
		this.intField = intField;
	}
	
	@SuppressWarnings("unused")
	private Foo(String msg) {
		super();
		System.out.println("private constructor: " + msg);
	}
	public Foo(int intField, String stringField) {
		super();
		this.intField = intField;
		this.stringField = stringField;
	}

	@Override
	public String toString() {
		return "Foo [intField=" + intField + ", stringField=" + stringField + "]";
	}
}
