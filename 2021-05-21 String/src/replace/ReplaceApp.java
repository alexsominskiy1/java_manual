package replace;

public class ReplaceApp {

	public static void main(String[] args) {
		
		String example1 = "abracadabra";
		String example2 = "John,   Michael,Alice,   Bob, David";
		
		System.out.println(example1.replace('a', 'e'));
		System.out.println(example1.replace("abra", "!?^&"));
		System.out.println(example2.replaceAll(",\\s+|,", ", "));

	}

}
