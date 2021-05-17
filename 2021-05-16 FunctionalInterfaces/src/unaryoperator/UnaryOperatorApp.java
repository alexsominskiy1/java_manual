package unaryoperator;

import java.util.function.DoubleUnaryOperator;

public class UnaryOperatorApp {
	
	public static void main(String[] args) {
		
		DoubleUnaryOperator cos = x -> Math.cos(x);
		
		System.out.println("found: " + bisectionRoot(0., 3.14, cos, 1.e-8));
		System.out.println("exact: " + Math.PI/2.);
		
	}
	
	public static double bisectionRoot(double a, double b, DoubleUnaryOperator fun, double eps) {
		
		// function root fun(x) = 0 at the (a,b) interval by bisection
		// assumptions: a < b; fun(a) and fun(b) are non zero and of different signs
		// eps is the absolute error
		
		double sign = fun.applyAsDouble(a) < 0 ? 1. : -1.;
		
		while (b - a > eps) {
			double midArg = (a + b)/2.;
			if (sign*fun.applyAsDouble(midArg) < 0.) a = midArg;
			else b = midArg;
		}
		
		return (a + b)/2.;
	}

}
