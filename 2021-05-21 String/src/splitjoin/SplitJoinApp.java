package splitjoin;

import java.util.Locale;

public class SplitJoinApp {

	public static void main(String[] args) {
		
		String names = "Alice,  Bob,Camilla, David";
		String[] namesSplit = names.split(",\s*");
		for(int i=0; i<namesSplit.length; i++)System.out.println(namesSplit[i]);
		String namesJoint = String.join(" -*- ", namesSplit);
		System.out.println("\n" + namesJoint);
		
		
	}
}
