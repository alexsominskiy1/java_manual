package arrays;

public class ArraysMethods {
	
	public static <T extends Comparable<? super T>> int binarySearch(T[] arr, T key){
		
		int left = 0;
		int right = arr.length - 1;
		
		// ~x equal to -1-x
		
		if (key.compareTo(arr[0]) < 0) return -1;
		if (key.compareTo(arr[0]) == 0) return 0;
		if (key.compareTo(arr[right]) > 0) return ~arr.length;
		if (key.compareTo(arr[right]) == 0) return right;
		
		while (right - left > 1) {
			int mid = (right + left)/2;
			int comparision = key.compareTo(arr[mid]);
			if (comparision < 0) right = mid;
			else if (comparision == 0) return mid;
			else left = mid;
		}
		
		return ~right;
	}
	
	public static <T extends Comparable<? super T>> int mismatch(T[] arr1, T[] arr2) {
		int minLen = Integer.min(arr1.length, arr2.length);
		for (int i = 0; i < minLen; i++) {
			if (arr1[i].compareTo(arr2[i]) != 0) return i;
		}
		return -1;
	}
	
	public static <T extends Comparable<? super T>> int compare(T[] arr1, T[] arr2){
		int mismatch = mismatch(arr1, arr2);
		return mismatch < 0 ? arr1.length - arr2.length : arr1[mismatch].compareTo(arr2[mismatch]);
	}
	
	public static <T  extends Comparable<? super T>> boolean equals(T[] arr1, T[] arr2) {
		return compare(arr1, arr2) == 0;
	}

}
