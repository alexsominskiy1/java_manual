package application;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomAppPrimitives {

	// [a, b) for a range means: a inclusive, b exclusive
	
	public static void main(String[] args) {
		
		Random gen = new Random();
		
		long seed = 1234567890987L;
		Random genSeed = new Random(seed);
		
		// random int
		
		int randomInt = gen.nextInt();
		int threadLocalRandomInt = ThreadLocalRandom.current().nextInt();
		
		// upper bounded int - [0, bound) range
		
		int bound = 100;
		randomInt = gen.nextInt(bound);
		threadLocalRandomInt = ThreadLocalRandom.current().nextInt(bound);
		
		// int in the [min, max) range
		
		int min = -10;
		int max = 10;
		threadLocalRandomInt = ThreadLocalRandom.current().nextInt(min, max);
		
		// random long
		
		long randomLong = gen.nextLong();
		long threadLocalRandomLong = ThreadLocalRandom.current().nextLong();
		
		// random long - [0L, longBound) range
		
		long longBound = 1000000000000L;
		threadLocalRandomLong = ThreadLocalRandom.current().nextLong(longBound);
		
		// long in the [longMin, longMax) range
		
		long longMin = -10000000000L;
		long longMax = 10000000000L;
		threadLocalRandomLong = ThreadLocalRandom.current().nextLong(longMin, longMax);
		
		// random bytes
		
		byte[] bytes = new byte[10];
		gen.nextBytes(bytes);
		
		// random float - [0.f, 1.f) like Math.random() for floats
		
		float randomFloat = gen.nextFloat();
		float threadLocalRandomFloat = ThreadLocalRandom.current().nextFloat();
		
		// random double - [0., 1.) like Math.random()
		
		double randomDouble = gen.nextDouble();
		double threadLocalRandomDouble = ThreadLocalRandom.current().nextDouble();
		
		// upper bounded double - [0., doubleBound) range
		
		double doubleBound = 5.;
		threadLocalRandomDouble = ThreadLocalRandom.current().nextDouble(doubleBound);
		
		// double in range - [doubleMin, doubleMax)
		
		double doubleMin = -3.;
		double doubleMax = 7.;
		threadLocalRandomDouble = ThreadLocalRandom.current().nextDouble(doubleMin, doubleMax);
		
		// random boolean (equally probale true and false)
		
		boolean randomBoolean = gen.nextBoolean();
		boolean threadLocalRandomBoolean = ThreadLocalRandom.current().nextBoolean();

	}

}
