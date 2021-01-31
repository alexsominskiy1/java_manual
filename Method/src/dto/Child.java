package dto;

public class Child extends Parent{

	public String childPublicMethod(int num, String msg) {
		return "child public method message: " + msg+" "+ num;
	}

	private String childPrivateMethod(int num, String msg) {
		return "child private method message: " + msg+" "+ num;
	}
}
