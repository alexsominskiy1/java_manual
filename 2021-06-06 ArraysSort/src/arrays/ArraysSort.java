package arrays;

public class ArraysSort {
	
	private static void swap(int[] arr, int i, int j) {		
		int pocket = arr[i];
		arr[i] = arr[j];
		arr[j] = pocket;		
	}
	
	// select
	
	private static void selectMaxToEnd(int[] arr, int start, int end) {
		int maxIndex = start;
		for (int i = start + 1; i <= end; i++) {
			if(arr[i] > arr[maxIndex]) maxIndex = i;
		}
		swap(arr, maxIndex, end);
	}
	
	public static void selectSort(int[] arr, int start, int end) {
		for (int i = end; i > start; i--)selectMaxToEnd(arr, start, i);
	}
	
	// bubble
	
	private static boolean bubbleMaxToEnd(int[] arr, int start, int end) {
		boolean swop = false;
		for (int i = start + 1; i <= end; i++) {
			if (arr[i] < arr[i-1]) {
				swap(arr, i, i-1);
				swop = true;
			}
		}
		return swop;
	}
	
	public static void bubbleSort(int[] arr, int start, int end) {
		for (int i = end; end > start && bubbleMaxToEnd(arr, start, i); i--);
	}
	
	// quick
	
	
	public static void quickSort(int[] arr, int start, int end) {
		if (start < end)
	    {
	        int[] partitions = dutchFlagPartition(arr, start, end);
	        quickSort(arr, start, partitions[0] - 1);
	        quickSort(arr, partitions[1] + 1, end);
	    }
	}
	
	public static int[] dutchFlagPartition(int[] arr, int start, int end) {

		int pivot = arr[(start+end)/2];

		int pivotAreaStart = start;
		int current = start;
		int pivotAreaEnd = end;

		while (current <= pivotAreaEnd) {
			if (arr[current] < pivot) swap(arr,pivotAreaStart++,current++);
			else if (arr[current] > pivot)swap(arr, current, pivotAreaEnd--);
			else current++;
		}

		int[] result =  {pivotAreaStart, pivotAreaEnd};
		return result;
	}
}
