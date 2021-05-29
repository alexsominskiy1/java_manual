package transform;

import java.util.function.Function;

public class TransformApp {
	
	static Function<String, Boolean> isPalindrome = str -> {
		String normalized = str.toLowerCase().replaceAll("[\\W]|_", "");
		int left = 0;
		int right = normalized.length() - 1;
		while(right > left) {
			if (normalized.charAt(left) != normalized.charAt(right)) return false;
			left++;
			right--;
		}
		return true;
	};
	
	public static void main(String[] args) {
		
		String sample1 = "Was it a car or a cat I saw?";
		System.out.println(sample1.transform(isPalindrome));
		
		String sample2 = "Was it a car or a rat I saw?";
		System.out.println(sample2.transform(isPalindrome));
	}
}
