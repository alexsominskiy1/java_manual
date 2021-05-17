package unaryoperator;

import java.util.function.IntUnaryOperator;

public class UnaryOperatorChainingApp {

	public static void main(String[] args) {
		
		IntUnaryOperator addTwo = i -> i + 2;
		IntUnaryOperator multiplyByTwo = i -> i * 2;
		
		System.out.println("andThen: " + addTwo.andThen(multiplyByTwo).applyAsInt(2));
		System.out.println("compose: " + addTwo.compose(multiplyByTwo).applyAsInt(2));
		
	}
}
