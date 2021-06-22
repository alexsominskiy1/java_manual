package application;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.Supplier;

public class RandomArrayLib {
	
	
	
	public static int[] randomIntArray(int size, int bound) {
		return randomIntArray(size, 0, bound);
	}
	
	public static int[] randomIntArray(int size, int lowerBound, int upperBound) {
		int[] result = new int[size];
		IntUnaryOperator generator = (i) -> ThreadLocalRandom.current().nextInt(lowerBound, upperBound);
		Arrays.setAll(result, generator);
		return result;
	}
	
	public static long[] randomLongArray(int size, long bound) {
		return randomLongArray(size, 0, bound);
	}
	
	public static long[] randomLongArray(int size, long lowerBound, long upperBound) {
		long[] result = new long[size];
		IntToLongFunction generator = (i) -> ThreadLocalRandom.current().nextLong(lowerBound, upperBound);
		Arrays.setAll(result, generator);
		return result;
	}
	
	public static double[] randomDoubleArray(int size, double bound) {
		return randomDoubleArray(size, 0., bound);
	}
	
	public static double[] randomDoubleArray(int size, double lowerBound, double upperBound) {
		double[] result = new double[size];
		IntToDoubleFunction generator = (i) -> ThreadLocalRandom.current().nextDouble(lowerBound, upperBound);
		Arrays.setAll(result, generator);
		return result;
	}
}
