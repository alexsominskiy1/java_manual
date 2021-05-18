package supplier;

import java.util.function.Supplier;

public class SupplierApp {
				
	public static Supplier<Point> defaultSupplier = () -> Point.defaultPoint();
	public static Supplier<Point> squareSupplier = () -> Point.randomPointInSquare();
	public static Supplier<Point> circleSupplier = () -> Point.randomPointInCircle();
	
	public static void printPoints(int num, Supplier<Point> pointSupplier) {
		
		for (int i=0; i < num; i++) System.out.println(pointSupplier.get());
	}
	
	public static void main(String[] args) {
			
		System.out.println("default");
		printPoints(5, defaultSupplier);
		
		System.out.println("square");
		printPoints(5, squareSupplier);
		
		System.out.println("circle");
		printPoints(5, circleSupplier);		
	}
}
