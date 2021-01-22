package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;

public class CustomCollectorApp {

	public static void main(String[] args) {
		
		//  data to process
		
		String[] girls = {"Sasha", "Masha", "Dasha", "Natasha"};
		
		HashMap<String, Integer> rates = new HashMap<>();
		rates.put("John", 5);
		rates.put("James", 8);
		rates.put("Robert", 6);
		rates.put("Samuel", 5);
		rates.put("David", 9);
		rates.put("Mary", 8);
		rates.put("Alice", 7);
		rates.put("Camilla", 8);
		rates.put("Dorothy", 9);
		rates.put("Eva", 5);
		
		// simple example
		
		long sumLen = Arrays.stream(girls).collect(Collector.of(
				
				() -> {long[] acc = {0}; return acc;},				// supplier
				(acc, s) -> acc[0] = acc[0] + s.length(),			// accumulator 
				(acc1, acc2) -> { 	 								// combiner
					long[] result = new long[1]; 
					result[0] = acc1[0] + acc2[0]; 
					return result;
					},
				acc -> acc[0]));									// finisher
		
		System.out.println(sumLen);
		
		// more complicated example
		
		ArrayList<Map.Entry<String,Integer>> mostRatedStudents = 
				rates.entrySet().stream().collect(Collector.of(
					() -> new ArrayList<Map.Entry<String,Integer>>(),   // supplier
					(acc, e) -> {										// accumulator
						if(acc.size() == 0) acc.add(e);
						else{
							int currentValue = acc.get(0).getValue();
							int entryValue = e.getValue();
							if (currentValue <= entryValue) {
								if (currentValue < entryValue)acc.clear();
								acc.add(e);
							}
						}
					}, 
					(a1, a2) -> {a1.addAll(a2); return a1;} 			// combiner
																		// no finisher !
				));
				
		for (Map.Entry<String,Integer> e : mostRatedStudents)
			System.out.println(e.getKey() + "\t\t" + e.getValue());	
		}
}
