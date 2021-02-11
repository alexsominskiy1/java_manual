package application;

import java.time.LocalDate;

import dto.Person;

public class AnnotationValidationApp {

	public static void main(String[] args) {
	
		Person person = new Person("Sasha", LocalDate.of(2010, 3, 21), 78.3, true);
		System.out.println(person);

	}
}
