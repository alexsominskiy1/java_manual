package stringbuilder;

public class StringBuilderApp {

	private static final int NUM_CONCATENATIONS = 200000;

	public static void main(String[] args) {
		
		String hello = "hello";
		
		System.out.println("Number of concatenations: "+NUM_CONCATENATIONS);
		
		// string concatenation
		
		String result = "";
		long t1 = System.currentTimeMillis();
		for (int i = 0; i < NUM_CONCATENATIONS; i++) result = result + hello;
		long t2 = System.currentTimeMillis();
		System.out.println("concatenation: " + (t2-t1) + "ms");
		
		// string builder
		
		StringBuilder stringBuilder = new StringBuilder();
		t1 = System.currentTimeMillis();
		for (int i = 0; i < NUM_CONCATENATIONS; i++)stringBuilder.append(hello);
		t2 = System.currentTimeMillis();
		System.out.println("string builder: " + (t2-t1) + "ms");

	}
}
