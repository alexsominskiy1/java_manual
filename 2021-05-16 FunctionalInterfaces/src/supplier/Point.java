package supplier;

public class Point {
	
	double x;
	double y;
		
	public Point() {
		super();
	}

	public Point(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
		
	public static Point defaultPoint() {
		return new Point();
	}
	
	public static Point randomPointInSquare() {
		double x = Math.random();
		double y = Math.random();
		return new Point(x,y);
	}
	
	public static Point randomPointInCircle() {
		double r = Math.random();
		double phi = 2.*Math.PI*Math.random();
		return new Point(r*Math.cos(phi), r*Math.sin(phi));
	}
	
	public String toString() {
		return String.format("x : %.2f; y: %.2f", x, y);
	}

}
