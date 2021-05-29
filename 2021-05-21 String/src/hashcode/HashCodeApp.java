package hashcode;

public class HashCodeApp {

	public static void main(String[] args) {
		String str = "abracadabra";
		System.out.println(str.hashCode());
		System.out.println(myHashCode(str));

	}
	
	public static int myHashCode(String str) {
		int hash = 0;
		for (int i=0; i<str.length(); i++) hash = 31*hash + str.charAt(i);
		return hash;
	}

}
