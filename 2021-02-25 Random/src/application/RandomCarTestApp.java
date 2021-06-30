package application;

import dto.Car;

public class RandomCarTestApp {
	
	public static void main(String[] args) {		
		for (int i=0; i< 20; i++) System.out.println(Car.randomCar());	
	}

}
