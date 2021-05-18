package consumer;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerApp {
	
	private static Consumer<List<String>> toTitleCase = list -> {
		int listSize = list.size();
		for (int i = 0; i < listSize; i++) {
			String item = list.get(i);
			list.set(i, item.substring(0,1).toUpperCase() + item.substring(1).toLowerCase());
		}	
	};
	
	private static Consumer<List<String>> outputList = list -> {
		int listSize = list.size();
		for (int i = 0; i < listSize; i++) System.out.println(list.get(i));
	};
	
	public static void main(String[] args) {
		
		List<String> names = Arrays.asList("john", "robert", "mary", "jenny");
		toTitleCase.andThen(outputList).accept(names);	
	}
}
