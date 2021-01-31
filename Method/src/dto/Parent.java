package dto;

public class Parent {
	
	public static void parentStaticMethod(int num, String msg) {
		System.out.println("parent public static method message: " + msg+" "+num);
	}
	
	public String parentPublicMethod(int num, String msg) {
		return "parent public method message: " + msg+" "+num;
	}

	private String parentPrivateMethod(int num, String msg) {
		return "parent private method message: " + msg+" "+num;
	}

}
